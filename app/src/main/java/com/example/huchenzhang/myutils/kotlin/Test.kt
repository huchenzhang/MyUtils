package com.example.huchenzhang.myutils.kotlin

/**
 * kotlin语法练习
 * Created by hu_cz on 2017/11/1 16:37.
 */

/** 打印 **/
fun main(args: Array<String>){
    print("Hello word!")
}

/** Greeter类里面有一个greet方法 **/
class Greeter(val name:String){
    fun greet(){
        print("Hello ,$name")
    }
}

/** 不需要new Greeter对象 **/
fun main2(args: Array<String>){
    Greeter(args[0]).greet()
}




