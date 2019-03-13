package com.hcykj.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @描述: BaseMvpActivity
 * @作者: 宋俊 SongJun
 * @时间: 2016/11/10 12:13
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mvpPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解绑
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            mvpPresenter=null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
