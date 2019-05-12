package com.majiaxin.api;

import com.majiaxin.bean.ArticleBean;
import com.majiaxin.bean.BaseResponce;
import com.majiaxin.bean.HomeBannerbBean;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.bean.KnowledgeBean;
import com.majiaxin.bean.LoginBean;
import com.majiaxin.bean.NavigationBean;
import com.majiaxin.bean.NetBean;
import com.majiaxin.bean.ProjectTabBean;
import com.majiaxin.bean.WechatTabBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    public static final String wanandroidUrl = "https://www.wanandroid.com/";

    @GET("article/list/{page}/json")
    Observable<BaseResponce<HomeBean>> getHomeData(@Path("page") int page);

    @GET("banner/json")
    Observable<HomeBannerbBean> getHomeBannerData();

    @GET("tree/json")
    Observable<KnowledgeBean> getKnowledgeData();

    @GET("article/list/{page}/json?")
    Observable<BaseResponce<HomeBean>> getKnowArtData(@Path("page") int page, @Query("cid") int cid);

    @GET("wxarticle/chapters/json")
    Observable<WechatTabBean> getWechatTabData();

    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponce<HomeBean>> getWechatArtData(@Path("id") int id, @Path("page") int page);

    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponce<HomeBean>> getWechatSearchData(@Path("id") int id, @Path("page") int page,@Query("k")String quary);

    @GET("project/tree/json")
    Observable<ProjectTabBean> getProjectTabData();

    @GET("project/list/{page}/json")
    Observable<BaseResponce<HomeBean>> getProjectArtData(@Path("page") int page, @Query("cid") int cid);

    @GET("navi/json")
    Observable<NavigationBean> getNavigationData();

    @GET("friend/json")
    Observable<NetBean> getNetData();

    @GET("/hotkey/json")
    Observable<NetBean> getSearchData();

    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> getLoginBean(@Field("username") String username,@Field("password")String password);

    @FormUrlEncoded
    @POST("user/register")
    Observable<LoginBean> getRegisterBean(@Field("username") String username,@Field("password")String password,@Field("repassword")String repassword);

}
