package com.projectsecond.model.protoco;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/5.（分类+list的最小组合 下面组合内容为分类的具体子类）
 * {
 * "categoryInfo": {
 * "ctgId": "0010001006",
 * "name": "按功能选择菜谱",
 * "parentId": "0010001001"
 * },
 * "childs": [
 * {
 * "categoryInfo": {
 * "ctgId": "0010001056",
 * "name": "减肥",
 * "parentId": "0010001006"
 * }
 * },
 * {
 * "categoryInfo": {
 * "ctgId": "0010001065",
 * "name": "润肺",
 * "parentId": "0010001006"
 * }
 * }
 * ]
 * }
 */

public class CategorySubGroup {
    private Category categoryInfo;
    private ArrayList<Category> childs;

    public Category getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(Category categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public ArrayList<Category> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Category> childs) {
        this.childs = childs;
    }
}
