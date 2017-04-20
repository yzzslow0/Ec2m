package com.easycode.retrofit;

import android.content.Context;

import com.easycode.event.HttpEvent;
import com.easycode.retrofit.cookie.AddCookiesInterceptor;
import com.easycode.retrofit.cookie.SaveCookiesInterceptor;
import com.easycode.retrofit.interceptor.BaseInterceptor;
import com.easycode.retrofit.interceptor.CaheInterceptor;
import com.easycode.retrofit.model.BaseCallModel;
import com.easycode.util.ContextHolder;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by yzz on 2016/8/31.
 */
public class MRetrofit {

//    public static final String IP = "http://172.16.0.200";
    public static final String IP = "http://172.16.0.167";
    private static final int DEFAULT_TIMEOUT = 20;
    private static MRetrofit mInstance;
    private static Map<String,String> headers;



    public static MRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (MRetrofit.class) {
                if (mInstance == null)
                    mInstance = new MRetrofit(ContextHolder.getContext(), headers);
            }
        }
        return mInstance;
    }

    private RetrofitService retrofitService;

    private MRetrofit(Context context,Map<String, String> headers) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .sslSocketFactory(SSLHelper.getSSLCertifcation(context))//为OkHttp对象设置SocketFactory用于双向认证
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(new AddCookiesInterceptor(context))
                .addInterceptor(new SaveCookiesInterceptor(context))
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(new CaheInterceptor())

                .cache(new Cache(new File(ContextHolder.getContext().getCacheDir(),"mReCache"),1024*1024*10))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(IP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//1.X为RxJavaCallAdapterFactory
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }


    public RetrofitService getRetrofitService() {
        return retrofitService;
    }


    /***************************调用*************************************/

    /**
     * EventBus
     * @param url
     * @param params
     */
    public void CallEvnet(String url, Map<String, String> params) {

        getRetrofitService().createCommitResponseBody(url, params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                HttpEvent httpEvent = new HttpEvent();
                httpEvent.setState(1);
                try {
                    httpEvent.setResult(response.body().string() + "");
                } catch (Exception e) {
                    httpEvent.setResult("");
                    e.printStackTrace();
                }

                EventBus.getDefault().post(httpEvent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * 普通调用
     *
     * @param url
     * @param params
     * @return
     */
    public Call<ResponseBody> CallResponse(String url, Map<String, String> params) {

        return getRetrofitService().createCommitResponseBody(url, params);

    }




}
