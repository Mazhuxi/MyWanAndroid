package com.majiaxin.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.LoginBean;
import com.majiaxin.day02_wanandroid.MainActivity;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.model.LogRegModel;
import com.majiaxin.presenter.RegisterPresenter;
import com.majiaxin.view.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegisterFragment extends BaseFragment<RegisterPresenter<RegisterView>, RegisterView> implements RegisterView {
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
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    Unbinder unbinder;

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
                ((MainActivity) getActivity()).switchFragmnet(MainActivity.TYPE_LOGIN);
                ((MainActivity) getActivity()).showDrawerLayout();
            }
        });

        //点击注册返回登录界面
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String repassword = mCopyPassword.getText().toString().trim();

                mPresenter.getRegisterData(username, password, repassword);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }


    @Override
    public void onRegisterSuccess(LoginBean register) {
        if (register.getErrorCode() == 0) {
            Snackbar.make(mLinearLayout, register.getErrorMsg(), Snackbar.LENGTH_SHORT)
                    .setActionTextColor(getActivity().getResources().getColor(R.color.know_title_color2))
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .show();


            ((MainActivity) getActivity()).switchFragmnet(MainActivity.TYPE_LOGIN);
        } else {

            Snackbar.make(mLinearLayout, register.getErrorMsg(), Snackbar.LENGTH_SHORT)
                    .setActionTextColor(getActivity().getResources().getColor(R.color.know_title_color2))
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .show();
        }
    }
}
