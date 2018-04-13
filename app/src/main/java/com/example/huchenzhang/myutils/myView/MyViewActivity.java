package com.example.huchenzhang.myutils.myView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityMyImageviewBinding;

/**
 * 显示自定义view
 * Created by hu on 2018/4/12.
 */

public class MyViewActivity extends BaseActivity<ActivityMyImageviewBinding>{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCountView(this, R.layout.activity_my_imageview);
    }
}
