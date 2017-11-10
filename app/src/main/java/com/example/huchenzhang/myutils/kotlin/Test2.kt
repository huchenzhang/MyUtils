package com.example.huchenzhang.myutils.kotlin

/**
 * 语法练习
 * Created by hu_cz on 2017/11/5 17:24.
 */

/***
 * 定义变量
 * 可变变量定义：var 关键字
 * var <标识符> ： <类型> = <初始值>
 */
var a : Int = 1

/***
 * 定义常量
 * 不可变变量：val<标识符>：<类型> = 初始值
 */
val b : Int = 2

/***
 * 常量和变量都可以没有初始值，但是在引用前必须初始化
 * 编译器支持自动判断类型，即声明时可不可以不指定类型，有编译器判断
 * 系统自动推断变量类型
 */
val c = 3

/** 如果不在声明时初始化，必须提供变量类型 **/
//val d : Int
//d = 1



/**
 * 方法默认作用域为包下
 * Int 参数，返回值 Int
 */
fun test1(a : Int, b : Int):Int{
    return a + b
}

/** 表达式作为函数体，返回类型自动判断 **/
fun test2(a:Int , b: Int) = a + b

/** 指定返回值 **/
public fun test3(a: Int,b: Int):Int = a + b

/** 无返回值的函数，类似java的void **/
fun test4(a: Int,b: Int) : Unit{
}

/** 无返回类型的全局方法 **/
public fun test5(a: Int,b: Int){
}

/**
 * vararg可边长的参数函数，
 * 就是说v可以是一个int值，也可以是int集合，
 * for循环里不需要指定对象类型 **/
fun test6(vararg v:Int){
    for (vt in v){
        print("  test6 = " + vt)
    }
}


fun main(args: Array<String>){
    //带返回值，返回int
    print("test1 = " + test1(1,2))
    print("\n")
    //带返回值，不指定返回类型，返回类型自动判断
    print("test2 = " + test2(1,2))
    print("\n")
    //带返回值，返回int，return写法不同
    print("test3 = " + test3(1,2))
    print("\n")
    //无返回值，指定返回Unit，Unit用法类似于void
    print("test4 = " + test4(1,2))
    print("\n")
    //无返回值，不指定返回类型Unit，可以直接不写return
    print("test5 = " + test5(1,2))
    print("\n")
    //vararg可边长参数,无返回值
    test6(1,2,3,4)
    //lambda表达式使用，输出3
    val test7:(Int,Int) -> Int = {x , y -> x + y}
    print("test7(lambda) = " + test7(1,2))
    print("\n")

}

