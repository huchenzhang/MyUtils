package com.example.huchenzhang.myutils.svg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivitySvgBinding;

/**
 * 测试
 * Created by hu_cz on 2017/11/28 16:12.
 */

public class Svg extends BaseActivity<ActivitySvgBinding> {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCountView(this,R.layout.activity_svg);
		initGif();
	}

	/**
	 * 加载gif图片
	 */
	private void initGif(){
		Glide
				.with(this)
				.load(R.mipmap.gif)
				.into(binding.ivGif);
	}
}
