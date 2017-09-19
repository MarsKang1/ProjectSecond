package com.projectsecond.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;

import com.common.ui.activity.BaseActivity;
import com.common.ui.mvp.BasePresenter;
import com.projectsecond.R;
import com.projectsecond.adapter.CookDetailAdapter;
import com.projectsecond.model.cookDetail.CookDetail;
import com.projectsecond.utils.GlideUtil;
import com.projectsecond.utils.StatusBarUtil;

import butterknife.Bind;

public class CookDetailActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    public CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.recyclerview_list)
    public RecyclerView recyclerList;

    @Bind(R.id.imgv_bg)
    public ImageView imgvBg;


    private CookDetail data;
    private boolean isShowCollection;
    private GlideUtil glideUtil;
    private CookDetailAdapter cookDetailAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cook_detail;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        StatusBarUtil.setImmersiveStatusBar(this);
        StatusBarUtil.setImmersiveStatusBarToolbar(toolbar, this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        data = intent.getParcelableExtra("cook");
        isShowCollection = intent.getBooleanExtra("collection", false);

        if (null == data) return;

        glideUtil = new GlideUtil();
        if (data.getRecipe().getImg() != null && (!TextUtils.isEmpty(data.getRecipe().getImg())))
            glideUtil.attach(imgvBg).injectImageWithNull(data.getRecipe().getImg());

        getSupportActionBar().setTitle(data.getName());

        cookDetailAdapter = new CookDetailAdapter(this, data, isShowCollection);
        recyclerList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerList.setAdapter(cookDetailAdapter);
    }
}
