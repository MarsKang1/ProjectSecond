package com.projectsecond.mvp.loading;

import com.common.ui.mvp.BaseModel;
import com.projectsecond.manager.RetrofitManager;
import com.projectsecond.model.protoco.CategoryAllInfo;
import com.projectsecond.model.cookDetail.CookMenuAllInfo;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/5.
 */

public class LoadingModel implements BaseModel {
    private ICookService iCookService = null;

    public LoadingModel() {
        iCookService = RetrofitManager.getInstance().create(ICookService.class);
    }

    public Observable<CategoryAllInfo> getCategoryQuery() {//查询菜谱的所有分类
        return iCookService.getCategoryQuery("1b5cf0085e135");
    }

    public Observable<CookMenuAllInfo> searchCookMenuByID(final String cid, final int page, final int size) {//根据标签ID/菜谱名称查询菜谱详情
        return iCookService.searchCookMenuByID("1b5cf0085e135", cid, page, size);
    }

    public Observable<CookMenuAllInfo> searchCookMenuByName(final String name, final int page, final int size) {//根据标签ID/菜谱名称查询菜谱详情
        return iCookService.searchCookMenuByName("1b5cf0085e135", name, page, size);
    }
}
