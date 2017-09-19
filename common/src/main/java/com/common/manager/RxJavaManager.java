package com.common.manager;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/11.
 * RxJava的管理类
 */
public class RxJavaManager {
    public static void execute(Observable observable, Subscriber subscriber) {
        observable.observeOn(AndroidSchedulers.mainThread())//界面处理线程
                .subscribeOn(Schedulers.io())//任务执行线程
                .subscribe(subscriber);
    }
}
