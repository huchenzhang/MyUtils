package com.example.huchenzhang.myutils.share;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ShareActivityBinding;
import com.example.huchenzhang.myutils.utils.HuToast;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * 第三方分享
 * Created by hu_cz on 2017/12/11 14:54.
 */

public class Share extends BaseActivity{
	private ShareActivityBinding binding;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.share_activity);
		initView();
	}
	
	/**
	 * 如果大于android6.0 需要检查权限
	 * 否则直接分享
	 */
	private void initPermission(){
		if(Build.VERSION.SDK_INT >= 23){
			String[] mPermissionList = new String[]{
					Manifest.permission.WRITE_EXTERNAL_STORAGE,
					Manifest.permission.ACCESS_FINE_LOCATION,
					Manifest.permission.CALL_PHONE,
					Manifest.permission.READ_LOGS,
					Manifest.permission.READ_PHONE_STATE,
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.SET_DEBUG_APP,
					Manifest.permission.SYSTEM_ALERT_WINDOW,
					Manifest.permission.GET_ACCOUNTS,
					Manifest.permission.WRITE_APN_SETTINGS};
			ActivityCompat.requestPermissions(this,mPermissionList,123);
		}else{
//			ShareText();
			String string = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513052918761&di=32035432f40edb639d148603c85581bc&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Fface%2Fdd7f1b3ebdd9451fcb5d0844e25b6bae87bfc5ea.jpg";
//			ShareImage(string,string);
			ShareWeb(string);
		}
		
	}
	
	/**
	 * 权限申请后的回调
	 * @param requestCode
	 * @param permissions
	 * @param grantResults
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		//如果权限通过，就去分享
		if(123 == requestCode){
			ShareText();
		}else{
			HuToast.show("请通过所有权限",this);
		}
	}
	
	/** 初始化view */
	private void initView(){
		//分享按钮点击事件
		binding.btShare.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//先检查权限
				initPermission();
			}
		});
		
		//登陆按钮点击事件
		binding.btLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				login();
			}
		});
	}
	
	/** 分享 */
	private void ShareText(){
		//不带面板，直接分享
//		new ShareAction(this)
//				.setPlatform(SHARE_MEDIA.QQ)//传入平台
//				.withText("hello")//分享内容
//				.setCallback(shareListener)//回调监听器
//				.share();
		
		//带面板，选择平台进行分享
		new ShareAction(this)
				.withText("hello")
				.setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
				.setCallback(shareListener)
				.open();
		
		
	}
	
	/***
	 * 分享图片
	 * @param image 图片地址
	 * @param thumb 缩略图地址
	 *  UMImage image = new UMImage(ShareActivity.this, "imageurl");//网络图片
	 *	UMImage image = new UMImage(ShareActivity.this, file);//本地文件
	 *	UMImage image = new UMImage(ShareActivity.this, R.drawable.xxx);//资源文件
	 *	UMImage image = new UMImage(ShareActivity.this, bitmap);//bitmap文件
	 *	UMImage image = new UMImage(ShareActivity.this, byte[]);//字节流
	 */
	private void ShareImage(String image,String thumb){
//		UMImage pic = new UMImage(this,image);
//		pic.setThumb(new UMImage(this,thumb));
//		new ShareAction(this)
//				.withMedia(pic)
//				.setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
//				.setCallback(shareListener)
//				.withText("蕾姆")
//				.open();
		
			UMImage pic = new UMImage(this,R.drawable.a);
			pic.setThumb(new UMImage(this,R.drawable.a));
			new ShareAction(this)
					.withMedia(pic)
					.setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
					.setCallback(shareListener)
					.withText("")
					.open();
	}
	
	/***
	 * 分享链接
	 * @param thumb_img 缩略图
	 */
	private void ShareWeb(String thumb_img){
		UMImage thumb = new UMImage(this,thumb_img);
		UMWeb web = new UMWeb("https://www.baidu.com/");
		web.setThumb(thumb);
		web.setDescription("超可爱的蕾姆图片");
		web.setTitle("蕾姆图片");
		new ShareAction(this)
				.withMedia(web)
				.setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
				.setCallback(shareListener)
				.open();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/** 登陆 */
	private void login(){
	
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		UMShareAPI.get(this).release();
	}
	
	
	private UMShareListener shareListener = new UMShareListener() {
		/**
		 * @descrption 分享开始的回调
		 * @param platform 平台类型
		 */
		@Override
		public void onStart(SHARE_MEDIA platform) {
		}
		
		/**
		 * @descrption 分享成功的回调
		 * @param platform 平台类型
		 */
		@Override
		public void onResult(SHARE_MEDIA platform) {
			Toast.makeText(Share.this,"成功了",Toast.LENGTH_LONG).show();
		}
		
		/**
		 * @descrption 分享失败的回调
		 * @param platform 平台类型
		 * @param t 错误原因
		 */
		@Override
		public void onError(SHARE_MEDIA platform, Throwable t) {
			Toast.makeText(Share.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
		}
		
		/**
		 * @descrption 分享取消的回调
		 * @param platform 平台类型
		 */
		@Override
		public void onCancel(SHARE_MEDIA platform) {
			Toast.makeText(Share.this,"取消了",Toast.LENGTH_LONG).show();
			
		}
	};
	
}
