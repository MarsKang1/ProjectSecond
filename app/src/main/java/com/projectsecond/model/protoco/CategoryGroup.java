package com.projectsecond.model.protoco;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/5.（食物的大分类组合 上面为分类信息 下面为该分类的子分类信息）
 * "result": {
 * "categoryInfo": {
 * "ctgId": "0010001001",
 * "name": "全部菜谱"
 * },
 * "childs": [
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
 * ]
 * }
 */
public class CategoryGroup {

    private Category categoryInfo;
    private ArrayList<CategorySubGroup> childs;

    public Category getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(Category categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public ArrayList<CategorySubGroup> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<CategorySubGroup> childs) {
        this.childs = childs;
    }
}
