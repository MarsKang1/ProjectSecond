package com.projectsecond.manager;

import com.projectsecond.model.cookDetail.CookDetail;
import com.projectsecond.model.entity.TbCookDetail;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 * 数据库管理类
 */
public class CookCollectionManager {

    //获取详情信息
    public static List<TbCookDetail> getCookDetails() {
        return DataSupport.findAll(TbCookDetail.class);
    }

    //保存详情信息
    public static void saveCookDetail(CookDetail data) {
        if (0 == DataSupport.where("menuId = ?", data.getMenuId()).count(TbCookDetail.class)) {
            TbCookDetail cook = cookDetail2TB(data);
            cook.save();
        }
    }

    //删除详情
    public static void deleteCookDetail(CookDetail data) {
        List<TbCookDetail> tbDatas = DataSupport.where("menuId = ?", data.getMenuId()).find(TbCookDetail.class);
        if (tbDatas != null && tbDatas.size() > 0) {
            tbDatas.get(0).delete();
        }
    }

    private static TbCookDetail cookDetail2TB(CookDetail cook) {
        TbCookDetail data = new TbCookDetail();
        data.setCtgTitles(cook.getCtgTitles());
        data.setMenuId(cook.getMenuId());
        data.setName(cook.getName());
        data.setThumbnail(cook.getThumbnail());
        data.setImg(cook.getRecipe().getImg());
        data.setMethod(cook.getRecipe().getMethod());
        data.setIngredients(cook.getRecipe().getIngredients());
        data.setSumary(cook.getRecipe().getSumary());
        data.setTitle(cook.getRecipe().getTitle());
        return data;
    }

}
