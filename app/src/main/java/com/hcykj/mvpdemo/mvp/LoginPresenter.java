package com.hcykj.mvpdemo.mvp;

import android.provider.SyncStateContract;

import com.blankj.utilcode.util.StringUtils;
import com.hcykj.mvpdemo.base.BaseModel;
import com.hcykj.mvpdemo.base.BasePresenter;
import com.hcykj.mvpdemo.base.Callback;

/**
 * Author: SongJun.
 * CreateDate: 2017/9/27 0027.
 */

public class LoginPresenter extends BasePresenter<LoginCovenant.View, LoginCovenant.Stores>
        implements LoginCovenant.Presenter {

    public LoginPresenter(LoginCovenant.View view) {
        attachView(view);
    }

    @Override
    public void login(String account, String password) {
        mvpView.showLoading("加载中");
        addSubscription(appStores.login(account, password),
                new Callback<BaseModel<String>>() {
                    @Override
                    public void onSuccess(BaseModel<String> model) {
                        mvpView.hideLoading();
                        mvpView.onLoginSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.hideLoading();
                        mvpView.onLoginFailure(new BaseModel<>(false, code, msg));
                    }
                });
    }
}
