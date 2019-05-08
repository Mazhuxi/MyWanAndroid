package com.majiaxin.fragment;

import com.majiaxin.base.BaseFragment;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.presenter.CollectPresenter;
import com.majiaxin.view.CollectView;

public class CollectFragment extends BaseFragment<CollectPresenter<CollectView>,CollectView> {
    @Override
    protected CollectPresenter<CollectView> initPresenter() {
        return new CollectPresenter<>();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }
}
