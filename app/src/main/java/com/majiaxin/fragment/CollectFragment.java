package com.majiaxin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.majiaxin.adapter.CoolectAdapter;
import com.majiaxin.base.BaseFragment;
import com.majiaxin.bean.DatasBean;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.day02_wanandroid.ShowUrlActivity;
import com.majiaxin.presenter.CollectPresenter;
import com.majiaxin.utils.DaoUtils;
import com.majiaxin.view.CollectView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CollectFragment extends BaseFragment<CollectPresenter<CollectView>, CollectView> {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.nullText)
    TextView mNullText;
    private CoolectAdapter mAdapter;

    @Override
    protected CollectPresenter<CollectView> initPresenter() {
        return new CollectPresenter<>();
    }

    @Override
    protected void initData() {
        List<DatasBean> datasBeans = DaoUtils.quaryAllData();
        if (datasBeans.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mAdapter = new CoolectAdapter(getActivity());
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.addData(datasBeans);

            mAdapter.setOnItemListener(new CoolectAdapter.onItemListener() {
                @Override
                public void onClick(String title, String url) {
                    Intent intent = new Intent(getActivity(),ShowUrlActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("title",title);
                    startActivity(intent);
                }
            });
        } else {
            mRecyclerView.setVisibility(View.GONE);
            mNullText.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }
    }

    private void initRecyclerView() {

    }
}
