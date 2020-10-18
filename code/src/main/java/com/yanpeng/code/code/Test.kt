package com.yanpeng.code.code

class Test{

    /**
     * 返回2个int值里最大的那个
     * 一般写法
     */
    fun getMaxNum(n:Int,m:Int):Int{
        if (n>m){
            return n
        }else{
            return m
        }
    }

    /**
     * 上述代码的简化写法
     */
    fun getMaxNum2(n:Int,m:Int)= if (n>m) n else m


    /**
     * 传入动物类型
     * 打印出动物名字
     */
    fun getAnimalName(type:String) = when(type){
        "Dog"->{
            "Diff is a ${type}"
        }
        "Cat"->{
            "Tom is a ${type}"
        }
        else->{
            "这是什么鬼"
        }
    }
}