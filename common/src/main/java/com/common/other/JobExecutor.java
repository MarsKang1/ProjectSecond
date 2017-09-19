package com.common.other;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/11.
 * 使用方法
 * 在 new RxJavaExecuter(JobExecutor.getInstance(), UIThread.instance()); 中添加如下引用
 * Schedulers.from(threadExecutor)
 * 目的
 * 控制加载线程数量，让任务呈线性执行先进先出
 */
public class JobExecutor implements Executor {
    private static JobExecutor instance = null;
    private final ThreadPoolExecutor threadPoolExecutor;

    private JobExecutor() {
        //1.核心线程数 2.最大线程数 3.设置空闲线程在终止之前等待的时间 4.将时间单位设置为秒 先进先出的队列
        this.threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new JobThreadFactory());
    }

    public static JobExecutor getInstance() {
        if (instance == null) instance = new JobExecutor();
        return instance;
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable);
        }
    }
}