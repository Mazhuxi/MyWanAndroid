package com.majiaxin.presenter;

import com.majiaxin.base.BasePresenter;
import com.majiaxin.bean.WechatTabBean;
import com.majiaxin.model.WechatModel;
import com.majiaxin.view.WechatView;

import java.util.List;

public class WechatPresenter<V extends WechatView> extends BasePresenter<WechatView> implements WechatModel.TabCallBack {

    private WechatModel mModel = new WechatModel();

    public void getWechatTab() {
        mModel.getWechatTabData(this);
    }

    @Override
    public void onSuccess(List<WechatTabBean.DataBean> list) {
        if (mView != null){
            mView.onSuccess(list);
        }
    }

    @Override
    public void onError(String error) {
        if (mView != null){
            mView.onError(error);
        }
    }
}
