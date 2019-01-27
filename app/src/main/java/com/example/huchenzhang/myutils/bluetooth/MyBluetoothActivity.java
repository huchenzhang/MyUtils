package com.example.huchenzhang.myutils.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityMyBluetoothBinding;
import com.example.huchenzhang.myutils.utils.Constants;
import net.vidageek.mirror.dsl.Mirror;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 蓝牙相关学习
 * Created by hu on 2019/1/3.
 */

public class MyBluetoothActivity extends BaseActivity<ActivityMyBluetoothBinding>{
    private static final int REQUEST_ENABLE = 30;
    private static final int REQUEST_DISCOVERABLE = 31;
    private BluetoothAdapter bluetoothAdapter;//获取本地蓝牙配置器
    private BluetoothLeScanner bluetoothLeScanner;//用于搜索蓝牙设备的对象
    private List<BluetoothDevice> listDevices = new ArrayList<>();//保存搜索出来的蓝牙设备集合

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCountView(this, R.layout.activity_my_bluetooth);

        //判断系统是否需要动态获取权限。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }

        bluetoothAdapter =  BluetoothAdapter.getDefaultAdapter();
        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();

        binding.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBluetooth();
            }
        });
        binding.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBluetooth();
            }
        });
        binding.bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv1.setText(getBluetoothMacAddress());
            }
        });
    }

    private String getBlueAddress(){
//        String str = android.provider.Settings.Secure.getString(Application.getAppContext().getContentResolver(), "bluetooth_address");
//        return "名称:"+ bluetoothAdapter.getName() + "\n地址:" + str;
        return "";
    }

    private String getBluetoothMacAddress() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Object bluetoothManagerService = new Mirror().on(bluetoothAdapter).get().field("mService");
        if (bluetoothManagerService == null) {
            return null;
        }
        Object address = new Mirror().on(bluetoothManagerService).invoke().method("getAddress").withoutArgs();
        if (address != null && address instanceof String) {
            return (String) address;
        } else {
            return null;
        }
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

    /**
     * 搜索蓝牙设备
     */
    private void searchBluetooth(){
        bluetoothAdapter.getBluetoothLeScanner().startScan(scanCallback);
//        bluetoothLeScanner.startScan(scanCallback);
    }

    /**
     * 搜索蓝牙设备结果的回调
     */
    private ScanCallback scanCallback =  new ScanCallback(){
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            String name = new String(result.getScanRecord().getBytes());
            String name2 = "";
            try {
                name2 = new String(result.getScanRecord().getBytes(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            final BluetoothDevice bluetoothDevice = result.getDevice();
            final BleAdvertisedData devicName = BleUtil.parseAdertisedData(result.getScanRecord().getBytes());
            boolean isSame = true;
            if (bluetoothDevice != null){
                for (BluetoothDevice bd : listDevices){//设备列表中是否存在一样的蓝牙设备
                    if (bd.getAddress().equalsIgnoreCase(bluetoothDevice.getAddress())){
                        isSame = false;
                    }
                }
                //存在一样的设备不添加到集合中
                if (isSame){
                    listDevices.add(bluetoothDevice);
                    Log.e(Constants.HU_LOG,"蓝牙设备 ：" + bluetoothDevice.getName() + " name:" + devicName.getName() + " \nAddress: " + bluetoothDevice.getAddress());
                }
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };
}
