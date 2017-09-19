package com.projectsecond.mvp.loading;

import com.common.manager.RxJavaManager;
import com.common.ui.mvp.BasePresenter;
import com.google.gson.Gson;
import com.projectsecond.manager.CategoryManager;
import com.projectsecond.model.protoco.CategoryAllInfo;
import com.projectsecond.model.protoco.CategorySubGroup;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/5.
 */
public class LoadingPresenter extends BasePresenter {
    private LoadingView view;
    private LoadingModel model;
    private CategoryInfo subscriber;

    public LoadingPresenter(LoadingView view) {
        this.view = view;
        model = new LoadingModel();
        subscriber = new CategoryInfo();
        addSubscriber(subscriber);
    }

    /* @Override
     protected List<Subscriber> initSubscriberList() {
         return new ArrayList<>().add(subscriber);
     }
 */
    public void initData() {
        RxJavaManager.execute(model.getCategoryQuery(), subscriber);
    }

    private class CategoryInfo extends Subscriber<CategoryAllInfo> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (subscriber != null) subscriber.unsubscribe();
            CategoryManager.getInstance().initData(null);
            if (view != null) view.goToNextPage();
        }

        @Override
        public void onNext(CategoryAllInfo data) {
            String json = new Gson().toJson(data);
            int a = json.length();


            ArrayList<CategorySubGroup> datas = data.getResult().getChilds();
//            CookCategoryManager.getInstance().initDatas(datas);
            CategoryManager.getInstance().initData(datas);
            if (view != null) view.goToNextPage();
            this.onCompleted();
        }
    }
}
