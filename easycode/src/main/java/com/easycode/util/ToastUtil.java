package com.easycode.util;

import android.widget.Toast;

import com.easycode.BaseApplication;
import com.easycode.BuildConfig;

/**
 * Created by yzz on 2016/11/15.
 */

public class ToastUtil {
    private static boolean isDebug = BuildConfig.DEBUG;
//    private static boolean isDebug =true;
    private static Toast toast;
    private static final int SHORT = 0x00;
    private static final int LONG = 0x01;
    private static final int NOTSHOW = 0x02;
    private static final int SHOW = 0x03;


    private static void Show(String content, int Short, int Show) {
        if (isDebug ) {
            if (Short == SHORT) {
                toast = Toast.makeText(ContextHolder.getContext(), content + "", Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(ContextHolder.getContext(), content + "", Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }




    public static void showLong(String content) {
        if (toast != null)
            toast.cancel();

        Show(content, LONG, SHOW);

    }

    public static void showShort(String content) {
        if (toast != null)
            toast.cancel();
        Show(content, SHORT, NOTSHOW);
    }


    public static void DebugShow(String content) {
        if (toast != null)
            toast.cancel();
        Show(content, SHORT, NOTSHOW);
    }




}
