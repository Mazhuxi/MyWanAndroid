package com.majiaxin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.ProjectTabBean;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.presenter.ProjectPresenter;
import com.majiaxin.view.ProjectView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectFragment extends BaseFragment<ProjectPresenter<ProjectView>, ProjectView> implements ProjectView {

    @BindView(R.id.tabLayout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.project_divider)
    View mProjectDivider;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private List<Fragment> mFragments;
    private List<ProjectTabBean.DataBean> mList;
    private int currentPage;
    private static final String TAG = "ProjectFragment";

    @Override
    protected ProjectPresenter<ProjectView> initPresenter() {
        return new ProjectPresenter<>();
    }

    @Override
    protected void initData() {
        mPresenter.getProjectTabData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void onSuccess(List<ProjectTabBean.DataBean> data) {
        mList = data;
        initViewPager();
    }

    @Override
    public void onError(String error) {
        Log.i(TAG, "onError: " + error);
    }

    private void initViewPager() {
        initFramgnets();

        mViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mList == null? 0 : mList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mList.get(position).getName();
            }
        });

        //结合
        mTabLayout.setViewPager(mViewpager);
    }

    private void initFramgnets() {
        mFragments = new ArrayList<>();
        if (mList.size() > 0) {
            for (ProjectTabBean.DataBean dataBean : mList) {
                mFragments.add(ProjectArtFragment.newInstance(dataBean.getId()));
            }
        }
    }
}
