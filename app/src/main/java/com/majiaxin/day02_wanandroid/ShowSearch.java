package com.majiaxin.day02_wanandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.majiaxin.adapter.HomeAdapter;
import com.majiaxin.app.BaseApp;
import com.majiaxin.bean.DatasBean;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.utils.DaoUtils;
import com.majiaxin.utils.RecycleSpacesItemDecoration;
import com.majiaxin.view.Constants;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowSearch extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new HomeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置条目间距
        mRecyclerView.addItemDecoration(new RecycleSpacesItemDecoration(10, 10));

        //设置数据
        Intent intent = getIntent();
        String bean = intent.getStringExtra("bean");
        String quary = intent.getStringExtra("quary");

        mToolbarTitle.setText(quary);
        HomeBean homeBean = new Gson().fromJson(bean, HomeBean.class);

        mAdapter.addArticleData(homeBean.getDatas());

        mAdapter.setOnItemListener(new HomeAdapter.onItemListener() {
            @Override
            public void onClick(String title,String url) {
                Intent intent = new Intent(ShowSearch.this,ShowUrlActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });


        mAdapter.setOnCheakedListener(new HomeAdapter.onCheakedListener() {
            @Override
            public void onClick(int position, boolean isChecked,DatasBean item) {
                SharedPreferences sp = ShowSearch.this.getSharedPreferences(Constants.LOGIN, ShowSearch.this.MODE_PRIVATE);
                boolean loginBoolean = sp.getBoolean("loginBoolean", false);
                if (loginBoolean == false){
                    Toast.makeText(ShowSearch.this,"请先登录",Toast.LENGTH_SHORT).show();
                }else {
                    if (isChecked){
                        DaoUtils.insertData(item);
                        Toast.makeText(ShowSearch.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    }else {
                        DaoUtils.deleteData(item);
                        Toast.makeText(ShowSearch.this, "取消收藏", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}
