package com.projectsecond.model.cookDetail.adapterInfo.viewholder;

import android.view.View;
import android.widget.TextView;

import com.projectsecond.R;
import com.projectsecond.ui.widget.SwitchIconView;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/4/17.
 */

public class CookManItemViewHolder extends ItemViewHolder {
    @Bind(R.id.switchIconView_collection)
    public SwitchIconView switchIconView;
    @Bind(R.id.text_sumary)
    public TextView textSumary;

    public CookManItemViewHolder(View itemView) {
        super(itemView);
    }
}
