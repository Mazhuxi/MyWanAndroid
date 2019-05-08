package com.majiaxin.view;

import com.majiaxin.base.BaseView;
import com.majiaxin.bean.HomeBean;

public interface KnowArticleView extends BaseView {
    void onSuccess(HomeBean homeBean);
    void onError(String error);
}
