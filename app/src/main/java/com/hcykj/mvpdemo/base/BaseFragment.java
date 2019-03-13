package com.hcykj.mvpdemo.base;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;
import java.lang.reflect.Field;

/**
 * @描述: Fragment基类
 * @作者: 宋俊 SongJun
 * @时间: 2016/10/11 10:50
 */
public abstract class BaseFragment extends RxFragment {

    protected BaseActivity mActivity;
    private View mView;

    protected abstract int getLayoutId();
    protected void beforeSetView(){}
    protected void initView(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mActivity = (BaseActivity) getActivity();
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
            return mView;
        }
        beforeSetView();
        View view = inflater.inflate(getLayoutId(), container, false);
        mView = view;
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
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
        return mActivity;
    }
}
