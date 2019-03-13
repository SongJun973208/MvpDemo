package com.hcykj.mvpdemo.base;

import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DefaultObserver;
import retrofit2.HttpException;

/**
 * @描述: 访问网络完毕回调
 * @作者: 宋俊 SongJun
 * @时间: 2016/11/11 14:32
 */
public abstract class Callback<M> extends DefaultObserver<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String msg);

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String msg;
        int code = 888;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            code = httpException.code();
            Log.e("onError-code", code + "");
            if (code >= 500 && code < 600) {
                msg = "网络不给力";
                code = 801;
            }else if (code >= 400 && code < 500) {
                msg = "无法连接到服务器";
                code = 801;
            }else{
                code = 803;
                msg = "网络不给力";
            }
        } else if (e instanceof UnknownHostException) {
            code = 803;
            msg = "网络不给力";
        } else if (e instanceof SocketTimeoutException) {
            code = 804;
            msg = "连接超时";
        } else if (e instanceof ConnectException) {
            code = 805;
            msg = "连接服务器失败";
        } else if (e instanceof IllegalStateException) {
            code = 806;
            msg = "解析错误";
        } else if (e instanceof JsonSyntaxException) {
            code = 806;
            msg = "解析错误";
        } else if (e instanceof IOException) {
            code = 807;
            msg = "无法连接到网络";
        } else {
            msg = "未知错误";
        }
        if (!NetworkUtils.isConnected()) {
            code = 800;
            msg = "无法连接到网络";
        }
        onFailure(code, msg);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onStart() {
        if (!NetworkUtils.isConnected()) {
            onFailure(800, "无法连接到网络");
            cancel();
        }
    }

    public void cancelRequest(){
        cancel();
    }
}
