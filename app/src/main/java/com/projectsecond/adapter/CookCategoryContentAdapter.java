package com.projectsecond.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projectsecond.R;
import com.projectsecond.callback.CookCategoryContentCallback;
import com.projectsecond.model.protoco.Category;
import com.projectsecond.model.category.adapterInfo.CookCategoryContentStruct;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/4/15.
 */
public class CookCategoryContentAdapter extends BaseRecyclerAdapter<CookCategoryContentStruct> {
    private CookCategoryContentCallback callback;
    private CookCategoryContentStruct oldCook;

    public CookCategoryContentAdapter(CookCategoryContentCallback callback) {
        this.callback = callback;
    }

    public static List<CookCategoryContentStruct> createDatas(ArrayList<Category> datas) {
        List<CookCategoryContentStruct> dstDatas = new ArrayList<>();

        int size = datas.size();
        int shi = size / 3;
        int ge = size % 3;

        if (0 == shi) {
            if (0 == ge) return dstDatas;
            CookCategoryContentStruct item = new CookCategoryContentStruct();
            for (int i = 0; i < ge; i++) item.add(datas.get(i));
            dstDatas.add(item);
            return dstDatas;
        }

        int index = 0;
        for (int i = 0; i < shi; i++) {
            CookCategoryContentStruct item = new CookCategoryContentStruct(datas.get(index), datas.get(index + 1), datas.get(index + 2));
            dstDatas.add(item);
            index += 3;
        }

        if (0 == ge) return dstDatas;
        CookCategoryContentStruct item = new CookCategoryContentStruct();
        for (int i = 0; i < ge; i++) item.add(datas.get(index + i));
        dstDatas.add(item);
        return dstDatas;
    }

    @Override
    public CommonHolder<CookCategoryContentStruct> setViewHolder(ViewGroup parent) {
        return new CookCategoryHolder(parent.getContext(), parent);
    }

    private void updateSelection(CookCategoryContentStruct newCook, int position) {
        if (oldCook != null) oldCook.isSelect = 0;
        newCook.isSelect = position;
        oldCook = newCook;
        notifyDataSetChanged();
    }

    class CookCategoryHolder extends CommonHolder<CookCategoryContentStruct> {
        @Bind(R.id.btn_tag_1)
        Button btnTag1;
        @Bind(R.id.btn_tag_2)
        Button btnTag2;
        @Bind(R.id.btn_tag_3)
        Button btnTag3;

        CookCategoryHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_cook_category_content);
        }

        @Override
        public void bindData(final CookCategoryContentStruct cook) {
            if (cook.data1 != null) {
                btnTag1.setVisibility(View.VISIBLE);
                btnTag1.setText(cook.data1.getName());
                if (cook.isSelect == 1)
                    btnTag1.setSelected(true);
                else
                    btnTag1.setSelected(false);
                btnTag1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callback != null) {
                            updateSelection(cook, 1);
                            callback.onCookCategorySndClick(cook.data1.getCtgId(), cook.data1.getName());
                        }
                    }
                });

                if (cook.data2 == null) {
                    btnTag2.setVisibility(View.GONE);
                    btnTag3.setVisibility(View.GONE);
                    return;
                }

                btnTag2.setVisibility(View.VISIBLE);
                btnTag2.setText(cook.data2.getName());
                if (cook.isSelect == 2)
                    btnTag2.setSelected(true);
                else
                    btnTag2.setSelected(false);
                btnTag2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateSelection(cook, 2);
                        if (callback != null) {
                            callback.onCookCategorySndClick(cook.data2.getCtgId(), cook.data2.getName());
                        }
                    }
                });

                if (cook.data3 == null) {
                    btnTag3.setVisibility(View.GONE);
                    return;
                }

                btnTag3.setVisibility(View.VISIBLE);
                btnTag3.setText(cook.data3.getName());
                if (cook.isSelect == 3)
                    btnTag3.setSelected(true);
                else
                    btnTag3.setSelected(false);
                btnTag3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateSelection(cook, 3);

                        if (callback != null) {
                            callback.onCookCategorySndClick(cook.data3.getCtgId(), cook.data3.getName());
                        }
                    }
                });
            }
        }
    }

}
