package com.projectsecond.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projectsecond.R;
import com.projectsecond.manager.CookCollectionManager;
import com.projectsecond.model.cookDetail.CookDetail;
import com.projectsecond.model.cookDetail.adapterInfo.CookDetailStruct;
import com.projectsecond.model.cookDetail.adapterInfo.CookRecipeMethod;
import com.projectsecond.model.cookDetail.adapterInfo.viewholder.CookManItemViewHolder;
import com.projectsecond.model.cookDetail.adapterInfo.viewholder.HeaderItemViewHolder;
import com.projectsecond.model.cookDetail.adapterInfo.viewholder.ItemViewHolder;
import com.projectsecond.model.cookDetail.adapterInfo.viewholder.StepItemViewHolder;
import com.projectsecond.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class CookDetailAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private Context context;
    private CookDetail srcData;
    private List<CookDetailStruct> datas;

    private String sumary;
    private ArrayList<String> ingredientsDatas;
    private ArrayList<CookRecipeMethod> cookRecipeMethods;
    private boolean isShowCollection;

    private Gson gson;
    private GlideUtil glideUtil;

    public CookDetailAdapter(Context context, CookDetail data, boolean isShowCollection) {
        this.context = context;
        this.srcData = data;
        this.isShowCollection = isShowCollection;
        this.gson = new Gson();
        this.glideUtil = new GlideUtil();
        sumary = data.getRecipe().getSumary();
        String str = data.getRecipe().getIngredients();
        if (null == str || TextUtils.isEmpty(str)) {
            ingredientsDatas = new ArrayList<>();
        } else {
            str = str.replace("\\", "");
            ingredientsDatas = gson.fromJson(str, new TypeToken<ArrayList<String>>() {
            }.getType());
        }
        str = data.getRecipe().getMethod();
        if (null == str || TextUtils.isEmpty(str)) {
            cookRecipeMethods = new ArrayList<>();
        } else {
            str = str.replace("\\", "");
            cookRecipeMethods = gson.fromJson(str, new TypeToken<ArrayList<CookRecipeMethod>>() {
            }.getType());
        }
        this.datas = CookDetailStruct.create(cookRecipeMethods);
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (0 == viewType) {
            return new CookManItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cook_detail_cookman, parent, false));
        } else if (1 == viewType) {
            return new HeaderItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cook_detail_header, parent, false));
        } else {
            return new StepItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cook_detail_step, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (0 == getItemViewType(position)) {
            final CookManItemViewHolder holderView = (CookManItemViewHolder) holder;

            if (isShowCollection) {
                holderView.switchIconView.setVisibility(View.VISIBLE);

                holderView.switchIconView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holderView.switchIconView.isIconEnabled()) {
                            CookCollectionManager.deleteCookDetail(srcData);
                        } else {
                            CookCollectionManager.saveCookDetail(srcData);
                        }
                        holderView.switchIconView.switchState();
                    }
                });
            } else {
                holderView.switchIconView.setVisibility(View.GONE);
            }

            holderView.textSumary.setText(sumary);
            return;
        }


        if (1 == getItemViewType(position)) {
            HeaderItemViewHolder holderView = (HeaderItemViewHolder) holder;
            if (ingredientsDatas.size() < 1) {
                holderView.view1.setVisibility(View.GONE);
                holderView.view2.setVisibility(View.GONE);
                holderView.view3.setVisibility(View.GONE);

                holderView.textIngredients.setVisibility(View.GONE);
            } else if (ingredientsDatas.size() < 2) {
                holderView.view1.setVisibility(View.VISIBLE);
                holderView.view2.setVisibility(View.GONE);
                holderView.view3.setVisibility(View.GONE);

                holderView.textIngredientsContent1.setText(ingredientsDatas.get(0));
            } else if (ingredientsDatas.size() < 3) {
                holderView.view1.setVisibility(View.VISIBLE);
                holderView.view2.setVisibility(View.VISIBLE);
                holderView.view3.setVisibility(View.GONE);

                holderView.textIngredientsContent1.setText(ingredientsDatas.get(0));
                holderView.textIngredientsContent2.setText(ingredientsDatas.get(1));
            } else {
                holderView.view1.setVisibility(View.VISIBLE);
                holderView.view2.setVisibility(View.VISIBLE);
                holderView.view3.setVisibility(View.VISIBLE);

                holderView.textIngredientsContent1.setText(ingredientsDatas.get(0));
                holderView.textIngredientsContent2.setText(ingredientsDatas.get(1));
                holderView.textIngredientsContent3.setText(ingredientsDatas.get(2));
            }
            return;
        }

        StepItemViewHolder holderView = (StepItemViewHolder) holder;
        CookRecipeMethod data = datas.get(position).getData();
        holderView.textContent.setText(data.getStep());

        if (data.getImg() != null && (!TextUtils.isEmpty(data.getImg()))) {
            holderView.imgvStep.setVisibility(View.VISIBLE);
            glideUtil.attach(holderView.imgvStep).injectImageWithNull(data.getImg());
        } else {
            holderView.imgvStep.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


}
