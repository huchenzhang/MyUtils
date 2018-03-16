package com.example.huchenzhang.myutils.kotlin

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.example.huchenzhang.myutils.R
import kotlinx.android.synthetic.main.activity_kotlin_three.*

/**
 * Kotlin练习3
 * Created by hu on 2018/3/16.
 */

class Test3 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_three)
        tv_1.text = "你好"
        tv_1.visibility = View.INVISIBLE
        tv_1.setOnClickListener {  }
    }

}
