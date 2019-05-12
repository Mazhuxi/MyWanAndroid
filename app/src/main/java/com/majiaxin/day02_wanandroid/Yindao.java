package com.majiaxin.day02_wanandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;

import com.airbnb.lottie.LottieAnimationView;
import com.majiaxin.app.BaseApp;
import com.majiaxin.base.SimpleActivity;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Yindao extends SimpleActivity {

    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimation;
    @BindView(R.id.ten_animation)
    LottieAnimationView mTenAnimation;
    private CountDownTimer timer;

    @Override
    protected void initData() {
        //调用开始动画的方法
        /*startAnimation(mOneAnimation, "W.json");
        startAnimation(mTwoAnimation, "A.json");
        startAnimation(mThreeAnimation, "N.json");
        startAnimation(mFourAnimation, "A.json");
        startAnimation(mFiveAnimation, "N.json");
        startAnimation(mSixAnimation, "D.json");
        startAnimation(mSevenAnimation, "R.json");
        startAnimation(mEightAnimation, "I.json");
        startAnimation(mNineAnimation, "O.json");
        startAnimation(mTenAnimation, "D.json");*/

        //跳转页面
        timer = new CountDownTimer(1500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                timer.cancel();
                startActivity(new Intent(Yindao.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }.start();
    }

    private void cancelAnimation() {
        //调用取消动画的方法
        cancelAnimation(mOneAnimation);
        cancelAnimation(mTwoAnimation);
        cancelAnimation(mThreeAnimation);
        cancelAnimation(mFourAnimation);
        cancelAnimation(mFiveAnimation);
        cancelAnimation(mSixAnimation);
        cancelAnimation(mSevenAnimation);
        cancelAnimation(mEightAnimation);
        cancelAnimation(mNineAnimation);
        cancelAnimation(mTenAnimation);
    }

    private void startAnimation(LottieAnimationView mLottieAnimationView, String animationName) {
        mLottieAnimationView.setAnimation(animationName);   //设置一个动画
        mLottieAnimationView.playAnimation(); //播放动画
    }

    private void cancelAnimation(LottieAnimationView mLottieAnimationView) {
        //判断对象是否为空，不为空就取消
        if (mLottieAnimationView != null) {
            mLottieAnimationView.cancelAnimation();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yindao;
    }

    @Override
    protected void onDestroy() {
        cancelAnimation();  //清除动画
        super.onDestroy();
    }
}
