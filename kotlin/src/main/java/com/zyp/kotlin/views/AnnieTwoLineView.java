package com.zyp.kotlin.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zyp.kotlin.R;


/**
 * 双色 条纹 view/可只使用单个颜色
 * Created by zhangyanpeng on 2020/5/28
 */
public class AnnieTwoLineView extends View {

    private Context context;
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
        this.context = context;
    }

    public AnnieTwoLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnnieTwoLineView);
        lineWidth = typedArray.getDimension(R.styleable.AnnieTwoLineView_lineWidth, 0f);
        mainColor = typedArray.getColor(R.styleable.AnnieTwoLineView_mainColor, getResources().getColor(R.color.colorWhite));
        lineColor = typedArray.getColor(R.styleable.AnnieTwoLineView_lineColor, getResources().getColor(R.color.colorAccent));
        radios = typedArray.getDimension(R.styleable.AnnieTwoLineView_rectRadios, 0);
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
//      设置颜色
        mPaint.setColor(mainColor);
//      设置填充样式 为填满
        mPaint.setStyle(Paint.Style.FILL);
//      规定矩形相对于当前View 上，下，左，右的距离
        RectF rect = new RectF();
        rect.left = 0;
        rect.top = 0;
        rect.right = width;
        rect.bottom = height;
        if (radios == 0f) {
//          无圆角矩形
            mCanvas.drawRect(rect, mPaint);
        } else {
//          带圆角矩形
            mCanvas.drawRoundRect(rect, radios, radios, mPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;

        //      图像保存
        canvas.save();
        drawArcRect();

        drawMain();
        if (lineWidth > 0) {
            drwaLine();
        }

//      图像恢复
        canvas.restore();
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
    }

    /**
     * 绘制下边界为弧线的矩形
     */
    private float multiple = 0.7f;

    /**
     * 真裁剪
     */
    private void drawArcRect() {
        //从当前位置到目标点(x,y) 用直线连起 即左边直线
        path.lineTo(0, height * multiple);
//      从当前位置到目标点(x2,y2)做一条贝塞尔曲线,控制点为(x1,y1)  即底部曲线
        path.quadTo(width / 2, height, width, height * multiple);
//      绘制右边直线
        path.lineTo(width, 0);
//      将图形封闭,只有当style为Fill时生效,等同于     path.lineTo(0,0);
        path.close();
//      重要 裁剪
        mCanvas.clipPath(path);
    }

    /**
     * 第一种“裁剪方案”假裁剪
     * @param dpValue
     * @return
     */
//    private void drawArcRect() {
////      重要,此颜色可以作为自定义View的属性进行,方便与布局的背景协调
//        mPaint.setColor(Color.WHITE);
//        mPaint.setStyle(Paint.Style.FILL);
//        //从当前位置到目标点(x,y) 用直线连起 即左边直线
//        path.lineTo(0, height * multiple);
////      从当前位置到目标点(x2,y2)做一条贝塞尔曲线,控制点为(x1,y1)  即底部曲线
//        path.quadTo(width / 2, height, width, height * multiple);
////      绘制右边直线
//        path.lineTo(width, 0);
////      将图形封闭,只有当style为Fill时生效,等同于     path.lineTo(0,0);
//        path.close();
////      重要,设置当前View与之前图形的交叠之后的显示方式
//        path.setFillType(Path.FillType.INVERSE_WINDING);
////      重要 绘制
//        mCanvas.drawPath(path,mPaint);
//    }

    private float dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dpValue - 0.5f) * scale;
    }
}
