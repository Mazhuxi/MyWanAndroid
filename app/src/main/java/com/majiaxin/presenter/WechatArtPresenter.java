package com.majiaxin.presenter;

import com.majiaxin.base.BasePresenter;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.model.WechatModel;
import com.majiaxin.view.WechatArtView;

public class WechatArtPresenter<V extends WechatArtView> extends BasePresenter<WechatArtView> implements WechatModel.ArtCallBack {

    private WechatModel mModel = new WechatModel();

    public void getWechatArtData(int page, int id) {
        mModel.getWechatArtData(page,id,this);
    }

    public void getWechatSearchData(int page, int id, String quary) {
        mModel.getWechatSearchArtData(page,id,quary,this);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        if (mView != null){
            mView.onSuccess(homeBean);
        }
    }

    @Override
    public void onError(String error) {
        if (mView != null){
            mView.onError(error);
        }
    }
}
