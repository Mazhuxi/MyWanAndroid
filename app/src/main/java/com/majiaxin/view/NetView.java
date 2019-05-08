package com.majiaxin.view;

import com.majiaxin.base.BaseView;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.bean.NetBean;

import java.util.List;

public interface NetView extends BaseView {
    void onNetSuccess(List<NetBean.DataBean> list);
    void onHotData(List<NetBean.DataBean> list);
    void onSerachData(HomeBean homeBean);
}
