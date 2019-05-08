package com.majiaxin.day02_wanandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.majiaxin.base.SimpleActivity;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowUrlActivity extends SimpleActivity {


    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    WebView mWeView;
    private String mUrl;
    private String mTitle;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mTitle = intent.getStringExtra("title");

        initLoadUrl();
        initToolbar();
    }

    private void initLoadUrl() {
        mWeView.getSettings().setJavaScriptEnabled(true);
        mWeView.setWebViewClient(new WebViewClient());

        mWeView.loadUrl(mUrl);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_showurl;
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbarTitle.setText(mTitle);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_article_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
            case R.id.item_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_EMAIL,mTitle);
                startActivity(Intent.createChooser(shareIntent,"分享"));
                break;
            case R.id.item_system_browser:
                Intent browerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
                startActivity(browerIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //让菜单同时显示图标和文字
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
