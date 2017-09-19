package com.projectsecond.model.cookDetail;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/5.
 */

public class CookMenu {
    private int curPage;
    private int total;
    private ArrayList<CookDetail> list;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<CookDetail> getList() {
        return list;
    }

    public void setList(ArrayList<CookDetail> list) {
        this.list = list;
    }
}
