package com.majiaxin.model;

import com.majiaxin.api.ApiService;
import com.majiaxin.bean.LoginBean;
import com.majiaxin.utils.net.BaseObserver;
import com.majiaxin.utils.net.HttpUtils;
import com.majiaxin.utils.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class LogRegModel {

    public interface LoginCallBack{
        void onLoginSuccess(LoginBean login);
    }

    public interface RegisterCallBack{
        void onRegisterSuccess(LoginBean login);
    }

    public void getLoginData(String username, String password,LoginCallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.wanandroidUrl,ApiService.class)
                .getLoginBean(username,password)
                .compose(RxUtils.<LoginBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        callBack.onLoginSuccess(loginBean);
                    }
                });
    }

    public void getRegisterData(String username, String password,String repassword,RegisterCallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.wanandroidUrl,ApiService.class)
                .getRegisterBean(username,password, repassword)
                .compose(RxUtils.<LoginBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean != null){
                            callBack.onRegisterSuccess(loginBean);
                        }
                    }
                });
    }

}
