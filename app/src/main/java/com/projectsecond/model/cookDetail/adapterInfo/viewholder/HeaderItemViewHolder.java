package com.projectsecond.model.cookDetail.adapterInfo.viewholder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectsecond.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/4/17.
 */

public class HeaderItemViewHolder extends ItemViewHolder {
    @Bind(R.id.text_ingredients)
    public TextView textIngredients;

    @Bind(R.id.relative_view1)
    public RelativeLayout view1;
    @Bind(R.id.relative_view2)
    public RelativeLayout view2;
    @Bind(R.id.relative_view3)
    public RelativeLayout view3;

    @Bind(R.id.text_ingredients_content1)
    public TextView textIngredientsContent1;
    @Bind(R.id.text_ingredients_content2)
    public TextView textIngredientsContent2;
    @Bind(R.id.text_ingredients_content3)
    public TextView textIngredientsContent3;

    public HeaderItemViewHolder(View itemView) {
        super(itemView);
    }
}
