package com.majiaxin.presenter;

import com.majiaxin.base.BasePresenter;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.bean.NetBean;
import com.majiaxin.model.HomeModel;
import com.majiaxin.model.WechatModel;
import com.majiaxin.view.NetView;

import java.util.List;

public class NetPresenter<V extends NetView> extends BasePresenter<NetView> implements HomeModel.NetCallBack,HomeModel.SearchCallBack,WechatModel.ArtCallBack{

    private HomeModel mHomeModel = new HomeModel();

    private WechatModel mWechatModel = new WechatModel();

    public void getNetData() {
        mHomeModel.getNetData(this);
    }


    public void getSearchData() {
        mHomeModel.getSearchData(this);
    }

    public void getArtData(int page, int id, String quary) {
        mWechatModel.getWechatSearchArtData(page,id,quary,this);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        if (mView != null){
            mView.onSerachData(homeBean);
        }
    }

    @Override
    public void onError(String error) {

    }


    @Override
    public void onNetSuccess(List<NetBean.DataBean> list) {

        if (mView != null){
            mView.onNetSuccess(list);
        }
    }

    @Override
    public void onSearchSuccess(List<NetBean.DataBean> list) {
        if (mView != null){
            mView.onHotData(list);
        }
    }
}
