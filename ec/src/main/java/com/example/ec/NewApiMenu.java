package com.example.ec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ec.newapi.ToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewApiMenu extends AppCompatActivity {

    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.button9)
    Button button9;
    @BindView(R.id.button10)
    Button button10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_api_menu);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.button8, R.id.button9, R.id.button10})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button8:
                startActivity(new Intent(this, ToolBarActivity.class));
                break;
            case R.id.button9:
//                startActivity(new Intent(this, CoordinatorActivity.class));
                break;
            case R.id.button10:
                break;
        }
    }
}
