package com.example.ec;

import android.database.sqlite.SQLiteDatabase;

import com.easycode.BaseApplication;
import com.example.ec.green.DaoMaster;
import com.example.ec.green.DaoSession;
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
        GreenDaoManager.getInstance();
    }

//    public static MyApplication getInstances(){
//        return instances;
//    }
}
