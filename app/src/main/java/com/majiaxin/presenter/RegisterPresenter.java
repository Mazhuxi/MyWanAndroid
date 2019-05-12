package com.majiaxin.presenter;

import com.majiaxin.base.BasePresenter;
import com.majiaxin.bean.LoginBean;
import com.majiaxin.model.LogRegModel;
import com.majiaxin.view.RegisterView;

public class RegisterPresenter<V extends RegisterView> extends BasePresenter<RegisterView> implements LogRegModel.RegisterCallBack {

    private LogRegModel mModel = new LogRegModel();

    public void getRegisterData(String username, String password, String repassword) {
        mModel.getRegisterData(username,password,repassword,this);
    }

    @Override
    public void onRegisterSuccess(LoginBean register) {
        if (mView != null){
            mView.onRegisterSuccess(register);
        }
    }
}
