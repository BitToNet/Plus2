package com.example.plus2.httpdemo;



import static com.example.plus2.httpdemo.ApiConstant.BASE_URL;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2019/6/18  14:41
 * desc   : 基类
 */
public class RetrofitManager {

    private static final int CONNEC_TIMEOUT = 25;
    private static final int READ_TIMEOUT   = 20;
    private static final int WRITE_TIMEOUT  = 20;

    private ApiService apiServer;

    private static RetrofitManager mHttpClient;

    // 懒汉式单例模式
    public static RetrofitManager getInstance() {
        if (mHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mHttpClient == null) {
                    mHttpClient = new RetrofitManager();
                }
            }
        }
        return mHttpClient;
    }

    private RetrofitManager() {
        // 创建OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                // 超时设置
                .connectTimeout(CONNEC_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);

        // 添加各种插入器
        addInterceptor(builder);

        // 创建并获取Retrofit实例
        apiServer = new Retrofit.Builder().baseUrl(
                BASE_URL)// Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException
                                          .client(builder.build())
                                          .addConverterFactory(GsonConverterFactory.create())
                                          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                          .build()
                                          .create(ApiService.class);
    }

    private void addInterceptor(OkHttpClient.Builder builder) {
        // 新建log拦截器
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor(
                message -> Log.i("HttpManager", "[RetrofitManager] " + message));
        // 日志显示级别
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        // OkHttp进行添加拦截器loggingInterceptor
        builder.addInterceptor(logger);
    }

    public ApiService getApiService() {
        return apiServer;
    }

    public ObservableTransformer threadTransformer() {
        return observable -> observable.subscribeOn(Schedulers.io())
                                       .unsubscribeOn(Schedulers.io())
                                       .observeOn(AndroidSchedulers.mainThread());
    }
}
