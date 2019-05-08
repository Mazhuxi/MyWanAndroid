package com.majiaxin.fragment;

import android.content.res.Configuration;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.majiaxin.base.SimpleFragment;
import com.majiaxin.day02_wanandroid.MainActivity;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.utils.SpUtil;
import com.majiaxin.utils.UIModeUtil;
import com.majiaxin.view.Constants;

import butterknife.BindView;

public class SettingsFragment extends SimpleFragment {
    @BindView(R.id.cache)
    CheckBox mCache;
    @BindView(R.id.picture)
    CheckBox mPicture;
    @BindView(R.id.nightMode)
    CheckBox mNightMode;
    @BindView(R.id.opinion)
    RelativeLayout mOpinion;
    @BindView(R.id.cache_size)
    TextView mCacheSize;
    @BindView(R.id.cache_clear)
    RelativeLayout mCacheClear;

    @Override
    protected void initData() {

        //判断当前是否日间模式，如果是设置为未选中状态，不是设置为选中状态
        int currentNightMode = getActivity().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            mNightMode.setChecked(false);
        } else {
            mNightMode.setChecked(true);
        }

    }

    @Override
    protected void initListener() {
        //无图模式
        mPicture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SpUtil.setParam(Constants.PICTOR, isChecked);
                } else {
                    SpUtil.setParam(Constants.PICTOR, isChecked);
                }
            }
        });


        //夜间模式
        mNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //切换日夜间模式 --- Activity会重建 对应的碎片也会重建
                if (buttonView.isPressed()) {
                    // 切换并保存模式
                    UIModeUtil.changeModeUI((MainActivity) getActivity());
                    // 保存当前碎片的type
                    SpUtil.setParam(Constants.NIGHT_CURRENT_FRAG_POS, MainActivity.TYPE_SETTING);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

}