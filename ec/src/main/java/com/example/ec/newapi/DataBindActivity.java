package com.example.ec.newapi;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ec.R;
import com.example.ec.databinding.ActivityDataBindBinding;
import com.example.ec.model.DataBindTest;
import com.example.ec.newapi.handlers.EventHandler;

public class DataBindActivity extends AppCompatActivity {

    private DataBindTest dataBindTest;
    private EventHandler eventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_bind);
        ActivityDataBindBinding bindBinding = DataBindingUtil.setContentView(this,R.layout.activity_data_bind);
         dataBindTest = new DataBindTest();
        bindBinding.setTest(dataBindTest);
        eventHandler = new EventHandler(dataBindTest);
        bindBinding.setDianiji(eventHandler);
    }


}
