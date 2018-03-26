package com.example.huchenzhang.myutils.zxing;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
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

public class Zxing extends BaseActivity<ActivityZxingBinding> {

    private int REQUEST_CODE_SCAN = 111;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCountView(this,R.layout.activity_zxing);
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
                final String str = data.getStringExtra(Constant.CODED_CONTENT);
                if(StringUtils.WeChatToPay(str)){
                    binding.tv1.setText(String.format("微信%s", str));
                }else if(StringUtils.AlipayToPay(str)){
                    binding.tv1.setText(String.format("支付宝%s", str));
                    toAlipay(str);

                }else{
                    binding.tv1.setText(String.format("请扫描正确付款码%s", str));
                }
            }
        }
    }

    /***
     * 支付宝支付
     * @param str 二维码的生成的字符串
     */
    private void toAlipay(String str){
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(PayHelper.gatewayUrl, PayHelper.app_id, PayHelper.merchant_private_key, PayHelper.format, PayHelper.charset, PayHelper.alipay_public_key, PayHelper.sign_type);
        //创建API对应的request类
        AlipayTradePayRequest request = new AlipayTradePayRequest(); //创建API对应的request类
        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setOutTradeNo(System.currentTimeMillis()+"");
        model.setScene("bar_code");
        model.setAuthCode(str);
        model.setSubject("Iphone6 16G");
        model.setTotalAmount("0.01");
        request.setBizModel(model);
        //通过alipayClient调用API，获得对应的response类
        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println("地址：" + response.getBody());
        } catch (NoClassDefFoundError | AlipayApiException e) {
            e.printStackTrace();
        }
    }


}
