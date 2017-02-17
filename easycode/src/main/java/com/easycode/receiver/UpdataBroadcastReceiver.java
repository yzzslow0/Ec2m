package com.easycode.receiver;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.easycode.event.UpdataEvent;
import com.easycode.util.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * Created by yzz on 2017/2/3.
 */

public class UpdataBroadcastReceiver extends BroadcastReceiver {

    @SuppressLint("NewApi")
    public void onReceive(Context context, Intent intent) {
        long downLoadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        long cacheDownLoadId =  PreferencesUtils.getLong(context,"download_id");

        if(DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.getAction())){
            DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            long downloadId = PreferencesUtils.getLong(context,"download_id");
            downloader.remove(downloadId);
            //eventbus 关闭dialog
            UpdataEvent updataEvent = new UpdataEvent();
            updataEvent.setResult("close dialog");
            updataEvent.setState(0);
            EventBus.getDefault().post(updataEvent);
        }else if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction()) && cacheDownLoadId == downLoadId) {
            try {
                Intent install = new Intent(Intent.ACTION_VIEW);
                File apkFile = queryDownloadedApk(context);
                install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(install);
            }catch (Exception e){

            }
        }
    }

    //通过downLoadId查询下载的apk，解决6.0以后安装的问题
    public static File queryDownloadedApk(Context context) {
        File targetApkFile = null;
        DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = PreferencesUtils.getLong(context,"download_id");
        if (downloadId != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            Cursor cur = downloader.query(query);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(uriString)) {
                        targetApkFile = new File(Uri.parse(uriString).getPath());
                    }
                }
                cur.close();
            }
        }
        return targetApkFile;
    }
}