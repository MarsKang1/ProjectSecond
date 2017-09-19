package com.projectsecond.model.cookDetail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/5.
 */

public class CookDetail implements Parcelable {
    public static final Creator<CookDetail> CREATOR = new Creator<CookDetail>() {
        @Override
        public CookDetail createFromParcel(Parcel in) {
            return new CookDetail(in);
        }

        @Override
        public CookDetail[] newArray(int size) {
            return new CookDetail[size];
        }
    };
    private ArrayList<String> ctgIds;//分类ID
    private String ctgTitles;
    private String menuId;
    private String name;
    private CookRecipe recipe;
    private String thumbnail;

    public CookDetail() {
    }

    protected CookDetail(Parcel in) {
        ctgIds = in.createStringArrayList();
        ctgTitles = in.readString();
        menuId = in.readString();
        name = in.readString();
        recipe = in.readParcelable(CookRecipe.class.getClassLoader());
        thumbnail = in.readString();
    }

    public static Creator<CookDetail> getCREATOR() {
        return CREATOR;
    }

    public ArrayList<String> getCtgIds() {
        return ctgIds;
    }

    public void setCtgIds(ArrayList<String> ctgIds) {
        this.ctgIds = ctgIds;
    }

    public String getCtgTitles() {
        return ctgTitles;
    }

    public void setCtgTitles(String ctgTitles) {
        this.ctgTitles = ctgTitles;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CookRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(CookRecipe recipe) {
        this.recipe = recipe;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.ctgIds);
        dest.writeString(this.ctgTitles);
        dest.writeString(this.menuId);
        dest.writeString(this.name);
        dest.writeParcelable(this.recipe, flags);
        dest.writeString(this.thumbnail);
    }
}
