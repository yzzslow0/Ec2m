package com.example.ec.activity.model;

/**
 * Created by yzz on 2017/5/9.
 */

public class RecycleEvent {
    public static int REFRESH = 0;
    public static int LEVEL_1= 0x01;
    private int state;
    private String result;
    private int position;

    public RecycleEvent(int state, String result, int position) {
        this.state = state;
        this.result = result;
        this.position = position;
    }


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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
