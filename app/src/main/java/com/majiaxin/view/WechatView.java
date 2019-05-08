package com.majiaxin.view;

import com.majiaxin.base.BaseView;
import com.majiaxin.bean.WechatTabBean;

import java.util.List;

public interface WechatView extends BaseView {
    void onSuccess(List<WechatTabBean.DataBean> list);
    void onError(String error);
}
