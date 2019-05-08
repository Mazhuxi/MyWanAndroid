package com.majiaxin.view;

import com.majiaxin.base.BaseView;
import com.majiaxin.bean.ProjectTabBean;

import java.util.List;

public interface ProjectView extends BaseView {
    void onSuccess(List<ProjectTabBean.DataBean> data);
    void onError(String error);
}
