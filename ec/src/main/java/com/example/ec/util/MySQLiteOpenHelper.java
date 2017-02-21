package com.example.ec.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ec.green.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by yzz on 2017/2/21.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新
//        MigrationHelper.migrate(db,UserBeanDao.class,UserBean1Dao.class,UserBean3Dao.class,SiteDao.class);
    }

}