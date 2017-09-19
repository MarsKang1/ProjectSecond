package com.projectsecond.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.CommonApplication;
import com.projectsecond.R;
import com.projectsecond.callback.CookCategoryListCallback;
import com.projectsecond.model.protoco.Category;
import com.projectsecond.model.category.adapterInfo.CookCategoryListStruct;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/4/15.
 * 可以继续优化将adapter做进一步处理
 */
public class CookCategoryListAdapter extends BaseRecyclerAdapter<CookCategoryListStruct> {

    private CookCategoryListCallback callback;

    public CookCategoryListAdapter(CookCategoryListCallback callback) {
        this.callback = callback;
    }

    public static List<CookCategoryListStruct> createDatas(ArrayList<Category> datas) {
        List<CookCategoryListStruct> dstDatas = new ArrayList<>();
        for (Category item : datas) {
            dstDatas.add(new CookCategoryListStruct(item));
        }
        if (dstDatas.size() > 0) dstDatas.get(0).isSelect = true;
        return dstDatas;
    }

    @Override
    public CommonHolder<CookCategoryListStruct> setViewHolder(ViewGroup parent) {
        return new CookCategoryHolder(parent.getContext(), parent);
    }

    class CookCategoryHolder extends CommonHolder<CookCategoryListStruct> {
        @Bind(R.id.ralative_bg)
        RelativeLayout relativeBg;
        @Bind(R.id.text_select)
        TextView textSelect;
        @Bind(R.id.text_category)
        TextView textCategory;

        CookCategoryHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_cook_category);
        }

        @Override
        public void bindData(final CookCategoryListStruct cook) {
            if (cook.isSelect) {
                textSelect.setVisibility(View.VISIBLE);
                relativeBg.setBackgroundColor(CommonApplication.getAppContext().getResources().getColor(R.color.white));
            } else {
                textSelect.setVisibility(View.GONE);
                relativeBg.setBackgroundColor(CommonApplication.getAppContext().getResources().getColor(R.color.black_alpha_light));
            }
            textCategory.setText(cook.data.getName().replace("按", "").replace("选择菜谱", ""));
            itemView.setOnClickListener(new MyClickListener(cook));
        }
    }

    private class MyClickListener implements View.OnClickListener {
        CookCategoryListStruct struct;

        MyClickListener(CookCategoryListStruct struct) {
            this.struct = struct;
        }

        @Override
        public void onClick(View v) {
            String id = struct.data.getCtgId();
            if (callback != null) callback.onCookCategoryFirClick(id);

            for (CookCategoryListStruct item : dataList) {
                item.isSelect = item.data.getCtgId().equals(id);
            }
            notifyDataSetChanged();
        }
    }
}
