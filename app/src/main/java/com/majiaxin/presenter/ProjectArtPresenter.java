package com.majiaxin.presenter;

import com.majiaxin.base.BasePresenter;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.model.ProjectModel;
import com.majiaxin.view.ProjectArtView;

public class ProjectArtPresenter<V extends ProjectArtView> extends BasePresenter<ProjectArtView> implements ProjectModel.ArtCallBack {

    private ProjectModel mModel = new ProjectModel();

    public void getProjectData(int page, int cid) {
        mModel.getProjectArtData(page,cid,this);
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
