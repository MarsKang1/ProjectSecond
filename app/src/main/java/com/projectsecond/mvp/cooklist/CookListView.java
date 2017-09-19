package com.projectsecond.mvp.cooklist;

import com.projectsecond.model.cookDetail.CookDetail;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/14.
 */

public interface CookListView {

    public void onRefreshSuccess(ArrayList<CookDetail> list);

    public void onRefreshFaile(String msg);

    public void onLoadMoreSuccess(ArrayList<CookDetail> list);

    public void onLoadMoreFaile(String msg);
}
