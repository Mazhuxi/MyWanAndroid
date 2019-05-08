package com.majiaxin.model;

import android.util.Log;

import com.majiaxin.api.ApiManager;
import com.majiaxin.api.ApiService;
import com.majiaxin.bean.ArticleBean;
import com.majiaxin.bean.BaseResponce;
import com.majiaxin.bean.HomeBannerbBean;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.bean.NetBean;
import com.majiaxin.callback.ObserverCallBack;
import com.majiaxin.presenter.HomePresnter;
import com.majiaxin.presenter.NetPresenter;
import com.majiaxin.utils.HttpUtils;
import com.majiaxin.view.HomeView;
import com.majiaxin.view.NetView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModel {


    public interface CallBack {

        void onSuccess(HomeBean homeBean);

        void onError(String error);

        void onBannerSuccess(List<HomeBannerbBean.DataBean> data);

        void onBannerError(String error);
    }

    public interface NetCallBack {
        void onNetSuccess(List<NetBean.DataBean> list);
    }

    public interface SearchCallBack {
        void onSearchSuccess(List<NetBean.DataBean> list);
    }

    public void getHomeData(int page, final CallBack callBack) {
        ApiManager.getInstance().getServer()
                .getHomeData(page)
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

    public void getHomeBannerData(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.wanandroidUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        service.getHomeBannerData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<HomeBannerbBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HomeBannerbBean homeBannerbBean) {
                        if (homeBannerbBean != null) {
                            List<HomeBannerbBean.DataBean> data = homeBannerbBean.getData();
                            callBack.onBannerSuccess(data);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("tag", "onError: " + e.getMessage());

                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("tag", "onComplete: " + "完成");
                    }
                });
    }

    public void getNetData(NetCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.wanandroidUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        service.getNetData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<NetBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(NetBean netBean) {
                        callBack.onNetSuccess(netBean.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void getSearchData(SearchCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.wanandroidUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        service.getSearchData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<NetBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NetBean netBean) {
                        callBack.onSearchSuccess(netBean.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
