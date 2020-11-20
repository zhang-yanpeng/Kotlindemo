package com.yanpeng.firstcodekotlin.view

/**
 * 粒子  实体类
 * Created by zhangyanpeng on 2020/11/9
 */
class Partacle(var x: Float,//X坐标
               var y: Float,//Y坐标
               var radius: Float,//半径
               var speed: Float,//速度
               var alpha: Int,//透明度
               var maxOffset: Int = 300,//最大移动距离
               var offset: Int,//移动距离
               var angle: Double//粒子角度
)