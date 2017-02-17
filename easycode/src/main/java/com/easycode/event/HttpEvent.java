package com.easycode.event;

/**
 * Created by yzz on 2016/9/1.
 * /**
 * threadMode 表示方法在什么线程执行   (Android更新UI只能在主线程, 所以如果需要操作UI, 需要设置ThreadMode.MainThread)
 * sticky     表示是否是一个粘性事件   (如果你使用postSticky发送一个事件,那么需要设置为true才能接受到事件)
 * priority   优先级                 (如果有多个对象同时订阅了相同的事件, 那么优先级越高,会优先被调用.)
 * */
public class HttpEvent {
    private int state;
    private String result;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
