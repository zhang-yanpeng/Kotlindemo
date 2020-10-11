package com.yanpeng.firstcodekotlin.code

class Test {
    fun main(){
        var n:Int =3
        var m:Int =5
        var result = getMaxNum(n,m)
        print("获取两数之间的大数："+result)
    }
    fun getMaxNum(n:Int,m:Int):Int{
        if (n>m){
            return n
        }else{
            return m
        }
    }
}