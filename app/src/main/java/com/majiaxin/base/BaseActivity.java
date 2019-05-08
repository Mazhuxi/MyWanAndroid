package com.majiaxin.base;

import android.view.View;

public abstract class BaseActivity<P extends BasePresenter<V>, V> extends SimpleActivity {

    protected P mPresenter;

    @Override
    protected void initView(View view) {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.deachView();
        mPresenter = null;
    }
}
