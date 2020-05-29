package com.zyp.kotlin.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.zyp.kotlin.R;


/**
 * 双色 条纹 view/可只使用单个颜色
 * Created by zhangyanpeng on 2020/5/28
 */
public class AnnieTwoLineView extends View {

    /**
     * 控件宽高
     */
    private int height, width;

    /**
     * 背景颜色
     */
    @SuppressLint("ResourceAsColor")
    private int mainColor;

    /**
     * 条纹颜色
     */
    @SuppressLint("ResourceAsColor")
    private int lineColor;

    /**
     * 条纹宽度  px
     */
    private float lineWidth;

    /**
     * 圆角
     */
    private float radios;

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Path path = new Path();

    public AnnieTwoLineView(Context context) {
        this(context, null);
    }

    public AnnieTwoLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnnieTwoLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnnieTwoLineView);
        lineWidth = typedArray.getDimension(R.styleable.AnnieTwoLineView_lineWidth, 0f);
        mainColor = typedArray.getColor(R.styleable.AnnieTwoLineView_mainColor, getResources().getColor(R.color.colorWhite));
        lineColor = typedArray.getColor(R.styleable.AnnieTwoLineView_lineColor, getResources().getColor(R.color.colorAccent));
        radios = typedArray.getDimension(R.styleable.AnnieTwoLineView_rectRadios, 0);
//        radios = typedArray.getDimension(R.styleable.AnnieTwoLineView_r)
        Log.i("test", "：" + lineWidth + "," + mainColor + "," + lineColor);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//      测量宽度和高度
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
//      方便生效
        setMeasuredDimension(width, height);
    }

    /**
     * 绘制一个背景 矩形
     */
    private void drawMain() {
        mPaint.setColor(mainColor);
        mPaint.setStyle(Paint.Style.FILL);
        RectF rect = new RectF();
        rect.left = 0;
        rect.top = 0;
        rect.right = width;
        rect.bottom = height;
        if (radios == 0) {
            mCanvas.drawRect(rect, mPaint);
        } else {
            mCanvas.drawRoundRect(rect, 20, 20, mPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        //离屏缓存
        drawMain();
        if (lineWidth > 0) {
            drwaLine();
        }
        int saveLayer = canvas.saveLayer(0, 0, width, height, mPaint);
        drawArcRect();
        canvas.restoreToCount(saveLayer);
    }

    private void drwaLine() {
        mPaint.setColor(lineColor);
        mPaint.setStyle(Paint.Style.FILL);

        boolean flag = true;
        int i = 0;
        while (flag) {
            RectF rectF = new RectF();
            rectF.left = lineWidth * (2 * i + 1);
            rectF.right = lineWidth * (2 * i + 2);
            rectF.top = 0;
            rectF.bottom = height;
            mCanvas.drawRect(rectF, mPaint);
            if (lineWidth * (2 * i + 1) > width) {
                break;
            }
            i++;
        }
//
    }

    /**
     * 绘制下边界为弧线的矩形
     */
    private float multiple = 0.7f;

    private void drawArcRect() {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        path.lineTo(0, height * multiple);
        path.quadTo(width / 2, height + 20, width, height * multiple);
        path.lineTo(width, 0);
        path.close();
//        path.setFillType(Path.FillType.INVERSE_WINDING);

        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mPaint.setXfermode(xfermode);
        mCanvas.drawPath(path, mPaint);
        mPaint.setXfermode(null);
    }
}
