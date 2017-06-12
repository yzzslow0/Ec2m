package com.easycode;

import android.app.Application;


/**
 * Created by yzz on 2016/11/15.
 */

public class BaseApplication extends Application {


    /**
     * 代码分割 >64k
     */
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        MultiDex.install(newBase);
//        super.attachBaseContext(newBase);
//    }
    @Override
    public void onCreate() {
        super.onCreate();


    }



}
