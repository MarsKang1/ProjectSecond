package com.projectsecond.model.cookDetail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/4/5.
 * 食谱
 */

public class CookRecipe implements Parcelable {
    public static final Creator<CookRecipe> CREATOR = new Creator<CookRecipe>() {
        @Override
        public CookRecipe createFromParcel(Parcel in) {
            return new CookRecipe(in);
        }

        @Override
        public CookRecipe[] newArray(int size) {
            return new CookRecipe[size];
        }
    };
    private String img;
    private String ingredients;
    private String method;
    private String sumary;
    private String title;

    public CookRecipe() {
    }

    protected CookRecipe(Parcel in) {
        img = in.readString();
        ingredients = in.readString();

        method = in.readString();
        sumary = in.readString();
        title = in.readString();
    }

    public static Creator<CookRecipe> getCREATOR() {
        return CREATOR;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeString(this.ingredients);
        dest.writeString(this.method);
        dest.writeString(this.sumary);
        dest.writeString(this.title);
    }
}
