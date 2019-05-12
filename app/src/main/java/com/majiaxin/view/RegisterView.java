package com.majiaxin.view;

import com.majiaxin.base.BaseView;
import com.majiaxin.bean.LoginBean;

public interface RegisterView extends BaseView {
    void onRegisterSuccess(LoginBean register);
}
