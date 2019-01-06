package com.example.huchenzhang.myutils.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityMyBluetoothBinding;

/**
 * 蓝牙相关学习
 * Created by hu on 2019/1/3.
 */

public class MyBluetoothActivity extends BaseActivity<ActivityMyBluetoothBinding>{
    private static final int REQUEST_ENABLE = 30;
    private static final int REQUEST_DISCOVERABLE = 31;
    private BluetoothAdapter bluetoothAdapter;//获取本地蓝牙配置器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCountView(this, R.layout.activity_my_bluetooth);

        bluetoothAdapter =  BluetoothAdapter.getDefaultAdapter();

        binding.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBluetooth();
            }
        });
    }

    /**
     * 打开蓝牙
     */
    private void openBluetooth(){
        //不做提示强行打开
        bluetoothAdapter.enable();
        //弹出对话框提示用户是否打开
//       Intent intent1 = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//       startActivityForResult(intent1,REQUEST_ENABLE);
//       //使设备能够被搜索到
//        Intent intent2 = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//        startActivityForResult(intent2,REQUEST_ENABLE);
    }
}
