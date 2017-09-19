package com.projectsecond.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.ui.fragment.BaseFragment;
import com.common.ui.mvp.BasePresenter;
import com.projectsecond.R;
import com.projectsecond.adapter.MainPagerAdapter;
import com.projectsecond.manager.CategoryManager;
import com.projectsecond.model.entity.TbCookCategory;
import com.projectsecond.utils.TabUtils;

import java.util.List;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/4/13.
 */
public class MainPageFragment extends BaseFragment {
    @Bind(R.id.magic_indicator)
    public TabLayout titleView;
    @Bind(R.id.view_pager)
    public ViewPager viewPager;
    private List<TbCookCategory> categoryList;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        categoryList = CategoryManager.getInstance().getDatas();//获取分类list
        viewPager.setAdapter(new MainPagerAdapter(getFragmentManager(), categoryList));
        titleView.setupWithViewPager(viewPager);
        TabUtils.dynamicSetTabLayoutMode(titleView);
    }

    public boolean onBackPressed() {
        return false;
    }

    public void updateChannel() {
//        initIndicatorView();
    }


}
