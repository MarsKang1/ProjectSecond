package com.projectsecond.utils;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.common.CommonApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * User: syc
 * Date: 2015/9/6
 * Time: 10:06
 * Email: ycshi@isoftstone.com
 * Dest: 工具类
 */

public class UIUtils {

    /**
     * 将JSon转换成字符串
     */
    public static <T> T json2str(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
            Log.i("records", e.toString());
        }
        return t;
    }

    public static <T> List<T> json2list(String jsonString) {
        List<T> t = null;
        try {
            t = new Gson().fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            Log.i("records", e.toString());
        }
        return t;
    }

    public static String getAssertStr(String jsonPath) {
        try {
            StringBuilder buf = new StringBuilder();
            InputStream json = CommonApplication.getAppContext().getAssets().open(jsonPath, AssetManager.ACCESS_STREAMING);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int dip2px(Context context, double dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

}
