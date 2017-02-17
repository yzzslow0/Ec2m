package com.example.ec.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ec.R;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomScanActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener{ // 实现相关接口
    // 添加一个按钮用来控制闪光灯，同时添加两个按钮表示其他功能，先用Toast表示

    @BindView(R.id.btn_switch) Button swichLight;
    @BindView(R.id.btn_hint1) Button hint1Show;
    @BindView(R.id.btn_hint2) Button hint2Show;
    @BindView(R.id.dbv_custom) DecoratedBarcodeView mDBV;
    private Map<String, String> params = new HashMap<>();
    private CaptureManager captureManager;
    private boolean isLightOn = false;

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scan);
        ButterKnife.bind(this);

        mDBV.setTorchListener(this);

        // 如果没有闪光灯功能，就去掉相关按钮
        if(!hasFlash()) {
            swichLight.setVisibility(View.GONE);
        }

        //重要代码，初始化捕获
        captureManager = new CaptureManager(this,mDBV);
        captureManager.initializeFromIntent(getIntent(),savedInstanceState);
        captureManager.decode();
    }

    // torch 手电筒
    @Override
    public void onTorchOn() {
        Toast.makeText(this,"torch on", Toast.LENGTH_LONG).show();
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        Toast.makeText(this,"torch off",Toast.LENGTH_LONG).show();
        isLightOn = false;
    }

    // 判断是否有闪光灯功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    // 点击切换闪光灯
    @OnClick({R.id.btn_switch,R.id.btn_hint1,R.id.btn_hint2})
    public void swichLight(View view){
        switch (view.getId()) {
            case R.id.btn_switch:
                if(isLightOn){
                    mDBV.setTorchOff();
                }else{
                    mDBV.setTorchOn();
                }
                break;
            case R.id.btn_hint1:
//                params.clear();
//                params.put("userName","system");
//                params.put("password","system");
//                MRetrofit.getInstance().getRetrofitService().scanexecutePost("login",params)
//                        .compose(TransformUtils.<BaseCallModel>defaultSchedulers())
//                        .subscribe(new BaseSubscriber<BaseCallModel>() {
//                            @Override
//                            public void onFailed(String message) {
//                                L.d(message + "");
//                            }
//
//                            @Override
//                            protected void onSuccess(String response) {
//                                L.d(response + "");
//                                ToastUtil.showShort("RESPONSE="+response);
////                                List<TestModel> t = JsonUtil.fromJson(response, new TypeToken<List<TestModel>>() {}.getType());
//////                              gson.fromJson( response,  new TypeToken<List<TestModel>>() {  }.getType());
////                                for (int i = 0; i < t.size(); i++) {
////                                    L.d(t.get(i).getAge() + "");
////                                }
//                            }
//                        });

                break;
            case R.id.btn_hint2:


                break;
        }

    }


}