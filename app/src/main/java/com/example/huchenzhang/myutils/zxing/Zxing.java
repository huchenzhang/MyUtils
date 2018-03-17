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
import com.example.huchenzhang.myutils.utils.HuToast;
import com.example.huchenzhang.myutils.utils.StringUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;
import java.util.List;

/**
 * 掃碼
 * Created by hu on 2018/3/16.
 */

public class Zxing extends BaseActivity {
    private ActivityZxingBinding binding;
    private int REQUEST_CODE_SCAN = 111;
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
                checkPermission();
            }
        });
    }

    /**
     * 检查权限
     */
    private void checkPermission(){
        AndPermission.with(this)
                .permission(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        startQrCode();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        HuToast.show("没权限",Zxing.this);
                    }
                })
                .start();
    }

    /** 开始扫码 */
    private void startQrCode() {
        Intent intent = new Intent(this, CaptureActivity.class);
        ZxingConfig config = new ZxingConfig();
        config.setPlayBeep(true);
        config.setShake(true);
        intent.putExtra(Constant.INTENT_ZXING_CONFIG,config);

        startActivityForResult(intent,REQUEST_CODE_SCAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if(requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK){
            if(data != null){
                String str = data.getStringExtra(Constant.CODED_CONTENT);
                if(StringUtils.WeChatToPay(str)){
                    binding.tv1.setText(String.format("微信%s", str));
                }else if(StringUtils.AlipayToPay(str)){
                    binding.tv1.setText(String.format("支付宝%s", str));
                }else{
                    binding.tv1.setText(String.format("请扫描正确付款码%s", str));
                }


            }
        }
    }

}
