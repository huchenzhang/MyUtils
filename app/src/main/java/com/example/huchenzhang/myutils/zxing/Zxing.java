package com.example.huchenzhang.myutils.zxing;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityZxingBinding;
import com.yxp.permission.util.lib.PermissionUtil;
import com.yxp.permission.util.lib.callback.PermissionOriginResultCallBack;

import java.util.List;

/**
 * 掃碼
 * Created by hu on 2018/3/16.
 */

public class Zxing extends BaseActivity {
    private ActivityZxingBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_zxing) ;
        initView();
    }

    private void initView(){
        //點擊掃碼
        binding.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQrCode();
            }
        });
    }

    /** 开始扫码 */
    private void startQrCode() {
        PermissionUtil.getInstance().request(this, new String[]{Manifest.permission.CAMERA}, new PermissionOriginResultCallBack() {
            @Override
            public void onResult(List<com.yxp.permission.util.lib.PermissionInfo> acceptList, List<com.yxp.permission.util.lib.PermissionInfo> rationalList, List<com.yxp.permission.util.lib.PermissionInfo> deniedList) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

        }

    }
}
