package com.projectsecond.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.common.ui.activity.BaseActivity;
import com.common.ui.mvp.BasePresenter;
import com.projectsecond.R;
import com.projectsecond.ui.fragment.MainPageFragment;

public class MainActivity extends BaseActivity {
    private MainPageFragment mainPageFragment;
    //点两次后退键退出应用-----------------------------------------------------------------------------------------------
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mainPageFragment = new MainPageFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main_content, mainPageFragment).show(mainPageFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (mainPageFragment.onBackPressed()) return;
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(context, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 30456 && resultCode == 30457) {
            if (mainPageFragment != null) mainPageFragment.updateChannel();
        }
    }

}
