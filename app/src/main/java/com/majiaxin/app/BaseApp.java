package com.majiaxin.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class BaseApp extends Application {

    private static BaseApp mBaseApp;
    private boolean isDebug;
    //默认不是夜间模式
    public static int mMode = AppCompatDelegate.MODE_NIGHT_NO;

    @Override
    public void onCreate() {
        super.onCreate();

        mBaseApp = this;

        // Umeng
        UMConfigure.init(this, "5cc7a4fa570df33e0600063b",null,UMConfigure.DEVICE_TYPE_PHONE, null);
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);

        // 腾讯bugly
        CrashReport.initCrashReport(getApplicationContext(), "1a35b708da", false);

    }

    public static BaseApp getmBaseApp() {
        return mBaseApp;
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
