package com.majiaxin.presenter;

import com.majiaxin.base.BasePresenter;
import com.majiaxin.base.BaseView;
import com.majiaxin.bean.ProjectTabBean;
import com.majiaxin.model.ProjectModel;
import com.majiaxin.view.ProjectView;

import java.util.List;

public class ProjectPresenter<V extends ProjectView> extends BasePresenter<ProjectView> implements ProjectModel.TabCallBack {

    private ProjectModel mModel = new ProjectModel();

    public void getProjectTabData() {
        mModel.getProjectTabData(this);
    }

    @Override
    public void onSuccess(List<ProjectTabBean.DataBean> list) {
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
