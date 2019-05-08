package com.majiaxin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.KnowledgeBean;
import com.majiaxin.bean.WechatTabBean;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.presenter.WechatPresenter;
import com.majiaxin.view.WechatView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WechatFragment extends BaseFragment<WechatPresenter<WechatView>, WechatView> implements WechatView {
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private static final String TAG = "WechatFragment";
    private List<WechatTabBean.DataBean> mList;
    private ArrayList<Fragment> mFragments;

    @Override
    protected WechatPresenter<WechatView> initPresenter() {
        return new WechatPresenter<>();
    }

    @Override
    protected void initData() {
        mPresenter.getWechatTab();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    public void onSuccess(List<WechatTabBean.DataBean> list) {
        mList = list;
        initTabLayout();
    }

    @Override
    public void onError(String error) {
        Log.i(TAG, "onError: "+error);
    }

    private void initTabLayout() {
        //结合
        mTablayout.setupWithViewPager(mViewpager);

        initViewPager();
    }

    private void initViewPager() {
        initFramgnets();
        //结合
        mTablayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mList.get(position).getName();
            }
        });

    }

    private void initFramgnets() {
        mFragments = new ArrayList<>();
        if (mList.size() > 0) {
            for (WechatTabBean.DataBean dataBean : mList) {
                mFragments.add(WechatArtFragment.newInstance(dataBean.getId(),dataBean.getName()));
            }
        }
    }
}
