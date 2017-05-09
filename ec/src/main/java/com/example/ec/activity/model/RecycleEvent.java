package com.example.ec.activity.model;

/**
 * Created by yzz on 2017/5/9.
 */

public class RecycleEvent {
    public static int REFRESH = 0;
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
