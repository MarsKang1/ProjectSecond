package com.projectsecond.mvp.loading;

import com.projectsecond.model.protoco.CategoryAllInfo;
import com.projectsecond.model.cookDetail.CookMenuAllInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/5.
 */

public interface ICookService {

    @GET("category/query")
//查询菜谱的所有分类
    Observable<CategoryAllInfo> getCategoryQuery(@Query("key") String key);

    @GET("menu/search")
//根据标签ID/菜谱名称查询菜谱详情
    Observable<CookMenuAllInfo> searchCookMenuByID(@Query("key") String key, @Query("cid") String cid, @Query("page") int page, @Query("size") int size);

    @GET("menu/search")
//根据标签ID/菜谱名称查询菜谱详情
    Observable<CookMenuAllInfo> searchCookMenuByName(@Query("key") String key, @Query("name") String name, @Query("page") int page, @Query("size") int size);

    //    String Cook_Service_CategoryQuery = "category/query";//查询菜谱的所有分类
    //    String  Cook_Service_MenuSearch = "menu/search";//根据标签ID/菜谱名称查询菜谱详情
    //     String Cook_Parameter_Key = "key";//MobAPI 开发者Key
    //    String Cook_Parameter_Cid = "cid";//
    //    String Cook_Parameter_Name = "name";//
    //      String Cook_Parameter_Page = "page";//
    //    String Cook_Parameter_Size = "size";//
}
