package com.majiaxin.model;

import android.support.v7.widget.CardView;

import com.majiaxin.api.ApiManager;
import com.majiaxin.api.ApiService;
import com.majiaxin.bean.BaseResponce;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.bean.KnowledgeBean;
import com.majiaxin.callback.ObserverCallBack;
import com.majiaxin.presenter.KnowledgePresenter;
import com.majiaxin.utils.HttpUtils;
import com.majiaxin.view.KnowledgeView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class KnowledgeModel {

    public interface CallBack {
        void onSuccess(List<KnowledgeBean.DataBean> list);
        void onError(String error);
    }

    public interface ArtCallBack {
        void onSuccess(HomeBean homeBean);
        void onError(String error);
    }

    public void getKnowledgeData(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.wanandroidUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        service.getKnowledgeData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<KnowledgeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KnowledgeBean knowledgeBean) {
                        if (knowledgeBean!= null){
                            callBack.onSuccess(knowledgeBean.getData());
                        }
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

    public void getKnowArtData(int page,int cid , final ArtCallBack callBack) {
        ApiManager.getInstance().getServer()
                .getKnowArtData(page,cid)
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
