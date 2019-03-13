package com.hcykj.mvpdemo.base;

import android.os.Bundle;

/**
 * @描述: BaseMvpActivity
 * @作者: 宋俊 SongJun
 * @时间: 2016/11/10 12:13
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            mvpPresenter = null;
        }
    }
}
