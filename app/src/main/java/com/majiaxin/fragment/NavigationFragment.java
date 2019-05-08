package com.majiaxin.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.majiaxin.adapter.NavigationAdapter;
import com.majiaxin.api.ApiService;
import com.majiaxin.bean.NavigationBean;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.utils.net.BaseObserver;
import com.majiaxin.utils.net.HttpUtils;
import com.majiaxin.utils.net.RxUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {
    private static final String TAG = "MoveFragment";
    @BindView(R.id.tab)
    VerticalTabLayout tab;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    Unbinder unbinder;

    private LinearLayoutManager mLayoutManager;
    private NavigationAdapter mRecyclerAdapter;
    private boolean isScroll = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_nav, null);
        initData();
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    private void initData() {
        HttpUtils.getInstance().getApiserver(ApiService.wanandroidUrl, ApiService.class)
                .getNavigationData()
                .compose(RxUtils.<NavigationBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<NavigationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NavigationBean moveBean) {
                        final List<NavigationBean.DataBean> list = moveBean.getData();
                        mLayoutManager = new LinearLayoutManager(getContext());
                        rlv.setLayoutManager(mLayoutManager);
                        mRecyclerAdapter = new NavigationAdapter(list, getContext());
                        rlv.setAdapter(mRecyclerAdapter);

                        tab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabView tab, int position) {
                                LinearLayoutManager layoutManager = (LinearLayoutManager) rlv.getLayoutManager();
                                layoutManager.scrollToPositionWithOffset(position, 0);
                            }

                            @Override
                            public void onTabReselected(TabView tab, int position) {

                            }
                        });

                        rlv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                //重写该方法主要是判断recyclerview是否在滑动
                                //0停止 ，1,2都是滑动
                                if (newState == 0) {
                                    isScroll = false;
                                } else {
                                    isScroll = true;
                                }
                                LinearLayoutManager layoutManager = (LinearLayoutManager) rlv.getLayoutManager();
                                //可见的第一条条目
                                int top = layoutManager.findFirstVisibleItemPosition();
                                tab.setTabSelected(top);
                            }

                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                //这个主要是recyclerview滑动时让tab定位的方法

                                               /*recyclerView : 当前滚动的view
                                dx : 水平滚动距离
                                dy : 垂直滚动距离
                                dx > 0 时为手指向左滚动,列表滚动显示右面的内容
                                dx < 0 时为手指向右滚动,列表滚动显示左面的内容
                                dy > 0 时为手指向上滚动,列表滚动显示下面的内容
                                dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                                LinearLayoutManager layoutManager = (LinearLayoutManager) rlv.getLayoutManager();
                                //可见的第一条条目
                                int top = layoutManager.findFirstVisibleItemPosition();
                                //可见的最后一条条目
                                int bottom = layoutManager.findLastVisibleItemPosition();
                                if (isScroll) {
                                    if (dy > 0) {
                                        tab.setTabSelected(top);
                                    }
                                }
                            }
                        });

                        mRecyclerAdapter.addData(list);

                        //tab栏的适配器
                        tab.setTabAdapter(new TabAdapter() {
                            @Override
                            public int getCount() {
                                return list.size();
                            }

                            @Override
                            public ITabView.TabBadge getBadge(int position) {
                                return null;
                            }

                            @Override
                            public ITabView.TabIcon getIcon(int position) {
                                return null;
                            }

                            @Override
                            public ITabView.TabTitle getTitle(int position) {
                                return new TabView.TabTitle.Builder()
                                        .setContent(list.get(position).getName())
                                        .build();
                            }

                            @Override
                            public int getBackground(int position) {
                                return 0;
                            }
                        });

                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}