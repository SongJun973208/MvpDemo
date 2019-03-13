package com.hcykj.mvpdemo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * @描述: Activity基类
 * @作者: 宋俊 SongJun
 * @时间: 2016/10/11 10:48
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    protected Activity mContext;
    protected View mContentView;

    @LayoutRes
    protected abstract int getLayout();

    protected void beforeSetView() { }

    protected abstract void initView(Bundle var1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        beforeSetView();
        mContentView = View.inflate(mContext, getLayout(), null);
        setContentView(mContentView);
        initView(savedInstanceState);
    }

    //BaseView 中的方法
    public void showToast(String text) {
        ToastUtils.showShort(text);
    }

    public void showLoading(String loading) {
        //显示对话框组件
    }

    public void hideLoading() {
        //隐藏对话框组件
    }

    public Context getContext() {
        return this;
    }

    public LifecycleTransformer bindUntilEvent(){
        return bindToLifecycle();
    }
}
