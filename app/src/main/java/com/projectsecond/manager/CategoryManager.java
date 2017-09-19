package com.projectsecond.manager;

import android.content.res.AssetManager;

import com.common.CommonApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projectsecond.model.protoco.Category;
import com.projectsecond.model.protoco.CategorySubGroup;
import com.projectsecond.model.channel.ChannelItem;
import com.projectsecond.model.entity.TbCookCategory;

import org.json.JSONArray;
import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2017/4/5.
 *
 */

public class CategoryManager {
    private static CategoryManager Instance = null;
    private List<TbCookCategory> datas;
    private List<TbCookCategory> otherDatas;

    public static CategoryManager getInstance() {
        if (Instance == null) Instance = new CategoryManager();
        return Instance;
    }

    public void initData(ArrayList<CategorySubGroup> categoryAllDatas) {
        datas = DataSupport.findAll(TbCookCategory.class);

        if (datas == null || datas.size() < 1) {
            datas = new ArrayList<>();

            try {
                StringBuilder buf = new StringBuilder();
                InputStream json = CommonApplication.getAppContext().getAssets().open("default_cook_category.json", AssetManager.ACCESS_STREAMING);
                BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
                String str;
                while ((str = in.readLine()) != null) {
                    buf.append(str);
                }
                in.close();
                JSONArray jo = new JSONArray(buf.toString());
                datas = new Gson().fromJson(jo.toString(), new TypeToken<List<TbCookCategory>>() {
                }.getType());
            } catch (Exception e) {

            }

        }

        otherDatas = new ArrayList<>();

        if (categoryAllDatas == null || categoryAllDatas.size() < 1)
            return;

        for (CategorySubGroup item1 : categoryAllDatas) {
            for (Category item2 : item1.getChilds()) {
                if (!isInDatas(item2.getCtgId())) {
                    otherDatas.add(categoryChildInfo2Tb(item2));
                }
            }
        }
    }


    private boolean isInDatas(String cid) {
        for (TbCookCategory item : datas) {
            if (item.getCtgId().equals(cid)) {
                return true;
            }
        }
        return false;
    }

    private TbCookCategory categoryChildInfo2Tb(Category categoryInfo) {
        return new TbCookCategory(categoryInfo);
    }

    private TbCookCategory ChannelItem2Tb(ChannelItem channelItem) {
        return new TbCookCategory(channelItem);
    }

    public List<TbCookCategory> getDatas() {
        return datas;
    }

    public List<TbCookCategory> getOtherDatas() {
        return otherDatas;
    }

    public void save(List<ChannelItem> channelItemDatas, List<ChannelItem> channelItemOtherDatas) {
        datas.clear();
        otherDatas.clear();
        for (ChannelItem item : channelItemDatas) datas.add(ChannelItem2Tb(item));
        for (ChannelItem item : channelItemOtherDatas) otherDatas.add(ChannelItem2Tb(item));
        DataSupport.deleteAll(TbCookCategory.class);
        DataSupport.saveAll(datas);
    }
}
