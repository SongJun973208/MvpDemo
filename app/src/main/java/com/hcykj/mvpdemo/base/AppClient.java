package com.hcykj.mvpdemo.base;

import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.GsonBuilder;
import com.hcykj.mvpdemo.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @描述: 访问网络构造
 * @作者: 宋俊 SongJun
 * @时间: 2016/11/11 14:33
 */
public class AppClient {
    //加载等待时间
    public final static int DEFAULT_TIMEOUT = 40;
    public static Retrofit mAppRetrofit;
    private static OkHttpClient okHttpClient;

    public static Retrofit getAppRetrofit() {
        if (mAppRetrofit == null) {
            mAppRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
                            .create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return mAppRetrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug MyLog 模式
                builder.addInterceptor(loggingInterceptor);
            }
            File httpCacheDirectory = new File(AppApplication.getInstance().getExternalCacheDir(), "responses");
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(httpCacheDirectory, cacheSize);
            builder.cache(cache);
            builder.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    //缓存拦截器
    static Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();
            Request request = chain.request();
            if (!NetworkUtils.isConnected()) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (!NetworkUtils.isConnected()) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-xcached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

}
