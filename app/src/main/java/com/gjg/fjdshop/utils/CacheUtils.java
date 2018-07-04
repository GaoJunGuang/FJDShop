package com.gjg.fjdshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JunGuang_Gao
 * on 2017/4/13  10:02.
 * 类描述：
 */

public class CacheUtils {

    /**
     * 得到保存的String类型的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp=context.getSharedPreferences("shoppingcart",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }


    /**
     * 保存String类型数据
     * @param context 上下文
     * @param key
     * @param value 保存的值
     */
    public static void saveString(Context context, String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("shoppingcart",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).apply();
    }
}
