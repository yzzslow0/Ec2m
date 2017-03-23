package com.example.ec.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.easycode.event.HttpEvent;
import com.easycode.util.L;
import com.example.ec.R;
import com.example.ec.newapi.DataBindActivity;
import com.example.ec.newapi.RecyclerViewActivity;
import com.example.ec.newapi.ToolBarActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewMenuActivity extends AppCompatActivity {

    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.button9)
    Button button9;
    @BindView(R.id.button10)
    Button button10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_menu);
        ButterKnife.bind(this);

    }
    @Subscribe
    public void onHttpResult(HttpEvent event) {
        L.v("测试evnet", event.getResult());
    }
    @OnClick({R.id.button8, R.id.button9, R.id.button10})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button8:
                startActivity(new Intent(this, ToolBarActivity.class));
                break;
            case R.id.button9:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.button10:
                startActivity(new Intent(this, DataBindActivity.class));
                break;
        }
    }
}
