package com.projectsecond.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.common.ui.activity.BaseActivity;
import com.common.ui.mvp.BasePresenter;
import com.projectsecond.R;
import com.projectsecond.adapter.CookCategoryContentAdapter;
import com.projectsecond.adapter.CookCategoryListAdapter;
import com.projectsecond.callback.CookCategoryContentCallback;
import com.projectsecond.callback.CookCategoryListCallback;
import com.projectsecond.manager.CookCategoryManager;

import butterknife.Bind;

public class CookCategoryActivity extends BaseActivity implements CookCategoryContentCallback, CookCategoryListCallback {
    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.recyclerview_list_category)
    public RecyclerView rvCategory;
    @Bind(R.id.recyclerview_list_content)
    public RecyclerView rvContent;
    CookCategoryListAdapter categoryAdapter;
    CookCategoryContentAdapter contentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cook_category;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        categoryAdapter = new CookCategoryListAdapter(this);
        categoryAdapter.setDataList(CookCategoryListAdapter.createDatas(CookCategoryManager.getCategoryFirDatas()));
        rvCategory.setLayoutManager(new LinearLayoutManager(rvCategory.getContext()));
        rvCategory.setAdapter(categoryAdapter);

        contentAdapter = new CookCategoryContentAdapter(this);
        contentAdapter.setDataList(CookCategoryContentAdapter.createDatas(CookCategoryManager.getCategorySndDatas(CookCategoryManager.getCategoryFirDatas().get(0).getCtgId())));
        rvContent.setLayoutManager(new LinearLayoutManager(rvContent.getContext()));
        rvContent.setAdapter(contentAdapter);
    }

    @Override
    public void onBackPressed() {
        boolean success = getSupportFragmentManager().popBackStackImmediate();
        if (!success) super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCookCategorySndClick(String ctgId, String name) {
//        CookListActivity.startActivity(this, ctgId, name);
        Intent intent = new Intent(this, CookListActivity.class);
        intent.putExtra("cid", ctgId);
        intent.putExtra("name", name);
        startActivity(intent);
    }

    @Override
    public void onCookCategoryFirClick(String ctgId) {
        contentAdapter.setDataList(CookCategoryContentAdapter.createDatas(CookCategoryManager.getCategorySndDatas(ctgId)));
    }
}
