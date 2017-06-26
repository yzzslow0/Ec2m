package com.example.ec;


import com.easycode.BaseApplication;
import com.easycode.retrofit.MRetrofit;
import com.easycode.util.ContextHolder;
import com.example.ec.util.GreenDaoManager;

/**
 * Created by yzz on 2017/2/21.
 */

public class MyApplication extends BaseApplication {
    public static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();

        instances = this;

        ContextHolder.initial(this);
        GreenDaoManager.getInstance();
        MRetrofit.IP ="http://172.16.0.167";
    }

//    public static MyApplication getInstances(){
//        return instances;
//    }
}
