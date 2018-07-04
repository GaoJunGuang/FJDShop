package com.gjg.fjdshop.app;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by JunGuang_Gao
 * on 2017/4/9  10:35.
 * 类描述：
 */

public class MyApplication extends Application implements Thread.UncaughtExceptionHandler{
    public static Context getContext() {
        return mContext;
    }

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mContext = this;
        /**
         * 初始化OkHttpUtils
         */
        initOkhttpClient();

        //设置程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    @Override
    public void uncaughtException(final Thread t, final Throwable e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                System.out.println(Thread.currentThread());
                Toast.makeText(getApplicationContext(),
                        "thread="+t.getId()+"ex="+e.toString(), Toast.LENGTH_SHORT).show();
                        Looper.loop();
            }
        }).start();
        SystemClock.sleep(3000);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
