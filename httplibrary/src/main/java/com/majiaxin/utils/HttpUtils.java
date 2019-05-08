package com.majiaxin.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.majiaxin.bean.ApiException;
import com.majiaxin.bean.BaseResponce;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HttpUtils {
    //网络判断
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }

    //简化线程切换
    public static <T> ObservableTransformer<T,T> rxObserableSchedulerHelper(){
        return new ObservableTransformer<T,T>(){
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread());
            }
        };
    }

    //获取数据转换为自己想要的数据
    public static <T> ObservableTransformer<BaseResponce<T>,T> rxRequestData(){
        return new ObservableTransformer<BaseResponce<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponce<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponce<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponce<T> tBaseResponce) throws Exception {
                        if (tBaseResponce.getErrorCode() == 0){
                            return create(tBaseResponce.getData());
                        }else {
                            return  Observable.error(new ApiException(tBaseResponce.getErrorMsg(),tBaseResponce.getErrorCode()));
                        }
                    }
                });
            }
        };
    }

    //封装想要的数据
    private static <T> ObservableSource<T> create(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                try {
                    e.onNext(data);
                    e.onComplete();
                } catch (Exception e1) {
                    e.onError(e1);
                }
            }
        });
    }


}
