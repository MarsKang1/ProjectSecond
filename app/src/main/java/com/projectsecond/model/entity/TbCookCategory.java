package com.projectsecond.model.entity;

import com.projectsecond.model.protoco.Category;
import com.projectsecond.model.channel.ChannelItem;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/4/5.
 * 食物分类
 */
public class TbCookCategory extends DataSupport {
    private String ctgId;//种类ID
    private String name;//种类的名称

    public TbCookCategory(Category categoryInfo) {
        this.ctgId = categoryInfo.getCtgId();
        this.name = categoryInfo.getName();
    }

    public TbCookCategory(ChannelItem channelItem) {
        this.ctgId = channelItem.getId();
        this.name = channelItem.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCtgId() {
        return ctgId;
    }

    public void setCtgId(String ctgId) {
        this.ctgId = ctgId;
    }
}
