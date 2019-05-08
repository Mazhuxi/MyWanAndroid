package com.majiaxin.day02_wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.majiaxin.base.SimpleActivity;
import com.majiaxin.bean.KnowledgeBean;
import com.majiaxin.fragment.KnowArtFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowArticleActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton mFloatingActionButton;
    private ArrayList<Fragment> mFragments;
    private KnowledgeBean.DataBean mArtBean;
    private List<KnowledgeBean.DataBean.ChildrenBean> mChildren;

    public FloatingActionButton getFloatingActionButton() {
        return mFloatingActionButton;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mArtBean = (KnowledgeBean.DataBean) intent.getSerializableExtra("children");
        mChildren = mArtBean.getChildren();

        initToolBar();
        initViewPager();
    }

    private void initToolBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewPager() {
        initFramgnets();
        //结合
        mTablayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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
                return mChildren.get(position).getName();
            }
        });

    }

    private void initFramgnets() {
        mFragments = new ArrayList<>();
        if (mChildren.size() > 0) {
            for (KnowledgeBean.DataBean.ChildrenBean child : mChildren) {
                mFragments.add(KnowArtFragment.newInstance(child.getId()));
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_know_article;
    }
}
