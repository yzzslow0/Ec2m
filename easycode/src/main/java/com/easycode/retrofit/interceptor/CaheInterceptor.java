package com.easycode.retrofit.interceptor;

import com.easycode.util.L;
import com.easycode.util.NetUtil;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yzz on 2016/11/23.
 */

public class CaheInterceptor implements Interceptor {
    protected  Response response = null;

    public CaheInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();

        if (NetUtil.isNetworkAvailable()) {

            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            try {
                 response = chain.proceed(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int maxAge = 60 * 5; // read from cache for 60 minute
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();

        } else {
            L.e("Tamic", " 无网络 加载缓存");

            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            try {
                response = chain.proceed(request);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int maxStale = 60 * 5;
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }


    }

}
