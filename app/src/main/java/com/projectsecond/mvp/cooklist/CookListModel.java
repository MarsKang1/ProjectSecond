package com.projectsecond.mvp.cooklist;

import com.projectsecond.manager.RetrofitManager;
import com.projectsecond.model.protoco.CategoryAllInfo;
import com.projectsecond.model.cookDetail.CookMenuAllInfo;
import com.projectsecond.mvp.loading.ICookService;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/14.
 */

public class CookListModel {

    private static CookListModel Instance = null;
    ICookService iCookService;

    private CookListModel() {
        iCookService = RetrofitManager.getInstance().create(ICookService.class);
    }

    public static CookListModel getInstance() {
        if (Instance == null) Instance = new CookListModel();
        return Instance;
    }

    public Observable<CategoryAllInfo> getCategoryQuery() {
        return iCookService.getCategoryQuery("1b5cf0085e135");
    }

    public Observable<CookMenuAllInfo> searchCookMenuByID(final String cid, final int page, final int size) {
        return iCookService.searchCookMenuByID("1b5cf0085e135", cid, page, size);
    }

    public Observable<CookMenuAllInfo> searchCookMenuByName(final String name, final int page, final int size) {
        return iCookService.searchCookMenuByName("1b5cf0085e135", name, page, size);
    }
}
