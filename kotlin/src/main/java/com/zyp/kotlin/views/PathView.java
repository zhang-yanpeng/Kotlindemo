package com.zyp.kotlin.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by zhangyanpeng on 2020/5/14
 */
public class PathView extends View {

    private Paint mPaint = new Paint();
    private Path path = new Path();
    private Canvas canvas;

    {
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
    }

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
//        canvas.drawPath(path,mPaint);
        drawTwoCirvle();
        drawPathLins();
    }

    private void drawTwoCirvle() {
        mPaint.setColor(Color.parseColor("#33ff00ff"));
//      Path.Direction.CW 顺时针   CCW 逆时针
        path.addCircle(100, 100, 70, Path.Direction.CW);
        path.addCircle(180, 100, 70, Path.Direction.CW);
        path.addCircle(140, 168, 70, Path.Direction.CW);
//      style 方式
        canvas.drawPath(path, mPaint);
    }

    private void drawPathLins(){
        path.reset();
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Style.STROKE);
        mPaint.setColor(Color.BLUE);
//      移动起始点
        path.moveTo(100,100);//移动
        path.lineTo(400,400);//绝对位置
        path.rLineTo(200,0);//相对位置

//      贝塞尔曲线
        path.moveTo(20,20);
//      贝塞尔曲线 分别指定起点，控制点，终点 来绘制曲线
        path.cubicTo(10,20,400,100,800,20);


//      画弧线
        path.arcTo(100,100,400,400,-90,90,true);

        canvas.drawPath(path,mPaint);
    }
}
