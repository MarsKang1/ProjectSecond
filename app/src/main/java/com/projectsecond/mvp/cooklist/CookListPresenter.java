package com.projectsecond.mvp.cooklist;

import com.common.manager.RxJavaManager;
import com.common.ui.mvp.BasePresenter;
import com.projectsecond.model.cookDetail.CookMenuAllInfo;
import com.projectsecond.utils.ErrorExceptionUtil;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/14.
 */
public class CookListPresenter extends BasePresenter {

    private CookListView view;
    private CookListModel model;
    private CookListSubscribe subscribe;
    private CookListLoadMoreSubscribe moreSubscribe;
    private int curPage = 1;
    private int totalPages = 1;

    public CookListPresenter(CookListView view) {
        this.view = view;
        model = CookListModel.getInstance();
        addSubscriber(subscribe);
        addSubscriber(moreSubscribe);
    }

    public void updateRefreshCookMenuByID(String cid) {
        curPage = 1;
        RxJavaManager.execute(model.searchCookMenuByID(cid, curPage, 20), subscribe = new CookListSubscribe());
    }

    public void loadMoreCookMenuByID(String cid) {
        curPage++;
        if (curPage > totalPages) {
            curPage--;
            if (view != null) view.onLoadMoreFaile("没有更多数据了");
            return;
        }
        RxJavaManager.execute(model.searchCookMenuByID(cid, curPage, 20), moreSubscribe = new CookListLoadMoreSubscribe());
    }


    private class CookListLoadMoreSubscribe extends Subscriber<CookMenuAllInfo> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            if (moreSubscribe != null) moreSubscribe.unsubscribe();
            if (view != null) view.onLoadMoreFaile(ErrorExceptionUtil.getErrorMsg(e));
        }

        @Override
        public void onNext(CookMenuAllInfo cookMenuAllInfo) {
            totalPages = cookMenuAllInfo.getResult().getTotal();
            if (view != null) view.onLoadMoreSuccess(cookMenuAllInfo.getResult().getList());
            this.onCompleted();
        }
    }

    private class CookListSubscribe extends Subscriber<CookMenuAllInfo> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            if (subscribe != null) subscribe.unsubscribe();
            if (view != null) view.onRefreshFaile(ErrorExceptionUtil.getErrorMsg(e));
        }

        @Override
        public void onNext(CookMenuAllInfo cookMenuAllInfo) {
            totalPages = cookMenuAllInfo.getResult().getTotal();
            if (view != null) view.onRefreshSuccess(cookMenuAllInfo.getResult().getList());
            this.onCompleted();
        }
    }
}
