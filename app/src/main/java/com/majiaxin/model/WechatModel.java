package com.majiaxin.model;

import com.majiaxin.api.ApiManager;
import com.majiaxin.api.ApiService;
import com.majiaxin.bean.BaseResponce;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.bean.WechatTabBean;
import com.majiaxin.callback.ObserverCallBack;
import com.majiaxin.utils.HttpUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WechatModel {

    public interface TabCallBack {
        void onSuccess(List<WechatTabBean.DataBean> list);
        void onError(String error);
    }

    public interface ArtCallBack {
        void onSuccess(HomeBean homeBean);
        void onError(String error);
    }

    public void getWechatTabData(final TabCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.wanandroidUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        service.getWechatTabData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WechatTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(WechatTabBean wechatTabBean) {
                        List<WechatTabBean.DataBean> data = wechatTabBean.getData();
                        callBack.onSuccess(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public void getWechatArtData(int page,int id , final ArtCallBack callBack) {
        ApiManager.getInstance().getServer()
                .getWechatArtData(id,page)
                .compose(HttpUtils.<BaseResponce<HomeBean>>rxObserableSchedulerHelper())
                .compose(HttpUtils.<HomeBean>rxRequestData())
                .subscribe(new ObserverCallBack<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        callBack.onSuccess(homeBean);
                    }

                    @Override
                    public void onError(String error) {
                        callBack.onError(error);
                    }
                }); 

    }

    public void getWechatSearchArtData(int page,int id ,String quary, final ArtCallBack callBack) {
        ApiManager.getInstance().getServer()
                .getWechatSearchData(id,page,quary)
                .compose(HttpUtils.<BaseResponce<HomeBean>>rxObserableSchedulerHelper())
                .compose(HttpUtils.<HomeBean>rxRequestData())
                .subscribe(new ObserverCallBack<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        callBack.onSuccess(homeBean);
                    }

                    @Override
                    public void onError(String error) {
                        callBack.onError(error);
                    }
                });

    }

}
