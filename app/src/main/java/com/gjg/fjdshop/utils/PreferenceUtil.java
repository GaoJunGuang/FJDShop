package com.gjg.fjdshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JunGuang_Gao
 * on 2017/4/5  14:19.
 * 类描述：
 */
public class PreferenceUtil {
    /**
     * 读取布尔值
     * @param context
     * @param fileName
     * @param key
     * @param defVal
     * @return
     */
    public static boolean readBoolean(Context context,String fileName,String key,boolean defVal){
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        boolean value_boolean=sharedPreferences.getBoolean(key,defVal);
        return value_boolean;
    }


    /**
     * 写入布尔值
     * @param context
     * @param fileName
     * @param key
     * @param writeBoolean
     */
    public static void write(Context context, String fileName, String key, boolean writeBoolean) {
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,writeBoolean);
        editor.commit();
    }
}
