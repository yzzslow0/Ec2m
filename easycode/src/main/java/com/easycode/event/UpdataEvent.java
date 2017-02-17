package com.easycode.event;

/**
 * Created by yzz on 2017/2/3.
 */

public class UpdataEvent {
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
