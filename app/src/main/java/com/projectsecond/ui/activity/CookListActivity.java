package com.projectsecond.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.common.ui.activity.BaseActivity;
import com.common.ui.mvp.BasePresenter;
import com.projectsecond.R;
import com.projectsecond.adapter.CookListAdapter;
import com.projectsecond.model.cookDetail.CookDetail;
import com.projectsecond.mvp.cooklist.CookListPresenter;
import com.projectsecond.mvp.cooklist.CookListView;
import com.projectsecond.ui.widget.twinklingrefreshlayout.Footer.BottomProgressView;
import com.projectsecond.ui.widget.twinklingrefreshlayout.RefreshListenerAdapter;
import com.projectsecond.ui.widget.twinklingrefreshlayout.TwinklingRefreshLayout;
import com.projectsecond.ui.widget.twinklingrefreshlayout.header.bezierlayout.BezierLayout;

import java.util.ArrayList;

import butterknife.Bind;

public class CookListActivity extends BaseActivity implements CookListView {
    @Bind(R.id.text_title)
    public TextView textTitle;
    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Bind(R.id.refresh_layout)
    public TwinklingRefreshLayout twinklingRefreshLayout;
    @Bind(R.id.recyclerview_list)
    public RecyclerView recyclerList;

    private String cid;
    private CookListAdapter cookListAdapter;
    private CookListPresenter cookListPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cook_list;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        cid = intent.getStringExtra("cid");
        textTitle.setText(intent.getStringExtra("name"));

        recyclerList.setLayoutManager(new LinearLayoutManager(recyclerList.getContext()));
        cookListAdapter = new CookListAdapter(this);
        recyclerList.setAdapter(cookListAdapter);

        BezierLayout headerView = new BezierLayout(this);
        headerView.setRoundProgressColor(getResources().getColor(R.color.white));
        headerView.setWaveColor(getResources().getColor(R.color.colorPrimary));
        headerView.setRippleColor(getResources().getColor(R.color.white));
        twinklingRefreshLayout.setHeaderView(headerView);
        twinklingRefreshLayout.setWaveHeight(140);

        BottomProgressView bottomProgressView = new BottomProgressView(twinklingRefreshLayout.getContext());
        bottomProgressView.setAnimatingColor(getResources().getColor(R.color.colorPrimary));
        twinklingRefreshLayout.setBottomView(bottomProgressView);
        twinklingRefreshLayout.setOverScrollBottomShow(true);

        twinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                cookListPresenter.updateRefreshCookMenuByID(cid);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                cookListPresenter.loadMoreCookMenuByID(cid);
            }
        });

        cookListPresenter = new CookListPresenter(this);
        cookListPresenter.updateRefreshCookMenuByID(cid);
    }

    //view的处理--------------------------------------------------------------------------------------------------
    @Override
    public void onRefreshSuccess(ArrayList<CookDetail> list) {
        twinklingRefreshLayout.finishRefreshing();
        cookListAdapter.setDataList(list);
        cookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefreshFaile(String msg) {
        twinklingRefreshLayout.finishRefreshing();
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreSuccess(ArrayList<CookDetail> list) {
        twinklingRefreshLayout.finishLoadmore();
        cookListAdapter.addItems(list);
    }

    @Override
    public void onLoadMoreFaile(String msg) {
        twinklingRefreshLayout.finishLoadmore();
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
