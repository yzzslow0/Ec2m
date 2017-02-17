package com.easycode.util;

import android.content.Context;

/**
 * Created by yzz on 2016/11/17.
 *
 * 各地获取context
 * 使用方法 ：在 application调用 initial () 方法
 */

public class ContextHolder {

    static Context ApplicationContext;
    public static void initial(Context context) {
        ApplicationContext = context;
    }
    public static Context getContext() {
        return ApplicationContext;
    }
}
