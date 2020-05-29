package com.zyp.kotlin.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zyp.kotlin.R;

/**
 * HenCode
 * 扔无线 第二节
 * Created by zhangyanpeng on 2020/5/28
 */
public class PaintView extends View {

    private Paint mPaint = new Paint();

    private int width,height;

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //  线性渐变
    Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);

    //  辐射渐变
    Shader shader2 = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);

    //  扫描渐变
    Shader shader3 = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"));

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//      绘制Shader
//        mPaint.setShader(shader3);
//        canvas.drawRect(0, 0, 800, 800, mPaint);

//        mPaint.setShader(shader2);
//        canvas.drawRect(500,100,1000,500,mPaint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mPaint.setShader(bitmapShader);
        canvas.drawCircle(400,400,300,mPaint);

    }
}
