package com.projectsecond.model.channel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/5.
 */

public class ChannelItem implements Serializable {
    private static final long serialVersionUID = -6465237897027410019L;

    public String id;

    public String name;

    public Integer orderId;

    public Integer selected;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
}
