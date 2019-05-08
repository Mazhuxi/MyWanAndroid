package com.majiaxin.view;

import com.majiaxin.bean.HomeBean;

public interface WechatArtView {
    void onSuccess(HomeBean homeBean);
    void onError(String error);
}
