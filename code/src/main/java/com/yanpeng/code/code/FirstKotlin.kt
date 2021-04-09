package com.yanpeng.code.code

/**
 * kotlin 文件 用于体验kotlin
 */
fun main() {

//    var str = ""
//    var startTime = System.currentTimeMillis()
//    repeat(50000) {
//        str += "str"
//    }
//    println("执行时间：${System.currentTimeMillis() - startTime}")
//
//    var sBuffer = StringBuffer()
//    var startTime1 = System.currentTimeMillis()
//    repeat(50000) {
//        sBuffer.append("str")
//    }
//    println("执行时间: ${System.currentTimeMillis() - startTime1} ")
//
//    var sBuilder = StringBuilder()
//    var startTime2 = System.currentTimeMillis()
//    repeat(50000) {
//        sBuilder.append("str")
//    }
//    println("执行时间: ${System.currentTimeMillis() - startTime2} ")

    //数组反转
    getResultArr(intArrayOf(3, 5, 12, 3, 4, 5, 123, 5, 23, 12, 41, 0, 3))

    getResult(intArrayOf(1, 3, 4, 5, 6, 7), 12)

}

fun getResultArr(array: IntArray): IntArray {
    var tem = 0
    var start = 0
    var end = array.size - 1
    while (start < end) {
        tem = array[start]
        array[start] = array[end]
        array[end] = tem
        start++
        end--
    }
    return array
}

/**
 * 解法1
 * 新建一个数组
 * 遍历赋值
 */
fun getResult(array: IntArray, s: Int) {
    //新建一个与输入数组长度相等的数组
    var result = Array(array.size) { it }
    for (i in 0..array.size - 1) {
        // result[i+s] = array[i]
        result[(i + s) % (result.size)] = array[i]
    }
}

fun getResult() {
    //这次，我们不创建新的数组，为了避免数据交换过程中导致的数据覆盖，我们采用
    //创建临时变量
    var tem =0



}


fun inc(num: Int) {
    val num = 2
    if (num > 0) {
        val num = 3
    }
    println("inc  " + num)
}