package com.yanpeng.code.code

import kotlinx.coroutines.*

/**
 * 直接 将所有程序执行在协程中
 */
fun main() = runBlocking<Unit> {
    launch {
        doWorld()
    }
    println("Hello,")

    //开启 10 万个协程
    var startTime = System.currentTimeMillis()
    repeat(100000) {
        launch {
            println(it)
            if (it == 99999) {
                //耗时 515
                println("创建1000000个协程执行时间：${System.currentTimeMillis() - startTime}")
            }
        }
    }

    //对比上述协程
//    var threadStartTime = System.currentTimeMillis()
//    repeat(100000) {
//        Thread(Runnable {
//            println(it)
//            if (it == 99999) {
//                //耗时  6144
//                println("创建100000个线程执行时间：${System.currentTimeMillis() - threadStartTime}")
//            }
//        }).start()
//    }
}

/**
 * susped 挂起函数
 */
private suspend fun doWorld() {
    withContext(Dispatchers.IO){
        delay(1000)
        println("World")
    }
}