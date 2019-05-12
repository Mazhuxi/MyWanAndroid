package com.majiaxin.presenter;

import com.majiaxin.base.BasePresenter;
import com.majiaxin.bean.LoginBean;
import com.majiaxin.model.LogRegModel;
import com.majiaxin.view.LoginView;

public class LoginPresenter<V extends LoginView> extends BasePresenter<LoginView> implements LogRegModel.LoginCallBack {

    private LogRegModel mModel = new LogRegModel();

    public void getLoginData(String username, String password) {
        mModel.getLoginData(username,password,this);
    }

    @Override
    public void onLoginSuccess(LoginBean login) {
        if (mView != null){
            mView.onLoginSuccess(login);
        }
    }
}
