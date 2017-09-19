package com.projectsecond.model.cookDetail.adapterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class CookDetailStruct {


    private int type;
    private CookRecipeMethod data;

    public CookDetailStruct(int type) {
        this.type = type;
    }

    public CookDetailStruct(int type, CookRecipeMethod data) {
        this.type = type;
        this.data = data;
    }

    public static List<CookDetailStruct> create(ArrayList<CookRecipeMethod> cookRecipeMethods) {
        List<CookDetailStruct> datas = new ArrayList<>();

        datas.add(new CookDetailStruct(0));
        datas.add(new CookDetailStruct(1));

        if (null == cookRecipeMethods)
            return datas;

        for (CookRecipeMethod item : cookRecipeMethods) {
            datas.add(new CookDetailStruct(2, item));
        }
        return datas;
    }

    public int getType() {
        return type;
    }

    public CookRecipeMethod getData() {
        return data;
    }
}
