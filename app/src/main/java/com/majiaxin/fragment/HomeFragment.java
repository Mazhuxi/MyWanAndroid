package com.majiaxin.fragment;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.bumptech.glide.util.LogTime;
import com.majiaxin.adapter.HomeAdapter;
import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.HomeBannerbBean;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.day02_wanandroid.MainActivity;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.day02_wanandroid.ShowUrlActivity;
import com.majiaxin.presenter.HomePresnter;
import com.majiaxin.utils.RecycleSpacesItemDecoration;
import com.majiaxin.view.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment<HomePresnter<HomeView>, HomeView> implements HomeView {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private int page = 0;
    private HomeAdapter mAdapter;
    private static final String TAG = "HomeFragment";

    @Override
    protected HomePresnter<HomeView> initPresenter() {
        return new HomePresnter<>();
    }

    @Override
    protected void initData() {
        //设置RecyclerView
        initRecyclerView();

        mPresenter.getBannerData();
        mPresenter.getHomeData(page);
        //刷新加载
        initSmartRefreshLayout();

    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new HomeAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new RecycleSpacesItemDecoration(5, 15));

        final MainActivity mainActivity = (MainActivity) getActivity();
        //滑动recyclerView隐藏tablayout
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 15 ) {
                        mainActivity.getFloatingActionButton().setVisibility(View.GONE);
                        mainActivity.getTabLayout().setVisibility(View.GONE);
                    } else if (dy < -15 ) {
                        mainActivity.getFloatingActionButton().setVisibility(View.VISIBLE);
                        mainActivity.getTabLayout().setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //点击悬浮按钮回到底部
        mainActivity.getFloatingActionButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

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

                mPresenter.getHomeData(page);

                mSmartRefreshLayout.finishLoadMore();
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mAdapter.clearBannerList();
                mAdapter.clearArtList();
                mAdapter.clearTitleList();

                mPresenter.getHomeData(page);
                mPresenter.getBannerData();

                mSmartRefreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
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
        Log.i(TAG, "onError: " + error);
    }

    @Override
    public void onBannerSuccess(List<HomeBannerbBean.DataBean> data) {
        if (data != null){
            mAdapter.addBannerData(data);
        }
    }

    @Override
    public void onBannerError(String error) {
        Log.i(TAG, "onBannerError: " + error);
    }
}
