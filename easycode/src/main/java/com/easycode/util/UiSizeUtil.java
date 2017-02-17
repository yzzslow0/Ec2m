package com.easycode.util;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * Created by yzz on 2016/6/21.
 */
public class UiSizeUtil {

    public static int MATCH_PARENT = -1;
    public static int WRAP_CONTENT = -2;
    public static int NO_CHANGE = -3;

    /**
     *
     * @param view
     * @param width (-1 MatchParent,-2 WrapContent，other）
     * @param height(-1 MatchParent,-2 WrapContent，other）
     */
    public static void LayoutChange(View view,int width,int height){

//    1、获取控件所在的布局
        LayoutParams para = view.getLayoutParams();//获取按钮的布局
//    2、修改布局中的height，width属性
        if (width==MATCH_PARENT){
            para.width=LayoutParams.MATCH_PARENT;
        }else if(width==WRAP_CONTENT) {
            para.width=LayoutParams.WRAP_CONTENT;//修改宽度
        }else if(width ==NO_CHANGE){

        }else {
            para.width = width;
        }

        if (height==MATCH_PARENT){
            para.height=LayoutParams.MATCH_PARENT;
        }else if(height==WRAP_CONTENT) {
            para.height=LayoutParams.WRAP_CONTENT;//修改宽度
        }else if(height ==NO_CHANGE){

        }else {
            para.height=height;//修改高度
        }
//    3、重新设置修改后的布局给控件
        view.setLayoutParams(para); //设置修改后的布局。
    }

}
