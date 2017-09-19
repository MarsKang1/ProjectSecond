package com.projectsecond.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.projectsecond.ui.fragment.CookListFragment;
import com.projectsecond.model.entity.TbCookCategory;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<TbCookCategory> categoryList;

    public MainPagerAdapter(FragmentManager fm, List<TbCookCategory> categoryList) {
        super(fm);
        this.categoryList = categoryList;
    }

    @Override
    public CookListFragment getItem(int position) {
        CookListFragment fragment = new CookListFragment();
        fragment.setCustomCategoryData(categoryList.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryList.get(position).getName();
    }
}
