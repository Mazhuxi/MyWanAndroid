package com.majiaxin.presenter;

import com.majiaxin.base.BasePresenter;
import com.majiaxin.bean.KnowledgeBean;
import com.majiaxin.model.KnowledgeModel;
import com.majiaxin.view.KnowledgeView;

import java.util.List;

public class KnowledgePresenter<V extends KnowledgeView> extends BasePresenter<KnowledgeView> implements KnowledgeModel.CallBack {

    private KnowledgeModel mModel = new KnowledgeModel();

    public void getKnowledgeData() {
        mModel.getKnowledgeData(this);
    }

    @Override
    public void onSuccess(List<KnowledgeBean.DataBean> list) {
        if (mView != null){
            mView.onDataSuccess(list);
        }
    }

    @Override
    public void onError(String error) {
        if (mView != null){
            mView.onDataError(error);
        }
    }
}
