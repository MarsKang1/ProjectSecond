package com.projectsecond.model.cookDetail.adapterInfo.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectsecond.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/4/17.
 */

public class StepItemViewHolder extends ItemViewHolder {
    @Bind(R.id.text_content)
    public TextView textContent;
    @Bind(R.id.imgv_step)
    public ImageView imgvStep;

    public StepItemViewHolder(View itemView) {
        super(itemView);
    }

}
