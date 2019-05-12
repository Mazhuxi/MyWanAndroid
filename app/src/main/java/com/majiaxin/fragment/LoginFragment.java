package com.majiaxin.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.LoginBean;
import com.majiaxin.day02_wanandroid.MainActivity;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.presenter.LoginPresenter;
import com.majiaxin.utils.SpUtil;
import com.majiaxin.utils.cache.EventMessage;
import com.majiaxin.view.Constants;
import com.majiaxin.view.LoginView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends BaseFragment<LoginPresenter<LoginView>, LoginView> implements LoginView {
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
    private String mTextUsername;
    private String mTextPassword;

    @Override
    protected LoginPresenter<LoginView> initPresenter() {
        return new LoginPresenter<>();
    }

    @Override
    protected void initData() {
        //点击toolbar返回
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).switchFragmnet(MainActivity.TYPE_HOME);
                ((MainActivity) getActivity()).showDrawerLayout();
            }
        });

    }

    @OnClick({R.id.login, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                mTextUsername = mUsername.getText().toString().trim();
                mTextPassword = mPassword.getText().toString().trim();

                mPresenter.getLoginData(mTextUsername, mTextPassword);
                break;
            case R.id.register:
                ((MainActivity)getActivity()).switchFragmnet(MainActivity.TYPE_REGISTER);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }


    @Override
    public void onLoginSuccess(LoginBean login) {
        if (login.getErrorCode() == 0){
            //保存值
            SharedPreferences.Editor edit = getActivity().getSharedPreferences(Constants.LOGIN, Context.MODE_PRIVATE).edit();
            edit.putBoolean("loginBoolean",true);
            edit.putString("username",login.getData().getUsername());
            edit.commit();

            //发送事件
            EventBus.getDefault().post(new EventMessage(login.getData().getUsername()));

            ((MainActivity)getActivity()).switchFragmnet(MainActivity.TYPE_HOME);
            ((MainActivity)getActivity()).getToolbar().setVisibility(View.VISIBLE);
        }
    }
}
