package com.hcykj.mvpdemo.mvp;

import com.hcykj.mvpdemo.base.BaseModel;
import com.hcykj.mvpdemo.base.BaseView;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Author: SongJun.
 * CreateDate: 2017/9/27 0027.
 */

public interface LoginCovenant {

    interface View extends BaseView {

        void onLoginSuccess(BaseModel<String> successResult);
        void onLoginFailure(BaseModel<Object> failureResult);

    }
    interface Stores {

        @FormUrlEncoded
        @POST("")
        Observable<BaseModel<String>> login(
                @Query("account") String account,
                @Query("password") String password
        );
    }

    interface Presenter {
        void login(String account, String password);
    }

}
