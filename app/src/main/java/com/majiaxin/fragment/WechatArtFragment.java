package com.majiaxin.fragment;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.majiaxin.adapter.HomeAdapter;
import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.day02_wanandroid.MainActivity;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.day02_wanandroid.ShowUrlActivity;
import com.majiaxin.presenter.WechatArtPresenter;
import com.majiaxin.utils.RecycleSpacesItemDecoration;
import com.majiaxin.view.WechatArtView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class WechatArtFragment extends BaseFragment<WechatArtPresenter<WechatArtView>, WechatArtView> implements WechatArtView {
    @BindView(R.id.search_text)
    EditText mSearchText;
    @BindView(R.id.search_btn)
    Button mSearchBtn;
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private int mId;
    private int page = 1;
    private HomeAdapter mAdapter;
    private static final String TAG = "WechatArtFragment";
    private String mQuary = "";
    private String mAuthor;

    public static Fragment newInstance(int id,String author) {
        WechatArtFragment wechatArtFragment = new WechatArtFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("author",author);
        wechatArtFragment.setArguments(bundle);

        return wechatArtFragment;
    }

    @Override
    protected WechatArtPresenter<WechatArtView> initPresenter() {
        return new WechatArtPresenter<>();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        mId = bundle.getInt("id");
        mAuthor = bundle.getString("author");

        mPresenter.getWechatArtData(page, mId);

        initRecyclerView();
        initSmartRefreshLayout();
        initSearchButton();
    }

    private void initSearchButton() {
        mSearchText.setHint(mAuthor+"带你发现更多干货");

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuary = "";
                mQuary = mSearchText.getText().toString().trim();

                if (mQuary.equals("")){
                    Toast.makeText(getActivity(), "没有搜索内容(*^_^*)", Toast.LENGTH_SHORT).show();
                }else {
                    mAdapter.clearArtList();
                    mPresenter.getWechatSearchData(page, mId, mQuary);
                    mSearchText.setText("");
                }

                shutDownSoftKeyboard();
            }
        });

    }

    //关闭软键盘
    private void shutDownSoftKeyboard(){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getActivity().getCurrentFocus()!=null){
            if (getActivity().getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new HomeAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置条目间距
        mRecyclerView.addItemDecoration(new RecycleSpacesItemDecoration(10, 10));

        final MainActivity mainActivity = (MainActivity) getActivity();
        //滑动recyclerView隐藏tablayout
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 15) {
                        mainActivity.getFloatingActionButton().setVisibility(View.GONE);
                        mainActivity.getTabLayout().setVisibility(View.GONE);
                    } else if (dy < -15) {
                        mainActivity.getFloatingActionButton().setVisibility(View.VISIBLE);
                        mainActivity.getTabLayout().setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //点击悬浮按钮回到顶部
        if (mainActivity.getFloatingActionButton() != null) {
            mainActivity.getFloatingActionButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerView.smoothScrollToPosition(0);
                }
            });
        }

        mAdapter.setOnItemListener(new HomeAdapter.onItemListener() {
            @Override
            public void onClick(String title, String url) {
                Intent intent = new Intent(getActivity(), ShowUrlActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }

    private void initSmartRefreshLayout() {
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (!mQuary.equals("")) {
                    mAdapter.clearArtList();
                    mPresenter.getWechatSearchData(page, mId, mQuary);
                } else {
                    mPresenter.getWechatArtData(page, mId);
                }

                mSmartRefreshLayout.finishLoadMore();
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mAdapter.clearArtList();

                if (!mQuary.equals("")) {
                    mAdapter.clearArtList();
                    mPresenter.getWechatSearchData(page, mId, mQuary);
                } else {
                    mPresenter.getWechatArtData(page, mId);
                }

                mSmartRefreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat_article;
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
}
