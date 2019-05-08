package com.majiaxin.view;

import com.majiaxin.base.BaseView;
import com.majiaxin.bean.KnowledgeBean;
import com.majiaxin.presenter.KnowledgePresenter;

import java.util.List;

public interface KnowledgeView extends BaseView {
    void onDataSuccess(List<KnowledgeBean.DataBean> list);
    void onDataError(String error);
}
