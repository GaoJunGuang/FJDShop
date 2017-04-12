package com.gjg.fjdshop.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.gjg.fjdshop.R;
import com.gjg.fjdshop.utils.PreferenceUtil;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FJDActivity extends AppCompatActivity {
    private Animation animation_in;
    private Animation animation_out;
    private Animation animation_scale;

    @BindView(R.id.application_bg)
    ImageView applicationBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 2017/4/9  添加友盟代理

        doSomeAction();

    }

    private void doSomeAction() {
        //先判断是否是第一次进入app，是：先进入欢迎界面 否：直接进入主界面
        boolean isFirst= PreferenceUtil.readBoolean(this, "First", "isFirst", true);
        if(isFirst){
            //改变isFirst
            PreferenceUtil.write(this,"First","isFirst",false);
            goToSplash();
        }else{
            toToHome();
        }
    }

    private void toToHome() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        RandomApplicationBg();
        initAnimation();
        initAnimationListener();

    }

    private void initAnimationListener() {
        animation_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                applicationBg.startAnimation(animation_scale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation_scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                applicationBg.startAnimation(animation_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Intent intent = new Intent(FJDActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void initAnimation() {
        animation_in= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        animation_in.setDuration(500);
        animation_scale=AnimationUtils.loadAnimation(this,R.anim.fade_in_scale);
        animation_scale.setDuration(2000);
        animation_out=AnimationUtils.loadAnimation(this,R.anim.fade_out);
        animation_in.setDuration(500);
        applicationBg.setAnimation(animation_in);
        //applicationBg.startAnimation(animation_in);

    }

    private void goToSplash() {
        Intent intent=new Intent(this,SplashIndicatorActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 随机选择背景图片
     */
    private void RandomApplicationBg() {

        int index = new Random().nextInt(2);
        if (index == 1) {
            applicationBg.setImageResource(R.drawable.entrance1);
        } else {
            applicationBg.setImageResource(R.drawable.entrance2);
        }

    }
}
