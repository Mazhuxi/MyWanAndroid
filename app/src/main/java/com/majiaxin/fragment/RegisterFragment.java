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
import com.majiaxin.presenter.RegisterPresenter;
import com.majiaxin.view.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegisterFragment extends BaseFragment<RegisterPresenter<RegisterView>, RegisterView> {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.CopyPassword)
    EditText mCopyPassword;
    @BindView(R.id.register)
    Button mRegister;

    @Override
    protected RegisterPresenter<RegisterView> initPresenter() {
        return new RegisterPresenter<>();
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
                ((MainActivity)getActivity()).switchFragmnet(MainActivity.TYPE_LOGIN);
                ((MainActivity)getActivity()).showDrawerLayout();
            }
        });

        //
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

}
