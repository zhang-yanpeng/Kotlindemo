package com.yanpeng.code.view

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup

class FlowLayout(context: Context, attributeSet: AttributeSet?) : ViewGroup(context, attributeSet) {

    constructor(context: Context) : this(context, null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //测量子view
        //遍历子view 的元素
        for (i in 0..childCount - 1) {
            //测量每个子view
            var chilrView = getChildAt(i)
            var childlp = chilrView.layoutParams
            //layoutParmas 转换为MeasureSpec
            var chileWidthSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childlp.width)
            var chileHeightSpec = getChildMeasureSpec(heightMeasureSpec, paddingLeft + paddingRight, childlp.height)
            measureChild(chilrView, chileWidthSpec, chileHeightSpec)
        }


        //测量自身
//        setMeasuredDimension()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }


    fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp as Float, Resources.getSystem().displayMetrics) as Int
    }

}