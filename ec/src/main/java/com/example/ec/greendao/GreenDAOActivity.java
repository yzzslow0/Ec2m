package com.example.ec.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.ec.MyApplication;
import com.example.ec.R;
import com.example.ec.green.GreenTestDao;
import com.example.ec.model.GreenTest;
import com.example.ec.util.GreenDaoManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDAOActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.activity_green_dao)
    RelativeLayout activityGreenDao;
    private GreenTestDao greenTestDao;
    private GreenTest greenTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.editText, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editText:
                break;
            case R.id.button2:
                greenTestDao = GreenDaoManager.getInstance().getSession().getGreenTestDao();
                greenTest = new GreenTest(Long.valueOf(editText.getText().toString()) , "呵呵", "123456", "11111111");
                greenTestDao.insert(greenTest);//添加一个


                break;
        }
    }
}
