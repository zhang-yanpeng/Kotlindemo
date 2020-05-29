package com.zyp.kotlin.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import kotlin.reflect.KVariance;

/**
 * Created by zhangyanpeng on 2020/5/29
 */
public class MutilView extends View {

    private Paint paint = new Paint();
    private Path path;
    private int width, heigth;


    public MutilView(Context context) {
        super(context);
    }

    public MutilView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        heigth = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.WHITE);
        path.addCircle(400,400,300, Path.Direction.CCW);
        canvas.drawPath(path,paint);
        paint.setColor(Color.BLUE);
        RectF rectF = new RectF();
        rectF.left =0;
        rectF.right = 800;
        rectF.top = 0;
        rectF.bottom = 800;
        path.addRect(rectF, Path.Direction.CW);
        canvas.drawPath(path,paint);
    }
}
