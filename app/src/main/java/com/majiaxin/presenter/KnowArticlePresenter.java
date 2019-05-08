package com.majiaxin.presenter;

import com.majiaxin.base.BasePresenter;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.model.KnowledgeModel;
import com.majiaxin.view.KnowArticleView;

public class KnowArticlePresenter<V extends KnowArticleView> extends BasePresenter<KnowArticleView> implements KnowledgeModel.ArtCallBack {

    private KnowledgeModel mModel = new KnowledgeModel();

    public void getArtData(int page, int cid) {
        mModel.getKnowArtData(page,cid,this);
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
