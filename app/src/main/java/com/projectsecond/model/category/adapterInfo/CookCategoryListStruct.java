package com.projectsecond.model.category.adapterInfo;

import com.projectsecond.model.protoco.Category;

public class CookCategoryListStruct {
    public Category data;
    public boolean isSelect;

    public CookCategoryListStruct(Category data) {
        this.data = data;
        this.isSelect = false;
    }
}