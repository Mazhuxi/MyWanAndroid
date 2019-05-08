package com.majiaxin.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.majiaxin.adapter.KnowledgeAdapter;
import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.KnowledgeBean;
import com.majiaxin.day02_wanandroid.KnowArticleActivity;
import com.majiaxin.day02_wanandroid.MainActivity;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.presenter.KnowledgePresenter;
import com.majiaxin.view.KnowArticleView;
import com.majiaxin.view.KnowledgeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter<KnowledgeView>, KnowledgeView> implements KnowledgeView {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private KnowledgeAdapter mAdapter;
    private static final String TAG = "KnowledgeFragment";

    @Override
    protected KnowledgePresenter<KnowledgeView> initPresenter() {
        return new KnowledgePresenter<>();
    }

    @Override
    protected void initData() {
        mPresenter.getKnowledgeData();
        initRecyclerView();
        initSmartRefreshLayout();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new KnowledgeAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemListener(new KnowledgeAdapter.onItemListener() {
            @Override
            public void OnClick(int positon, KnowledgeBean.DataBean bean) {
                if (bean!=null){
                    Intent intent = new Intent(getActivity(),KnowArticleActivity.class);
                    intent.putExtra("children", bean );
                    startActivity(intent);
                }
            }
        });

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
    }

    private void initSmartRefreshLayout() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAdapter.clearList();
                mPresenter.getKnowledgeData();

                mSmartRefreshLayout.finishRefresh();
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(getActivity(),"没有更多干货了(*^_^*)",Toast.LENGTH_SHORT).show();
                mSmartRefreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void onDataSuccess(List<KnowledgeBean.DataBean> list) {
        mAdapter.addData(list);
    }

    @Override
    public void onDataError(String error) {
        Log.i(TAG, "onDataError: "+error);
    }
}
