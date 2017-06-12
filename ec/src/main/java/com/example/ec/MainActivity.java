package com.example.ec;

import android.app.DownloadManager;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.easycode.event.UpdataEvent;
import com.easycode.util.PreferencesUtils;
import com.example.ec.activity.CamaraActivity;
import com.example.ec.activity.CustomScanActivity;
import com.example.ec.activity.DialogActivity;
import com.example.ec.activity.ExpandableItemActivity;
import com.example.ec.activity.FadeInTextActivity;
import com.example.ec.activity.HttpTestActivity;
import com.example.ec.activity.NewMenuActivity;
import com.example.ec.activity.RxJavaActivity;
import com.example.ec.activity.TabActivity;
import com.example.ec.activity.TimePickerActivity;
import com.example.ec.activity.XmlActivity;
import com.example.ec.anim.AnimActivity;
import com.example.ec.greendao.GreenDAOActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.btn_newapi)
    Button btnNewapi;
    @BindView(R.id.btn_greendao)
    Button btnGreendao;
    @BindView(R.id.btn_update_app)
    Button btnUpdateApp;
    @BindView(R.id.btn_scan)
    Button btnScan;
    @BindView(R.id.btn_xml)
    Button btnXml;
    @BindView(R.id.btn_tab)
    Button btnTab;
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    @BindView(R.id.btn_lottie)
    Button btnLottie;
    @BindView(R.id.btn_rxjava)
    Button btnRxJava;
    @BindView(R.id.btn_xmljx)
    Button btnXmljx;
    @BindView(R.id.btn_time)
    Button btnTime;
    @BindView(R.id.btn_recycle_ex)
    Button btnRecycleEx;
    @BindView(R.id.btn_camara)
    Button btnCamara;


    private Map<String, String> params = new HashMap<>();
    public static final String DOWNLOAD_ID = "download_id";
    private DownloadChangeObserver downloadObserver;
    private long lastDownloadId = 0;
    //"content://downloads/my_downloads"必须这样写不可更改
    public static final Uri CONTENT_URI = Uri.parse("content://downloads/my_downloads");
    private MaterialDialog materialDialog;
    //    private String NetUrl = "http://gdown.baidu.com/data/wisegame/8d5889f722f640c8/weixin_800.apk";
    private String NetUrl = "http://172.16.0.167/download/city.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @OnClick({R.id.button, R.id.btn_newapi, R.id.btn_greendao,
            R.id.btn_update_app, R.id.btn_scan, R.id.btn_xml,
            R.id.btn_tab, R.id.btn_dialog, R.id.btn_lottie, R.id.btn_rxjava,
            R.id.btn_xmljx, R.id.btn_time, R.id.btn_recycle_ex, R.id.btn_camara,R.id.btn_fadeIntext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(this, HttpTestActivity.class));
                break;
            case R.id.btn_newapi:
                startActivity(new Intent(this, NewMenuActivity.class));
                break;
            case R.id.btn_greendao:
                startActivity(new Intent(this, GreenDAOActivity.class));
                break;
            case R.id.btn_update_app:
                update();
                break;
            case R.id.btn_scan:
                scan();
                break;
            case R.id.btn_xml:
                startActivity(new Intent(this, XmlActivity.class));
                break;
            case R.id.btn_tab:
                startActivity(new Intent(this, TabActivity.class));
                break;
            case R.id.btn_dialog:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.btn_lottie:
                startActivity(new Intent(this, AnimActivity.class));
                break;
            case R.id.btn_rxjava:
                startActivity(new Intent(this, RxJavaActivity.class));
                break;
            case R.id.btn_xmljx:
//                startActivity(new Intent(this,XmlJxActivity.class));
                break;
            case R.id.btn_time:
                startActivity(new Intent(this, TimePickerActivity.class));
                break;
            case R.id.btn_recycle_ex:
                startActivity(new Intent(this, ExpandableItemActivity.class));
                break;
            case R.id.btn_camara:
                startActivity(new Intent(this, CamaraActivity.class));
                break;
            case R.id.btn_fadeIntext:
                startActivity(new Intent(this, FadeInTextActivity.class));
                break;
        }
    }

    private void scan() {

        new IntentIntegrator(this)
                .setPrompt("喝喝喝")
                .setBeepEnabled(false)
                .setOrientationLocked(false)
                .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
                .initiateScan(); // 初始化扫描
    }

    @Override
// 通过 onActivityResult的方法获取 扫描回来的 值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "内容为空", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "扫描成功:" + intentResult.getContents(), Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
//                String ScanResult = intentResult.getContents();
//
//                params.clear();
//                params.put("code",ScanResult);
//                MRetrofit.getInstance().getRetrofitService().scanexecutePost("qrcodeLogin",params)
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
////                                List<TestModel> t = JsonUtil.fromJson(response, new TypeToken<List<TestModel>>() {}.getType());
//////                              gson.fromJson( response,  new TypeToken<List<TestModel>>() {  }.getType());
////                                for (int i = 0; i < t.size(); i++) {
////                                    L.d(t.get(i).getAge() + "");
////                                }
//                            }
//                        });
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void update() {

        materialDialog = null;
        materialDialog = new MaterialDialog.Builder(MainActivity.this)
                .title("版本升级")
                .content("正在下载安装包，请稍候")
                .progress(false, 100, false)
                .cancelable(false)
                .show();

        //1.得到下载对象
        DownloadManager dowanloadmanager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        //2.创建下载请求对象，并且把下载的地址放进去
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(NetUrl));
        //3.给下载的文件指定路径
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "weixin.apk");
        //4.设置显示在文件下载Notification（通知栏）中显示的文字。6.0的手机Description不显示
        request.setTitle("更新中...点击取消");
//        request.setDescription("1.新增扫一扫\\r\\n2.首页添加热门\\r\\n3.优化和修复\\t\\t\\t\\t\\t");
        //5更改服务器返回的minetype为android包类型
        request.setMimeType("application/vnd.android.package-archive");
        //6.设置在什么连接状态下执行下载操作
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        //7. 设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner();
        //8. 设置为可见和可管理
        request.setVisibleInDownloadsUi(true);

        lastDownloadId = dowanloadmanager.enqueue(request);
        //9.保存id到缓存
        PreferencesUtils.putLong(this, DOWNLOAD_ID, lastDownloadId);
        //10.采用内容观察者模式实现进度
        downloadObserver = new DownloadChangeObserver(null);
        getContentResolver().registerContentObserver(CONTENT_URI, true, downloadObserver);
    }


    //用于显示下载进度
    class DownloadChangeObserver extends ContentObserver {

        public DownloadChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(lastDownloadId);
            DownloadManager dManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            final Cursor cursor = dManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                final int totalColumn = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                final int currentColumn = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
                int totalSize = cursor.getInt(totalColumn);
                int currentSize = cursor.getInt(currentColumn);
                float percent = (float) currentSize / (float) totalSize;
                int progress = Math.round(percent * 100);
                materialDialog.setProgress(progress);
                if (progress == 100) {
                    materialDialog.dismiss();
                }
            }

        }


    }

    @Subscribe
    public void onCloseUpdateDialog(UpdataEvent event) {
        if (materialDialog != null)
            materialDialog.dismiss();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadObserver != null)
            getContentResolver().unregisterContentObserver(downloadObserver);
        EventBus.getDefault().unregister(this);
    }
}
