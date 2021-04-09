package com.yanpeng.code.algorithm

import java.util.*

var arr = intArrayOf(0, 0, 0)

fun main() {
//    getResultThird(13)
//    println("1元:${resultArr[0]}张  5元:${resultArr[1]}张 7元:${resultArr[2]}张，合计${resultArr[0] + resultArr[1] * 5 + resultArr[2] * 7}")

//    var s = getPesult(4000)
//    println("有 $s 种方式")

    var arr = intArrayOf(2, 7, 4, 1, 8, 1)
    println("最后剩余石头:${getLastStone(arr)}")
}

/**
 * 递推（从前向后）
 */
fun getResult1(n: Int): Int {
    when (n) {
        0 -> {
            return 0
        }
        1 -> {
            return 1
        }
        2 -> {
            return 2
        }
        else -> {
            var a = 1;
            var b = 2;
            var c = 0;
            for (i in 3..n) {
                c = a + b
                a = b
                b = c
            }
            return c
        }
    }
}

/**
 * 递归(从后向前)
 * 超时
 */
fun getPesult(n: Int): Int {
    when (n) {
        0 -> {
            return 0
        }
        1 -> {
            return 1
        }
        2 -> {
            return 2
        }
        else -> {
            return getPesult(n - 1) + getPesult(n - 2)
        }
    }
    //采用map 缓存数据
}

/**
 * 3种硬币
 * 1，5，7
 */
var resultArr = intArrayOf(0, 0, 0)
fun getResultThird(n: Int) {
    resultArr[2] += n / 7
    when (n % 7) {
        1, 2 -> {
            resultArr[0] += n % 7
        }
        3, 4 -> {
            if (n > 7) {
                resultArr[2] -= 1
                resultArr[1] += (n % 7 + 7) / 5
                resultArr[0] += (n % 7 + 7) % 5
            } else {
                resultArr[0] += n % 7
            }
        }
        5, 6 -> {
            resultArr[1] += 1
            resultArr[0] += n % 7 - 5
        }
    }
}

fun getLastStone(array: IntArray): Int {
    var queue = PriorityQueue<Int> { a, b -> b - a }
    array.forEach {
        queue.offer(it)
    }
    while (queue.size > 1) {
        var a = queue.poll()
        var b = queue.poll()
        if (a > b) {
            queue.offer(a - b)
        }
    }
    if (queue.isEmpty()) {
        return 0
    } else {
        return queue.poll()
    }
}