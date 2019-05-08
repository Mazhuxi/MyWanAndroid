package com.majiaxin.httplibrary;

import android.content.Context;
import android.util.Log;

import com.majiaxin.utils.HttpUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static volatile HttpManager sHttpManager;

    private HttpManager() {
    }

    public static HttpManager getInstance(){
        if (sHttpManager == null){
            synchronized (HttpManager.class){
                if (sHttpManager == null){
                    sHttpManager = new HttpManager();
                }
            }
        }
        return sHttpManager;
    }

    //首先要返回一个retrofit对象
    public Retrofit getRetrofit(String baseUrl, Context context){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkhttpClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getOkhttpClient(Context context) {
        //创建缓存对象
        Cache cache = new Cache(new File(context.getCacheDir(), "Cache"), 1024 * 1024 * 50);

        //缓存拦截器
        MyCacheinterceptor cacheinterceptor = new MyCacheinterceptor(context);

        //日志过滤器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    Log.e("OKHttp-----", text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e("OKHttp-----", message);
                }
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .cache(cache)  //设置缓存
                .addInterceptor(httpLoggingInterceptor)  //添加日志拦截器
                .addInterceptor(cacheinterceptor)  //添加缓存拦截器
                .addNetworkInterceptor(cacheinterceptor)  //添加网络拦截器
                .retryOnConnectionFailure(true) //失败重连机制
                .build();
    }

    //缓存拦截器
    private class MyCacheinterceptor implements Interceptor {
        private Context context;

        public MyCacheinterceptor(Context context) {
            this.context = context;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
            if (!HttpUtils.isNetworkAvailable(context)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();

            }
            Response originalResponse = chain.proceed(request);
            if(HttpUtils.isNetworkAvailable(context)) {
                int maxAge = 0;
                // 有网络时, 不缓存, 最大保存时长为0
                return originalResponse.newBuilder()
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            }else{
                int maxStale = 60 * 60 * 24;
                return originalResponse.newBuilder()
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

        }
    }
}
