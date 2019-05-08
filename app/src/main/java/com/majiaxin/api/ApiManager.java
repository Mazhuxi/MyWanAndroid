package com.majiaxin.api;

import com.majiaxin.app.BaseApp;
import com.majiaxin.httplibrary.HttpManager;

import java.util.jar.Manifest;

public class ApiManager {
    private static volatile ApiManager mApiManager;

    private ApiManager() {
    }

    public static ApiManager getInstance(){
        if (mApiManager == null){
            synchronized (ApiManager.class){
                if (mApiManager == null){
                    mApiManager = new ApiManager();
                }
            }
        }
        return mApiManager;
    }

    public ApiService getServer(){
        return HttpManager.getInstance().getRetrofit(ApiService.wanandroidUrl,BaseApp.getmBaseApp()).create(ApiService.class);
    }
}
