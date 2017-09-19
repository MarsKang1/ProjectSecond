package com.projectsecond.model.protoco;

/**
 * Created by Administrator on 2017/4/5 (食物分类的最小单元)
 * <p>
 * "categoryInfo": {
 * "ctgId": "0010001007",
 * "name": "荤菜",
 * "parentId": "0010001002"
 * }
 */
public class Category {
    private String ctgId;//每一项的最小子单元
    private String parentId;
    private String name;

    public String getCtgId() {
        return ctgId;
    }

    public void setCtgId(String ctgId) {
        this.ctgId = ctgId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
