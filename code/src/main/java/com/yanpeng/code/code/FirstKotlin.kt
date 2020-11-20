package com.yanpeng.code.code

import java.util.*

/**
 * kotlin 文件 用于体验kotlin
 */
fun main() {
    //1.变量 的声明
//    var s:Byte =1
//    val s1 =1
//    var a:Int =1
//    var b:Short=3
//    var d =2L
//    var e = 4F
//    var f = true
////    getMax()
//
////  2.循环语句
//    var test = Test()
//    var n =10
//    var m =100
//    var maxNum = test.getMaxNum(n, m)
////  认识kt中的if  else
//    println(" maxNum: ${maxNum}")
////  认识kotlin中的when  相当于java中的seitch
//    println("${test.getAnimalName("Cat")}")
//    println("${test.getAnimalName("Dog")}")
//
//
////  认识kotlin中的循环
////  一个重要的概念 区间
//    println("遍历区间，包含起始点，也包含结束点")
//    var r:IntRange = 0..5   //代表着 从0 到5  包含0 也包含5
//    for (i in r){
//        println(i)
//    }
//
//    println("遍历前开后闭区间，即包含起始点，不包含结束点")
//    var r1 = 0 until 5 //代表着区间，从0 到5 包含0 不包含5  可以和数学概念中的区间
//    for (i in r1){
//        println(i)
//    }
////  能不能每次多走几步呢？
//    println("循环：每次走2步")
//    for (i in 0..10 step 2){
//        println(i)
//    }
//
//    println("能不能打印降序区间呢？当然可以!  借助downTo")
//    var r2 = 10 downTo 5
//    for (i in r2){
//        println(i)
//    }

    var uu = UUID.randomUUID().toString()
    println("UUID:${uu}")
    inc(1)
}

/**
 * 打印出各种数据类型的最大值，最小值
 */
fun getMax(){
    println("byte 的最大值：${Byte.MAX_VALUE}  和它的最小值：${Byte.MIN_VALUE}")
    println("int 的最大值：${Int.MAX_VALUE}   和它的最小值：${Int.MIN_VALUE}")
    println("short 的最大值：${Short.MAX_VALUE}   和它的最小值：${Short.MIN_VALUE}")
    println("long 的最大值：${Long.MAX_VALUE}   和它的最小值：${Long.MIN_VALUE}")
    println("float 的最大值：${Float.MAX_VALUE}   和它的最小值：${Float.MIN_VALUE}")
    println("double 的最大值：${Double.MAX_VALUE}   和它的最小值：${Double.MIN_VALUE}")
}

fun  inc(num:Int){
    val num =2
    if (num>0){
        val num =3
    }
    println("inc  "+num)
}