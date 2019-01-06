package com.example.huchenzhang.myutils.updateFile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import com.autonavi.amap.mapcore.FileUtil;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityUpdateBinding;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 文件同步
 * Created by hu on 2018/12/25.
 */

public class UpdateActivity extends BaseActivity<ActivityUpdateBinding>{
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;
    private FTP ftp;
    private Thread downloadThread;
    private String localPath = Environment.getExternalStorageDirectory().getPath()+"/hu/";
    private String localPathConfig = Environment.getExternalStorageDirectory().getPath()+"/hu/config/";
    private String localPathList = Environment.getExternalStorageDirectory().getPath()+"/hu/list/";
    private String localPathResource = Environment.getExternalStorageDirectory().getPath()+"/hu/resource/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCountView(this, R.layout.activity_update);
        getPermission();
        binding.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv1.setText(getLastUpdateTime(localPathConfig));
            }
        });
        binding.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FileUtil.deleteFile(new File(path));//删除目录及目录下的文件
                deleteFilesByDirectory(new File(localPathConfig));//删除目录下文件
            }
        });
        ftp = new FTP("172.18.30.86","hu","hu");
        downloadThread = new DownloadThread();
        binding.bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    downloadThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getPermission(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }


    /**
     * 获取文件最后更新时间
     * @param path 文件路径
     * @return 返回最后更新时间
     */
    private String getLastUpdateTime(String path){
        File f = new File(path);
        //Date time = new Date(f.lastModified());//两种方法都可以
        if(!f.exists()){
            f.mkdirs();
        }
        long time = f.lastModified();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(time);
    }

    /**
     * sd卡是否存在
     * @return
     */
    private boolean ExistSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 删除文件夹下文件
     * @param directory 文件夹路径
     */
    private void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    private class DownloadThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                ftp.openConnect();
                ftp.downloadFile1("\\config\\",localPathConfig);
                ftp.downloadFile1("\\list\\",localPathList);
                ftp.downloadFile1("\\resource\\",localPathResource);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
