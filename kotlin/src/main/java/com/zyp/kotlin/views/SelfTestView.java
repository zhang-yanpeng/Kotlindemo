package com.zyp.kotlin.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by zhangyanpeng on 2020/5/14
 */
public class SelfTestView extends View {

    private Paint mPaint = new Paint();

    //  View 中心
    private int centerX, centerY;

    //  半径
    private int circleR;

    public SelfTestView(Context context) {
        this(context, null);
    }

    public SelfTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelfTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getWidth();
        int height = getHeight();

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawaCircle(canvas);
        drawLins(canvas);
        drawRect(canvas);
        drawPoints(canvas);
        drawOval(canvas);
        drawRoundRect(canvas);
        drawArc(canvas);

        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#3344ff00"));
        mPaint.setStrokeWidth(2f);
//      字号大小
        mPaint.setTextSize(60);
        canvas.drawText("测试", 800, 100, mPaint);
    }

    private void drawArc(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(100, 100, 800, 800, 20, 80, true, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20f);
        canvas.drawArc(10, 10, 100, 100, -20, 80, false, mPaint);
    }

    /**
     * 绘制矩形
     *
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5F);
        canvas.drawRect(10, 10, 300, 300, mPaint);
    }

    private void drawRoundRect(Canvas canvas) {
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5F);
        canvas.drawRoundRect(80, 80, 700, 700, 50, 100, mPaint);
    }

    /**
     * 绘制线
     *
     * @param canvas
     */
    private void drawLins(Canvas canvas) {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10F);
        canvas.drawLine(10, 400, 300, 400, mPaint);

        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
        canvas.drawLines(points, mPaint);
    }

    /**
     * 绘制圆
     *
     * @param canvas
     */
    private void drawaCircle(Canvas canvas) {
        //      设置模式
//        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStyle(Paint.Style.STROKE);
//      设置线条宽度  单位像素
        mPaint.setStrokeWidth(30f);
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//      扛锯齿
        mPaint.setAntiAlias(true);
//      设置圆的颜色
//        mPaint.setColor();
        mPaint.setARGB(40, 100, 200, 0);
//      绘制一个圆
        canvas.drawCircle(300, 300, 200, mPaint);
    }

    private void drawPoints(Canvas canvas) {
//      单个点
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(20f);
        canvas.drawPoint(60, 60, mPaint);

//      多个点
        float[] points = new float[]{100, 30, 30, 100, 100, 100, 30, 30};
        canvas.drawPoints(points, mPaint);
    }

    private void drawOval(Canvas canvas) {
        mPaint.setARGB(80, 80, 100, 50);
        canvas.drawOval(100, 10, 500, 800, mPaint);

        canvas.drawOval(0, 0, 100, 800, mPaint);

//        RectF rectF = new RectF();
    }
}
