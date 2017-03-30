package com.example.ec.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.easycode.event.HttpEvent;
import com.easycode.retrofit.MRetrofit;
import com.easycode.retrofit.TransformUtils;
import com.easycode.retrofit.callback.BaseObserver;
import com.easycode.retrofit.model.BaseCallModel;
import com.easycode.util.JsonUtil;
import com.easycode.util.L;
import com.easycode.util.NetUtil;
import com.example.ec.MainActivity;
import com.example.ec.R;
import com.example.ec.model.TestModel;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpTestActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Map<String, String> params = new HashMap<>();

    @BindView(R.id.Rx_test)
    Button RxTest;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_download)
    Button btn_download;
    @BindView(R.id.btn_normal)
    Button btnNormal;
    @BindView(R.id.btn_event)
    Button btnEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.Rx_test, R.id.btn_update, R.id.btn_download, R.id.btn_normal, R.id.btn_event})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Rx_test:

                MRetrofit.getInstance().getRetrofitService().executePost("Retrofit2_222",params)
                        .compose(TransformUtils.<BaseCallModel>all_io())
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<BaseCallModel>() {
                            @Override
                            public void onFailed(String message) {
                                L.d(message + "");
                            }

                            @Override
                            protected void onSuccess(String response) {
                                L.d(response + "");
                                List<TestModel> t = JsonUtil.fromJson(response, new TypeToken<List<TestModel>>() {}.getType());
//                              gson.fromJson( response,  new TypeToken<List<TestModel>>() {  }.getType());
                                for (int i = 0; i < t.size(); i++) {
                                    L.d(t.get(i).getAge() + "");
                                }
                            }
//                            @Override
//                            public void onFailed(String message) {
//
//                            }
//
//                            @Override
//                            protected void onSuccess(String response) {
//
//                            }
                        });

                break;
            case R.id.btn_update:



                List<String> paths = new ArrayList<>();
                paths.add(Environment.getExternalStorageDirectory().getPath() + "/1.jpg");
                paths.add(Environment.getExternalStorageDirectory().getPath() + "/2.jpg");
                paths.add(Environment.getExternalStorageDirectory().getPath() + "/3.jpg");
                Map<String, RequestBody> photos = new HashMap<>();
                if (paths.size() > 0) {
                    for (int i = 0; i < paths.size(); i++) {
                        photos.put("photos" + i + "\"; filename=\"icon.jpg", RequestBody.create(MediaType.parse("multipart/form-data"), new File(paths.get(i))));
                    }
                }
                Call<ResponseBody> mcall = MRetrofit.getInstance().getRetrofitService().upload3("hehe", photos);
                mcall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


                break;
            case R.id.btn_download:

                Call<ResponseBody> mcall2 = MRetrofit.getInstance().getRetrofitService().downloadFile("/download/123.jpg");
                mcall2.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            L.d(TAG, "server contacted and has file");
                            boolean writtenToDisk = NetUtil.writeResponseBodyToDisk(response.body());
                            L.d(TAG, "file download was a success? " + writtenToDisk);
                        } else {
                            L.d(TAG, "server contact failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        L.e(TAG, "error");
                    }
                });

                break;
            case R.id.btn_normal:

//                MRetrofit.getInstance().getRetrofitService().createRx2("Retrofit2_222", params)
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new BaseSubscriber<BaseCallModel>() {
//                            @Override
//                            public void onFailed(String message) {
//
//                            }
//                            @Override
//                            protected void onSuccess(String response) {
//
//                            }
//
//                        });


                //普通callback
                MRetrofit.getInstance().CallResponse("oktest", params).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            L.v("测试response", response.body().string());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

                break;
            case R.id.btn_event:


                //EventBus 回调
                MRetrofit.getInstance().CallEvnet("Retrofit2", params);

                break;
        }
    }


    @Subscribe
    public void onHttpResult(HttpEvent event) {
        L.v("测试evnet", event.getResult());
    }


}
