package com.example.ec.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.easycode.util.ToastUtil;
import com.example.ec.R;

public class ScrollerActivity extends AppCompatActivity {

    private Scroller scroller;
    private LinearLayout llContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        Button startScrollby = (Button) findViewById(R.id.start_scrollby);
        Button startScrollto = (Button) findViewById(R.id.start_scrollto);

        llContent = (LinearLayout) findViewById(R.id.ll_content);

        TextView txt = (TextView) findViewById(R.id.txt);
        //初始化Scroller
        scroller = new Scroller(this);
        startScrollby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScrollby();
            }
        });
        startScrollto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScrollto();
            }
        });

    }

    private void startScrollby() {
        llContent.scrollBy(100,0);

    }
    private void startScrollto() {
        llContent.scrollTo(-500,0);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ToastUtil.DebugShow(keyCode+"");
        Log.i("keyCode",keyCode+"");
        Log.i("event",event+"");
        return super.onKeyDown(keyCode, event);
    }
}