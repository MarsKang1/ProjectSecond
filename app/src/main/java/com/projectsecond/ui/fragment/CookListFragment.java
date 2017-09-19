package com.projectsecond.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.common.ui.fragment.BaseFragment;
import com.common.ui.mvp.BasePresenter;
import com.projectsecond.R;
import com.projectsecond.ui.activity.CookCategoryActivity;
import com.projectsecond.adapter.CookListAdapter;
import com.projectsecond.model.cookDetail.CookDetail;
import com.projectsecond.model.entity.TbCookCategory;
import com.projectsecond.mvp.cooklist.CookListPresenter;
import com.projectsecond.mvp.cooklist.CookListView;
import com.projectsecond.ui.widget.fab_transformation.FabTransformation;
import com.projectsecond.ui.widget.twinklingrefreshlayout.Footer.BottomProgressView;
import com.projectsecond.ui.widget.twinklingrefreshlayout.RefreshListenerAdapter;
import com.projectsecond.ui.widget.twinklingrefreshlayout.TwinklingRefreshLayout;
import com.projectsecond.ui.widget.twinklingrefreshlayout.header.bezierlayout.BezierLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/14.
 */
public class CookListFragment extends BaseFragment implements CookListView {
    @Bind(R.id.refresh_layout)
    public TwinklingRefreshLayout refreshLayout;
    @Bind(R.id.recyclerview_list)
    public RecyclerView recyclerList;

    @Bind(R.id.view_overlay)
    public View viewOverlay;
    @Bind(R.id.fab_app)
    public FloatingActionButton floatingActionButton;
    @Bind(R.id.view_sheet)
    public View viewSheet;

    private CookListAdapter adapter;

    private TbCookCategory customCategoryData;
    private CookListPresenter presenter;


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cook_list;
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerList.setLayoutManager(new LinearLayoutManager(recyclerList.getContext()));
        adapter = new CookListAdapter(getActivity());
        recyclerList.setAdapter(adapter);

        BezierLayout headerView = new BezierLayout(getActivity());
        headerView.setRoundProgressColor(getResources().getColor(R.color.colorPrimary));
        headerView.setWaveColor(getResources().getColor(R.color.main_indicator_bg));
        headerView.setRippleColor(getResources().getColor(R.color.white));
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setWaveHeight(140);

        BottomProgressView bottomProgressView = new BottomProgressView(refreshLayout.getContext());
        bottomProgressView.setAnimatingColor(getResources().getColor(R.color.colorPrimary));
        refreshLayout.setBottomView(bottomProgressView);
        refreshLayout.setOverScrollBottomShow(true);

        final ArrayList<CookDetail> datas = new ArrayList<>();
        adapter.setDataList(datas);

        presenter = new CookListPresenter(this);
        presenter.updateRefreshCookMenuByID(customCategoryData.getCtgId());

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                presenter.updateRefreshCookMenuByID(customCategoryData.getCtgId());
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                presenter.loadMoreCookMenuByID(customCategoryData.getCtgId());
            }
        });
    }

    //按钮点击处理---------------------------------------------------------------------------------------------------

    @OnClick(R.id.fab_app)
    public void onClickFabApp() {
        if (floatingActionButton.getVisibility() == View.VISIBLE) {
            FabTransformation.with(floatingActionButton).setOverlay(viewOverlay).transformTo(viewSheet);
        }
    }

    @OnClick(R.id.view_overlay)
    public void onClickOverlay() {
        closeFabMenu();
    }

    @OnClick(R.id.relative_category)
    public void onClickCategory() {

        Intent intent = new Intent(getActivity(), CookCategoryActivity.class);
        getActivity().startActivity(intent);
//        CookCategoryActivity.startActivity(getActivity());
        onClickOverlay();
    }

    @OnClick(R.id.txt_title_collection)
    public void onClickCollection() {
//        CookCollectionListActivity.startActivity(getActivity());
        onClickOverlay();
    }

    //view实现接口处理-----------------------------------------------------------------------------------------------
    @Override
    public void onRefreshSuccess(ArrayList<CookDetail> list) {
        refreshLayout.finishRefreshing();
        adapter.setDataList(conversion(list));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefreshFaile(String msg) {
        refreshLayout.finishRefreshing();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreSuccess(ArrayList<CookDetail> list) {
        refreshLayout.finishLoadmore();
        adapter.addItems(conversion(list));
    }

    @Override
    public void onLoadMoreFaile(String msg) {
        refreshLayout.finishLoadmore();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    //其他----------------------------------------------------------------------------------------------
    public boolean closeFabMenu() {
        if (floatingActionButton.getVisibility() != View.VISIBLE) {
            FabTransformation.with(floatingActionButton).setOverlay(viewOverlay).transformFrom(viewSheet);
            return true;
        }
        return false;
    }

    private List<CookDetail> conversion(ArrayList<CookDetail> list) {
        List<CookDetail> datas = new ArrayList<>();
        for (CookDetail item : list) {
            datas.add(item);
        }
        return datas;
    }

    public void setCustomCategoryData(TbCookCategory customCategoryData) {
        this.customCategoryData = customCategoryData;
    }
}
