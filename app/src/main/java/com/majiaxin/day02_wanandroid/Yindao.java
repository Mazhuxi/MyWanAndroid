package com.majiaxin.day02_wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Yindao extends AppCompatActivity {

    private ImageView mIvW;
    private ImageView mIvA;
    private ImageView mIvN;
    private ImageView mIvAa;
    private ImageView mIvNn;
    private ImageView mIvD;
    private ImageView mIvR;
    private ImageView mIvO;
    private ImageView mIvI;
    private ImageView mIvDd;
    private Timer mTimer;
    int i = 2;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 10) {
                i--;
                if (i <= 0) {
                    mTimer.cancel();
                    startActivity(new Intent(Yindao.this,MainActivity.class));
                    finish();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindao);
        initView();


    }

    private void initView() {
        mIvW = (ImageView) findViewById(R.id.iv_w);
        mIvA = (ImageView) findViewById(R.id.iv_a);
        mIvN = (ImageView) findViewById(R.id.iv_n);
        mIvAa = (ImageView) findViewById(R.id.iv_aa);
        mIvNn = (ImageView) findViewById(R.id.iv_nn);
        mIvD = (ImageView) findViewById(R.id.iv_d);
        mIvR = (ImageView) findViewById(R.id.iv_r);
        mIvO = (ImageView) findViewById(R.id.iv_o);
        mIvI = (ImageView) findViewById(R.id.iv_i);
        mIvDd = (ImageView) findViewById(R.id.iv_dd);


        //创建透明度动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2000);

        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation1.setDuration(2000);
        alphaAnimation1.setStartOffset(500);

        //设置动画
        mIvW.startAnimation(alphaAnimation);
        mIvA.startAnimation(alphaAnimation);
        mIvN.startAnimation(alphaAnimation1);
        mIvAa.startAnimation(alphaAnimation);
        mIvNn.startAnimation(alphaAnimation);
        mIvD.startAnimation(alphaAnimation);
        mIvR.startAnimation(alphaAnimation1);
        mIvO.startAnimation(alphaAnimation1);
        mIvI.startAnimation(alphaAnimation);
        mIvDd.startAnimation(alphaAnimation1);

        //倒计时跳转
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(10);
            }
        }, 1000, 1000);

    }
}
