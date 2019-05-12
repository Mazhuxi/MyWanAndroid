package com.majiaxin.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.majiaxin.adapter.ProjectAdapter;
import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.DatasBean;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.day02_wanandroid.MainActivity;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.day02_wanandroid.ShowUrlActivity;
import com.majiaxin.presenter.ProjectArtPresenter;
import com.majiaxin.utils.RecycleSpacesItemDecoration;
import com.majiaxin.view.ProjectArtView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectArtFragment extends BaseFragment<ProjectArtPresenter<ProjectArtView>, ProjectArtView> implements ProjectArtView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton mFloatingActionButton;
    Unbinder unbinder;
    private int mCid;
    private int page = 0;
    private ProjectAdapter mAdapter;
    private static final String TAG = "ProjectArtFragment";

    public static Fragment newInstance(int id) {
        ProjectArtFragment projectArtFragment = new ProjectArtFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("cid", id);
        projectArtFragment.setArguments(bundle);

        return projectArtFragment;
    }

    @Override
    protected ProjectArtPresenter<ProjectArtView> initPresenter() {
        return new ProjectArtPresenter<>();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        mCid = bundle.getInt("cid");

        mPresenter.getProjectData(page, mCid);

        initRecyclerView();
        initSmartRefreshLayout();
    }

    @SuppressLint("RestrictedApi")
    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new ProjectAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new RecycleSpacesItemDecoration(5, 15));

        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //滑动recyclerView隐藏
        final MainActivity mainActivity = (MainActivity) getActivity();
        //滑动recyclerView隐藏tablayout
        mainActivity.getFloatingActionButton().setVisibility(View.GONE);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 15) {
                        mainActivity.getFloatingActionButton().setVisibility(View.GONE);
                        mainActivity.getTabLayout().setVisibility(View.GONE);
                        mFloatingActionButton.setVisibility(View.GONE);
                    } else if (dy < -15) {
                        mainActivity.getFloatingActionButton().setVisibility(View.GONE);
                        mainActivity.getTabLayout().setVisibility(View.VISIBLE);
                        mFloatingActionButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //点击悬浮按钮回到顶部
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

        mAdapter.setItemListener(new ProjectAdapter.ItemListener() {
            @Override
            public void onClick(String title, String url) {
                Intent intent = new Intent(getActivity(), ShowUrlActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

        //点击悬浮按钮回到顶部
        mainActivity.getFloatingActionButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });


    }

    private void initSmartRefreshLayout() {
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getProjectData(page, mCid);

                mSmartRefreshLayout.finishLoadMore();
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mAdapter.clearList();

                mPresenter.getProjectData(page, mCid);

                mSmartRefreshLayout.finishRefresh(8000);
                mSmartRefreshLayout.finishLoadMore(8000);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_article;
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        if (homeBean != null) {
            if (homeBean.getDatas().size() == 0) {
                Toast.makeText(getActivity(), "没有更多干货了(*^_^*)", Toast.LENGTH_SHORT).show();
            } else {
                for (DatasBean datasBean : homeBean.getDatas()) {
                    datasBean.setSuperChapterName("");
                }
                mAdapter.addData(homeBean.getDatas());
            }
        }
    }

    @Override
    public void onError(String error) {
        Log.i(TAG, "onError: " + error);
    }

}
