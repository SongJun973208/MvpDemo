package com.hcykj.mvpdemo.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @描述: BasePresenter
 * @作者: 宋俊 SongJun
 * @时间: 2016/11/10 12:13
 */
public class BasePresenter<V extends BaseView, S> {
    public V mvpView;
    protected S appStores;
    private List<Callback> subscribers;
    /**
     * 绑定View
     * @param mvpView
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        subscribers = new ArrayList<>();
        appStores = AppClient.getAppRetrofit().create(getClz());
    }

    /**
     * 获取S的Class
     * @return
     */
    private Class<S> getClz() {
        return ((Class<S>) (((ParameterizedType) (this.getClass()
                .getGenericSuperclass())).getActualTypeArguments()[1]));
    }

    /**
     * 解绑
     */
    public void detachView() {
        onUnSubscription();
        if(subscribers != null){
            subscribers.clear();
            subscribers = null;
        }
        mvpView = null;
        appStores = null;
    }

    /**
     * RxJava取消注册，以避免内存泄露
     */
    public void onUnSubscription() {
        if(subscribers != null && subscribers.size() > 0){
            for (Callback c : subscribers){
                c.cancelRequest();
            }
        }
    }

    /**
     * RxJava注册
     * @param observable
     * @param subscriber
     */
    public void addSubscription(Observable observable, Callback subscriber) {
        LifecycleTransformer lifecycleTransformer = bindUntilEvent();
        if (lifecycleTransformer != null) {
            observable.compose(lifecycleTransformer);
        }
        subscribers.add(subscriber);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public LifecycleTransformer bindUntilEvent() {
        return mvpView.bindUntilEvent();
    }

}
