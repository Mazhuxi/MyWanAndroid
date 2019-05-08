package com.majiaxin.presenter;

import com.majiaxin.bean.HomeBannerbBean;
import com.majiaxin.view.HomeView;
import com.majiaxin.base.BasePresenter;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.model.HomeModel;

import java.util.List;

public class HomePresnter<V extends HomeView> extends BasePresenter<HomeView> implements HomeModel.CallBack {

    private HomeModel mModel = new HomeModel();

    //获取首页文章数据
    public void getHomeData(int page) {
        mModel.getHomeData(page,this);
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

    //获取首页Banner数据
    public void getBannerData() {
        mModel.getHomeBannerData(this);
    }

    @Override
    public void onBannerSuccess(List<HomeBannerbBean.DataBean> data) {
        if (mView != null){
            mView.onBannerSuccess(data);
        }
    }

    @Override
    public void onBannerError(String error) {
        if (mView != null){
            mView.onBannerError(error);
        }
    }

}
