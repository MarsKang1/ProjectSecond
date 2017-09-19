package com.projectsecond.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.projectsecond.R;
import com.projectsecond.mvp.loading.LoadingPresenter;
import com.projectsecond.mvp.loading.LoadingView;

public class LoadingActivity extends AppCompatActivity implements LoadingView {
    private LoadingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        presenter = new LoadingPresenter(this);
        presenter.initData();
    }

    @Override
    public void goToNextPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
