package com.majiaxin.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.majiaxin.base.BaseFragment;
import com.majiaxin.day02_wanandroid.MainActivity;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.presenter.LoginPresenter;
import com.majiaxin.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginFragment extends BaseFragment<LoginPresenter<LoginView>, LoginView> {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.register)
    Button mRegister;

    @Override
    protected LoginPresenter<LoginView> initPresenter() {
        return new LoginPresenter<>();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        //点击toolbar返回
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).switchFragmnet(MainActivity.TYPE_HOME);
                ((MainActivity)getActivity()).showDrawerLayout();
            }
        });
        //点击注册跳转到注册页面
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).switchFragmnet(MainActivity.TYPE_REGISTER);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }


}
