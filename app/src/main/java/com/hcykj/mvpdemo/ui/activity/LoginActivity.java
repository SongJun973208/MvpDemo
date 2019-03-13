package com.hcykj.mvpdemo.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.hcykj.mvpdemo.base.BaseModel;
import com.hcykj.mvpdemo.base.BaseMvpActivity;
import com.hcykj.mvpdemo.mvp.LoginCovenant;
import com.hcykj.mvpdemo.mvp.LoginPresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginCovenant.View {
    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView(Bundle var1) {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onLoginSuccess(BaseModel<String> successResult) {

    }

    @Override
    public void onLoginFailure(BaseModel<Object> failureResult) {

    }
}
