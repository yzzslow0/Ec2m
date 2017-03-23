package com.example.ec.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.ec.BR;


/**
 * Created by yzz on 2017/3/13.
 */

public class DataBindTest extends BaseObservable{
    private String Test1;
    private String Test2;
    private String Test3;
    private String Test4;
    private String Test5;

    public DataBindTest() {
//        "测试1","测试2","测试3","测试4","测试5"
        setTest1("测试1");
        setTest2("测试2");
        setTest3("测试3");
        setTest4("测试4");
        setTest5("测试5");
    }
    @Bindable
    public String getTest1() {
        return Test1;
    }

    public void setTest1(String test1) {
        Test1 = test1;
        notifyPropertyChanged(BR.test1);
    }
    @Bindable
    public String getTest2() {
        return Test2;
    }

    public void setTest2(String test2) {
        Test2 = test2;
        notifyPropertyChanged(BR.test2);
    }
    @Bindable
    public String getTest3() {
        return Test3;
    }

    public void setTest3(String test3) {
        Test3 = test3;
        notifyPropertyChanged(BR.test3);
    }
    @Bindable
    public String getTest4() {
        return Test4;
    }

    public void setTest4(String test4) {
        Test4 = test4;
        notifyPropertyChanged(BR.test4);
    }
    @Bindable
    public String getTest5() {
        return Test5;
    }

    public void setTest5(String test5) {
        Test5 = test5;
        notifyPropertyChanged(BR.test5);

    }
}
