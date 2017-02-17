package com.easycode.retrofit.callback;

import com.easycode.retrofit.model.BaseCallModel;
import com.easycode.util.JsonUtil;
import com.easycode.util.L;
import com.easycode.util.NetUtil;

import rx.Subscriber;

/**
 * Created by yzz on 2016/9/20.
 */
public abstract class BaseSubscriber<T extends BaseCallModel> extends Subscriber<T> {
    public abstract void onFailed(String message);
    protected abstract void onSuccess(String response);
    @Override
    public void onStart() {
        super.onStart();

        L.v("Http is Start");
        if (!NetUtil.isNetworkAvailable()) {
            L.v("BaseSubscriber","无网络");
            onCompleted();
            return;
        }

    }

    @Override
    public void onCompleted() {
        L.v("BaseSubscriber_onCompleted","请求完成");
    }

    @Override
    public void onNext(T response) {
        if (response.getCode()==1){
            L.i("BaseSubscriber_onNext", JsonUtil.toJson(response));
            onSuccess(response.getData()+"");
        }else {
            onFailed( response.getMsg());
        }
    }



    @Override
    public void onError(Throwable e) {
        // todo error somthing
        L.d("BaseSubscriber_ERROR", e.getMessage());
        onFailed(e.getMessage());
        onFinish();
    }

    public void onFinish(){
        L.v("BaseSubscriber_onFinish","请求完成");
    }
}
