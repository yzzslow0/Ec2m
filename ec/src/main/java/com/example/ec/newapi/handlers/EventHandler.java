package com.example.ec.newapi.handlers;

import android.view.View;


import com.example.ec.model.DataBindTest;

/**
 * Created by yzz on 2017/3/13.
 */

public class EventHandler {
    private boolean isChange = false;
    private DataBindTest dataBindTest;
    public EventHandler(DataBindTest dataBindTest) {
        this.dataBindTest = dataBindTest;
    }

    public void onClickChange(View view){
        if (isChange){
            dataBindTest.setTest1("再变换1");
            dataBindTest.setTest2("再变换2");
            dataBindTest.setTest3("再变换3");
            dataBindTest.setTest4("再变换4");
            dataBindTest.setTest5("再变换5");
            isChange = false;
            }else {
            dataBindTest.setTest1("变换1");
            dataBindTest.setTest2("变换2");
            dataBindTest.setTest3("变换3");
            dataBindTest.setTest4("变换4");
            dataBindTest.setTest5("变换5");
            isChange = true;
        }
    }
}
