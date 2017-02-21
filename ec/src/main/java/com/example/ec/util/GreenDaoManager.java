package com.example.ec.util;

import com.easycode.util.ContextHolder;
import com.example.ec.MyApplication;
import com.example.ec.green.DaoMaster;
import com.example.ec.green.DaoSession;

/**
 * Created by yzz on 2017/2/21.
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private MySQLiteOpenHelper devOpenHelper;


    public GreenDaoManager() {
        //创建一个数据库

        devOpenHelper = new MySQLiteOpenHelper(ContextHolder.getContext(), "greendao-db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    /**
     * 关闭数据连接
     */
    public void close() {
        if (devOpenHelper != null) {
            devOpenHelper.close();
        }
    }
}
