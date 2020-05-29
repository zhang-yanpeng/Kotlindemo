package com.zyp.kotlin.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zyp.kotlin.R;

/**
 * Created by zhangyanpeng on 2020/5/29
 */
public class CircleImageView extends View {
    private Paint mPaint;

    /**
     * 圆心坐标
     */
    private float centerX,centerY;
    /**
     * 原型图片半径
     */
    private int radios;




    public CircleImageView(Context context) {
        this(context,null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int min = Math.min(width, height);
        radios = min/2;
        centerX = radios;
        centerY = radios;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bird);


        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(centerX,centerY,radios,mPaint);
    }

}
