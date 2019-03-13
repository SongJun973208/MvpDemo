package com.hcykj.mvpdemo.base;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @描述: BaseView
 * @作者: 宋俊 SongJun
 * @时间: 2016/11/10 12:13
 */
public interface BaseView {

    //基础方法自己可以扩展
    void showToast(String text);
    void showLoading(String loading);
    void hideLoading();
    Context getContext();
    LifecycleTransformer bindUntilEvent();
}
