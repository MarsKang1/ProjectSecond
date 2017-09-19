package com.common.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.ui.mvp.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/8.
 *
 */
public abstract class BaseFragment extends Fragment {
    protected BasePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initView(inflater, container, savedInstanceState);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (presenter == null && getPresenter() != null) {
            presenter = getPresenter();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (presenter != null) {
            presenter.destroy();
        }
    }

    protected abstract BasePresenter getPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
