package com.example.huchenzhang.myutils.myDataBinding;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityDataBindingBinding;

/**
 * activity
 * Created by hu on 2017/5/23.
 */

public class MyDataBindingActivity extends BaseActivity<ActivityDataBindingBinding>{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCountView(this, R.layout.activity_data_binding);
        User user = new User("huchenzhang","24");
        binding.setUser(user);
    }
}
