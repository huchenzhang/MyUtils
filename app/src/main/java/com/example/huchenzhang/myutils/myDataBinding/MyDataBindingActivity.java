package com.example.huchenzhang.myutils.myDataBinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.DataBindingActivityBinding;

/**
 * activity
 * Created by hu on 2017/5/23.
 */

public class MyDataBindingActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.data_binding_activity);
        User user = new User("huchenzhang","24");
        binding.setUser(user);
    }
}
