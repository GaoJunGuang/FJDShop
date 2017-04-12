package com.gjg.fjdshop.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by JunGuang_Gao
 * on 2017/4/9  10:35.
 * 类描述：
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
