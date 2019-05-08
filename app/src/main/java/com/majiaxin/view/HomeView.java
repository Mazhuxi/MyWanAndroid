package com.majiaxin.view;

import com.majiaxin.base.BaseView;
import com.majiaxin.bean.HomeBannerbBean;
import com.majiaxin.bean.HomeBean;

import java.util.List;

public interface HomeView extends BaseView {
    void onSuccess(HomeBean homeBean);
    void onError(String error);
    void onBannerSuccess(List<HomeBannerbBean.DataBean> data);
    void onBannerError(String error);
}
