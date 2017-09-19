package com.projectsecond.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectsecond.R;
import com.projectsecond.ui.activity.CookDetailActivity;
import com.projectsecond.model.cookDetail.CookDetail;
import com.projectsecond.utils.GlideUtil;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/4/14.
 */

public class CookListAdapter extends BaseRecyclerAdapter<CookDetail> {

    private Activity activity;
    private GlideUtil glideUtil;

    public CookListAdapter(Activity activity) {
        this.activity = activity;
        glideUtil = new GlideUtil();
    }

    @Override
    public CommonHolder<CookDetail> setViewHolder(ViewGroup parent) {
        return new CardHolder(parent.getContext(), parent);
    }


    class CardHolder extends CommonHolder<CookDetail> {

        @Bind(R.id.img_cook)
        ImageView imgvCook;

        @Bind(R.id.text_name)
        TextView textName;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_cook_list);
        }

        @Override
        public void bindData(final CookDetail cook) {
            textName.setText(cook.getName());

            if (cook.getRecipe() != null && cook.getRecipe().getImg() != null && (!TextUtils.isEmpty(cook.getRecipe().getImg())))
                glideUtil.attach(imgvCook).injectImageWithNull(cook.getRecipe().getImg());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, CookDetailActivity.class);
                    intent.putExtra("cook", cook);
                    intent.putExtra("collection", true);
                    activity.startActivityForResult(intent, 10029);
                }
            });
        }
    }

}
