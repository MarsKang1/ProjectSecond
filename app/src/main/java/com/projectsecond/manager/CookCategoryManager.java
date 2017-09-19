package com.projectsecond.manager;

import android.content.res.AssetManager;

import com.common.CommonApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projectsecond.model.protoco.Category;
import com.projectsecond.model.protoco.CategorySubGroup;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/12.
 */

public class CookCategoryManager {
    public static ArrayList<Category> getCategorySndDatas(String cid) {
        ArrayList<CategorySubGroup> categoryAllDatas = getCatregoryAllDate();
        for (CategorySubGroup item : categoryAllDatas) {
            if (item.getCategoryInfo().getCtgId().equals(cid)) {
                return item.getChilds();
            }
        }
        return null;
    }

    public static ArrayList<Category> getCategoryFirDatas() {
        ArrayList<Category> categoryFirDatas = new ArrayList<>();
        ArrayList<CategorySubGroup> categoryAllDatas = getCatregoryAllDate();
        for (CategorySubGroup item : categoryAllDatas) categoryFirDatas.add(item.getCategoryInfo());
        return categoryFirDatas;
    }

    //从json文件中取出数据
    private static ArrayList<CategorySubGroup> getCatregoryAllDate() {
        try {
            StringBuilder buf = new StringBuilder();
            InputStream json = CommonApplication.getAppContext().getAssets().open("default_cook_category_all.json", AssetManager.ACCESS_STREAMING);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
            JSONArray jo = new JSONArray(buf.toString());
            return new Gson().fromJson(jo.toString(), new TypeToken<ArrayList<CategorySubGroup>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
