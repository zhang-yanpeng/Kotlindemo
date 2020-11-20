package com.yanpeng.firstcodekotlin.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import java.util.*
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin
import kotlin.system.measureTimeMillis

/**
 * Created by zhangyanpeng on 2020/11/9
 */
class DimpleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    //   定义粒子集合
    private var practileList = mutableListOf<Partacle>()

    //  画笔
    private val mPaint = Paint()

    // 中心点
    var centerX = 0f
    var centerY = 0f

    //  为了测量圆上某一点的坐标
    var pathMeasure = PathMeasure()

    //  圆上某一点的坐标
    var pos = FloatArray(2)

    //  圆上的切线
    var tan = FloatArray(2)

    //粒子向外的扩展动画
    var anim = ValueAnimator.ofFloat(0f, 1f)

    init {
        anim.duration = 2000
        anim.repeatCount = -1
        anim.interpolator = LinearInterpolator()
        anim.addUpdateListener {
//          更改位置
            updatePartacle()
//          重新绘制
            invalidate()
        }
    }

    //  借助path 绘制一个看不见的圆
    var path = Path()

    //    添加粒子进集合  生成随机数对象
    var random = Random()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = Color.BLUE
        mPaint.isAntiAlias = true

        var time = measureTimeMillis {
            practileList.forEach { p1 ->
                mPaint.alpha=p1.alpha
                canvas?.drawCircle(p1.x, p1.y, p1.radius, mPaint)
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2.toFloat()
        centerY = h / 2.toFloat()
//      添加一个圆
        path.addCircle(centerX, centerY, 280f, Path.Direction.CCW)

        pathMeasure.setPath(path, false)
        var nextX = 0f
        var nextY = 0f
        var speed = 0
//
        for (i in 0..300) {
            pathMeasure.getPosTan(i / 500f, pos, tan)
            nextX = pos[0] + random.nextInt(6) - 3f
            nextY = pos[1] + random.nextInt(6) - 3f
            speed = random.nextInt(10) + 5
//          获取 当前点位置基于中心点的角度
            var angle = acos((pos[0] - centerX) / 280f).toDouble()
            practileList.add(Partacle(nextX, nextY, 2f, speed.toFloat(), 100, 0, 300, angle))
        }
//      启动动画
        anim.start()
    }

    /**
     * 更新动画
     */
    fun updatePartacle() {
        practileList.forEach {
//            if (it.offset > it.maxOffset) {
//                it.offset = 0
//                it.speed = (random.nextInt(10) + 5).toFloat()
//            }
//
//            it.alpha = ((1f - it.offset / it.maxOffset) * 225f).toInt()
//            it.x = (centerX + cos(it.angle) * (280f + it.offset)).toFloat()
//
//            if (it.y > centerY) {
////              中心右侧
//                it.y = (sin(it.angle) * (280f + it.offset) + centerY).toFloat()
//            } else {
////              中心左侧
//                it.y = (centerY - sin(it.angle) * (280f + it.offset)).toFloat()
//            }
//            it.offset +=it.speed.toInt()

            if (it.y - centerY > it.maxOffset) {
                it.y = centerY
//              随机生成X
                var run = Random()
                it.x = run.nextInt((centerX * 2).toInt()).toFloat()
                it.speed = (run.nextInt(10) + 5).toFloat()
            }
            it.y += it.speed
        }
    }
}