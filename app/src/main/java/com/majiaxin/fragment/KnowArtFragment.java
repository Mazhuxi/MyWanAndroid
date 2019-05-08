package com.majiaxin.fragment;


import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.majiaxin.adapter.HomeAdapter;
import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.day02_wanandroid.KnowArticleActivity;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.day02_wanandroid.ShowUrlActivity;
import com.majiaxin.presenter.KnowArticlePresenter;
import com.majiaxin.utils.RecycleSpacesItemDecoration;
import com.majiaxin.view.KnowArticleView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class KnowArtFragment extends BaseFragment<KnowArticlePresenter<KnowArticleView>, KnowArticleView> implements KnowArticleView{
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private HomeAdapter mAdapter;
    private static final String TAG = "KnowArtFragment";
    private int page = 0;
    private int mCid = 60;
    private FloatingActionButton mFloatingActionButton;

    public static Fragment newInstance(int id) {
        KnowArtFragment knowArtFragment = new KnowArtFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("cid",id);
        knowArtFragment.setArguments(bundle);

        return knowArtFragment;
    }

    @Override
    protected KnowArticlePresenter<KnowArticleView> initPresenter() {
        return new KnowArticlePresenter<>();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        mCid = bundle.getInt("cid");

        mPresenter.getArtData(page, mCid);

        initRecyclerView();
        initSmartRefreshLayout();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new HomeAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new RecycleSpacesItemDecoration(5, 15));

        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //滑动recyclerView隐藏
        mFloatingActionButton = ((KnowArticleActivity) getActivity()).getFloatingActionButton();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 10 ) {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(mFloatingActionButton, "translationY", mFloatingActionButton.getTranslationY(),mFloatingActionButton.getHeight() + 200);
                        animator.setDuration(200);
                        animator.start();
                    } else if (dy < 10) {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(mFloatingActionButton, "translationY", mFloatingActionButton.getHeight() + 200,0f);
                        animator.setDuration(200);
                        animator.start();
                    }
                }
            }
        });

        //点击悬浮按钮回到顶部
        if (mFloatingActionButton != null){
            mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerView.smoothScrollToPosition(0);
                }
            });
        }

        mAdapter.setOnItemListener(new HomeAdapter.onItemListener() {
            @Override
            public void onClick(String title,String url) {
                Intent intent = new Intent(getActivity(),ShowUrlActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }

    private void initSmartRefreshLayout() {
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getArtData(page,mCid);

                mSmartRefreshLayout.finishLoadMore();
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mAdapter.clearArtList();

                mPresenter.getArtData(page,mCid);

                mSmartRefreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowart;
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        if (homeBean != null) {
            if (homeBean.getDatas().size() == 0){
                Toast.makeText(getActivity(), "没有更多干货了(*^_^*)", Toast.LENGTH_SHORT).show();
            }else {
                mAdapter.addArticleData(homeBean.getDatas());
            }
        }
    }

    @Override
    public void onError(String error) {
        Log.i(TAG, "onError: "+error);
    }
}
