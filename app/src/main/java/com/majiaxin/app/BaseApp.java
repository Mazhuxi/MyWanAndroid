package com.majiaxin.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.majiaxin.dao.DaoMaster;
import com.majiaxin.dao.DaoSession;
import com.majiaxin.day02_wanandroid.BuildConfig;
import com.majiaxin.utils.DebugUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class BaseApp extends Application {

    private static BaseApp mBaseApp;
    private boolean isDebug;
    private RefWatcher refWatcher;
    //默认不是夜间模式
    public static int mMode = AppCompatDelegate.MODE_NIGHT_NO;
    private static DaoSession mDaoSession;

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApp = this;

        refWatcher = setupLeakCanary();//2

        // Umeng
        UMConfigure.init(this, "5cc7a4fa570df33e0600063b", null, UMConfigure.DEVICE_TYPE_PHONE, null);
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);

        // 腾讯bugly
        CrashReport.initCrashReport(getApplicationContext(), "1a35b708da", false);

        // Debug模式下开启严格模式
        /*if (BuildConfig.DEBUG) {
            DebugUtils.startStrictModeThreadPolicy();   //线程策略
            DebugUtils.startStrictModeVmPolicy();     //虚拟机策略
        }*/

        //greendao
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "article.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = daoMaster.newSession();
    }

    public static BaseApp getmBaseApp() {
        return mBaseApp;
    }

    //检测内存泄漏的方法
    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);//1
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApp leakApplication = (BaseApp) context.getApplicationContext();
        return leakApplication.refWatcher;
    }

    //获取进程号对应的进程名
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


}
