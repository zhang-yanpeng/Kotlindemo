package com.river.kotlindemo.utils

/**
 * kotlin 实体类
 * Created by ZhangYanPeng on 2019/2/13.
 */
class Boy( name:String, age:Int, 性格:String):people(name,age,性格) {
    init {
        println("$name 是个男孩儿，今年 $age 岁了，性格 $性格")
    }
}

class girl(name:String, age:Int, 性格:String):people(name,age,性格){
    init {
        println("$name 是个女孩儿，今年 $age 岁了，性格 $性格")
    }
}

class day(){

}

open class people(var name:String,var age:Int,var 性格:String){

}