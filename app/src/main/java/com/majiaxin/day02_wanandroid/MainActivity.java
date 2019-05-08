package com.majiaxin.day02_wanandroid;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.majiaxin.base.BaseActivity;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.bean.NetBean;
import com.majiaxin.fragment.CollectFragment;
import com.majiaxin.fragment.HomeFragment;
import com.majiaxin.fragment.KnowledgeFragment;
import com.majiaxin.fragment.LoginFragment;
import com.majiaxin.fragment.NavigationFragment;
import com.majiaxin.fragment.ProjectFragment;
import com.majiaxin.fragment.RegisterFragment;
import com.majiaxin.fragment.SettingsFragment;
import com.majiaxin.fragment.WechatFragment;
import com.majiaxin.myview.FlowLayout;
import com.majiaxin.presenter.NetPresenter;
import com.majiaxin.presenter.SearchTitleAdapter;
import com.majiaxin.utils.CircularAnimUtil;
import com.majiaxin.utils.SpUtil;
import com.majiaxin.utils.SystemUtil;
import com.majiaxin.view.Constants;
import com.majiaxin.view.NetView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//5cc7a4fa570df33e0600063b
public class MainActivity extends BaseActivity<NetPresenter<NetView>, NetView> implements NetView {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_search)
    ImageView mToolbarSearch;
    @BindView(R.id.img_net)
    ImageView mImgNet;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.frameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private ArrayList<Integer> mTitles;
    private ArrayList<Fragment> mFragments;
    private FragmentManager mFragmentManager;
    public static int TYPE_HOME = 0;
    private int TYPE_KOMWLEDGE = 1;
    private int TYPE_WECHAT = 2;
    private int TYPE_NAVIGATION = 3;
    private int TYPE_PROJECT = 4;
    private int TYPE_COLLECT = 5;
    public static int TYPE_SETTING = 6;
    public static int TYPE_LOGIN = 7;
    public static int TYPE_REGISTER = 8;
    private int beforeFragmentPosition = 0;
    private SettingsFragment mSettingsFragment;
    private PopupWindow mPopupWindow;
    private List<Integer> mColors;
    private FlowLayout mViewFlowLayout;
    private EditText mViewSearchText;
    private Button mViewSearchBtn;
    private FlowLayout mViewSearchFlowLayout;
    private TextView mViewClear;
    private RecyclerView mViewRecyclerView;
    private SearchTitleAdapter mAdapter;
    private String mQuary = "";
    private TextView mNullText;

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public TabLayout getTabLayout() {
        return mTabLayout;
    }

    public FloatingActionButton getFloatingActionButton() {
        return mFloatingActionButton;
    }

    public void showDrawerLayout() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    protected void initData() {
        //设置颜色集合
        initColors();

        //设置toolbar
        initToolbar();

        //设置侧滑样式
        initDrawLayout();

        //创建Titles集合
        initTitles();

        //创建Fragment集合
        initFragment();

        //初始化fragment
        initWanAndroidFragment();

        //创建tab并切换fragment
        initTabLayout();
    }

    private void initColors() {
        mColors = new ArrayList<>();
        mColors.add(R.color.search_title_color1);
        mColors.add(R.color.search_title_color2);
        mColors.add(R.color.search_title_color3);
        mColors.add(R.color.search_title_color4);
        mColors.add(R.color.search_title_color5);
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        //跳转搜索界面
        mToolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(R.layout.activity_main_popup_search);
                CircularAnimUtil.show(mToolbarSearch);
                mPopupWindow.showAtLocation(mDrawerLayout, Gravity.CENTER, 0, 0);

            }
        });

        //跳转网站界面
        mImgNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(R.layout.activity_main_popup_net);
                CircularAnimUtil.show(mImgNet);
                mPopupWindow.showAtLocation(mDrawerLayout, Gravity.CENTER, 0, 0);
            }
        });
    }

    //弹出popupwindow
    private void initPopupWindow(int layoutId) {
        View view = View.inflate(MainActivity.this, layoutId, null);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //设置弹出软键盘（调用该设置，还要在对应的Activity的清单文件加入android:windowSoftInputMode="adjustPan"）
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setFocusable(true);

        if (layoutId == R.layout.activity_main_popup_net) {
            Toolbar viewToolbar = view.findViewById(R.id.toolbar);
            mViewFlowLayout = view.findViewById(R.id.flowLayout);

            //点击返回
            viewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });
            //获取数据
            mPresenter.getNetData();

        } else if (layoutId == R.layout.activity_main_popup_search) {
            mViewSearchText = view.findViewById(R.id.search_text);
            mViewSearchBtn = view.findViewById(R.id.search_btn);
            Toolbar viewToolbar = view.findViewById(R.id.toolbar);
            mViewSearchFlowLayout = view.findViewById(R.id.flowLayout);
            mViewClear = view.findViewById(R.id.clear);
            mViewRecyclerView = view.findViewById(R.id.recyclerView);
            mNullText = view.findViewById(R.id.nullText);

            //点击返回
            viewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });
            mPresenter.getSearchData();
            //设置recyclerView
            mViewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mAdapter = new SearchTitleAdapter(this);
            mViewRecyclerView.setAdapter(mAdapter);
            initExitText();
        }
    }

    private void initExitText() {
        mViewSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuary = mViewSearchText.getText().toString().trim();

                if (!mQuary.equals("")) {
                    mPresenter.getArtData(1, 408, mQuary);
                }
            }
        });

        //清空集合
        mViewClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.mList.clear();
            }
        });
    }

    private void initDrawLayout() {
        //设置Menu中的图片可显示
        mNavigationView.setItemIconTintList(null);
        //设置三条杠
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mDrawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);

        //侧滑头部监听
        View headerView = mNavigationView.getHeaderView(0);
        headerView.findViewById(R.id.login_header).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragmnet(TYPE_LOGIN);
                setVisibility(View.GONE);
                mToolbar.setVisibility(View.GONE);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        //侧滑条目监听
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    default:
                        break;
                    case R.id.wan_android_item:
                        switchFragmnet(TYPE_HOME);
                        setVisibility(View.VISIBLE);
                        break;
                    case R.id.collection_item:
                        switchFragmnet(TYPE_COLLECT);
                        setVisibility(View.GONE);
                        break;
                    case R.id.settings_item:
                        switchFragmnet(TYPE_SETTING);
                        setVisibility(View.GONE);
                        break;
                    case R.id.about_item:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        break;

                }
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }

    @SuppressLint("RestrictedApi")
    private void setVisibility(int visible) {
        mTabLayout.setVisibility(visible);
        mFloatingActionButton.setVisibility(visible);
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(R.string.home);
        mTitles.add(R.string.knowledge);
        mTitles.add(R.string.wechat);
        mTitles.add(R.string.navigation);
        mTitles.add(R.string.project);
        mTitles.add(R.string.collection);
        mTitles.add(R.string.settings);
        mTitles.add(R.string.about);
        mTitles.add(R.string.login);
        mTitles.add(R.string.register);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new KnowledgeFragment());
        mFragments.add(new WechatFragment());
        mFragments.add(new NavigationFragment());
        mFragments.add(new ProjectFragment());
        mFragments.add(new CollectFragment());
        mSettingsFragment = new SettingsFragment();
        mFragments.add(mSettingsFragment);
        mFragments.add(new LoginFragment());
        mFragments.add(new RegisterFragment());
    }

    private void initWanAndroidFragment() {
        mFragmentManager = getSupportFragmentManager();
        int nightType = (int) SpUtil.getParam(Constants.NIGHT_CURRENT_FRAG_POS, TYPE_HOME);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mToolbarTitle.setText(mTitles.get(nightType));
        transaction.add(R.id.frameLayout, mFragments.get(nightType));
        transaction.commit();

    }

    private void initTabLayout() {
        //添加tab页
        createTabs();

        //创建tab与fragment的切换关系
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //获得被选中tab的索引
                int position = tab.getPosition();

                //对应fragment
                switchFragmnet(position);

                //修改toolbar标题
                mToolbarTitle.setText(mTitles.get(position));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    public void switchFragmnet(int type) {
        Fragment fragment = mFragments.get(type);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.frameLayout, fragment);
        }

        transaction.hide(mFragments.get(beforeFragmentPosition));

        transaction.show(fragment);

        transaction.commit();

        beforeFragmentPosition = type;

        mToolbarTitle.setText(mTitles.get(type));
    }

    //为Tablayout添加tab页
    private void createTabs() {
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getCustomView(TYPE_HOME)));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getCustomView(TYPE_KOMWLEDGE)));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getCustomView(TYPE_WECHAT)));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getCustomView(TYPE_NAVIGATION)));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getCustomView(TYPE_PROJECT)));
    }

    //获得当前tab页的自定义页面
    private View getCustomView(int position) {
        //获取tab页的布局
        View view = View.inflate(MainActivity.this, R.layout.activity_main_tab, null);
        //找到控件
        ImageView tab_img = view.findViewById(R.id.tab_img);
        TextView tab_title = view.findViewById(R.id.tab_title);
        //赋值
        if (position == 0) {
            tab_title.setText("首页");
            tab_img.setImageResource(R.drawable.tab_homepager_selector);
        } else if (position == 1) {
            tab_title.setText("知识体系");
            tab_img.setImageResource(R.drawable.tab_knowledge_selector);
        } else if (position == 2) {
            tab_title.setText("公众号");
            tab_img.setImageResource(R.drawable.tab_wechat_selector);
        } else if (position == 3) {
            tab_title.setText("导航");
            tab_img.setImageResource(R.drawable.tab_navigation_selector);
        } else if (position == 4) {
            tab_title.setText("项目");
            tab_img.setImageResource(R.drawable.tab_project_selector);
        }
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SpUtil.setParam(Constants.NIGHT_CURRENT_FRAG_POS, TYPE_HOME);
    }

    @Override
    protected NetPresenter<NetView> initPresenter() {
        return new NetPresenter<>();
    }

    @Override
    public void onNetSuccess(List<NetBean.DataBean> list) {
        if (list != null) {
            mViewFlowLayout.removeAllViews();

            for (int i = 0; i < list.size(); i++) {
                NetBean.DataBean dataBean = list.get(i);
                TextView tvNode = (TextView) View.inflate(MainActivity.this, R.layout.item_search_net_data, null);
                tvNode.setText(dataBean.getName());
                tvNode.setPadding(SystemUtil.dp2px(6f), SystemUtil.dp2px(6f), SystemUtil.dp2px(6f), SystemUtil.dp2px(6f));
                tvNode.setTextColor(getResources().getColor(R.color.text_color));
                tvNode.setBackground(getResources().getDrawable(mColors.get(i % 5)));

                int finalI = i;
                tvNode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ShowUrlActivity.class);
                        intent.putExtra("url", dataBean.getLink());
                        CircularAnimUtil.startActivity(MainActivity.this, intent, tvNode, mColors.get(finalI % 5));
                    }
                });

                mViewFlowLayout.addView(tvNode);
            }

        }
    }

    @Override
    public void onHotData(List<NetBean.DataBean> list) {
        if (list != null) {
            mViewSearchFlowLayout.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                NetBean.DataBean dataBean = list.get(i);
                TextView tvMode = (TextView) View.inflate(MainActivity.this, R.layout.item_search_net_data, null);
                tvMode.setText(dataBean.getName());
                tvMode.setPadding(SystemUtil.dp2px(6f), SystemUtil.dp2px(6f), SystemUtil.dp2px(6f), SystemUtil.dp2px(6f));
                tvMode.setTextColor(getResources().getColor(R.color.text_color));
                tvMode.setBackground(getResources().getDrawable(mColors.get(i % 5)));

                tvMode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mQuary = tvMode.getText().toString().trim();
                        mPresenter.getArtData(1, 408, mQuary);
                        Log.i("quary············", "onClick: "+mQuary.toString());
                    }
                });
                mViewSearchFlowLayout.addView(tvMode);
            }
        }
    }

    @Override
    public void onSerachData(HomeBean homeBean) {
        if (homeBean != null) {
            if (homeBean.getDatas().size() > 0) {
                mAdapter.addData(mQuary);
                mViewSearchText.setText("");

                Intent intent = new Intent(MainActivity.this, ShowSearch.class);
                String json = new Gson().toJson(homeBean);
                intent.putExtra("bean", json);
                intent.putExtra("quary", mQuary);
                startActivity(intent);

                //判断集合是否为空
                if (mAdapter.mList.size() > 0) {
                    mViewRecyclerView.setVisibility(View.VISIBLE);
                    mNullText.setVisibility(View.GONE);
                } else {
                    mViewRecyclerView.setVisibility(View.GONE);
                    mNullText.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(MainActivity.this, "抱歉，没有搜索内容", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
