package com.common.ui.mvp;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/5.
 * Presenter base类
 */
public abstract class BasePresenter {
    private List<Subscriber> subscribers = new ArrayList<>();

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    protected void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void destroy() {
        if (subscribers != null && subscribers.size() != 0) {
            for (int i = 0; i < subscribers.size(); i++) {
                Subscriber subscriber = subscribers.get(i);
                if (subscriber != null) subscriber.unsubscribe();
            }
        }
    }
}
