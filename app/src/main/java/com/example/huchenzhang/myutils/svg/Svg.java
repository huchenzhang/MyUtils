package com.example.huchenzhang.myutils.svg;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.SvgActivityBinding;

/**
 * 测试
 * Created by hu_cz on 2017/11/28 16:12.
 */

public class Svg extends BaseActivity {
	private SvgActivityBinding binding;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this,R.layout.svg_activity);
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
