package com.projectsecond.model.category.adapterInfo;

import com.projectsecond.model.protoco.Category;

/**
 * Created by Administrator on 2017/4/15.
 */

public class CookCategoryContentStruct {
    public Category data1;
    public Category data2;
    public Category data3;

    public int isSelect = 0;

    public CookCategoryContentStruct() {

    }

    public CookCategoryContentStruct(Category data1, Category data2, Category data3) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.isSelect = 0;
    }

    public void add(Category data) {
        if (null == data1) {
            data1 = data;
            return;
        }

        if (null == data2) {
            data2 = data;
            return;
        }

        if (null == data3) {
            data3 = data;
            return;
        }
    }
}
