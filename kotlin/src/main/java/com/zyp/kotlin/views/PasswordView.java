package com.zyp.kotlin.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.zyp.kotlin.R;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author sxs
 * @date 2018/8/23
 */

public class PasswordView extends View {

    /**
     * 样式模式
     */
    private PasswordMode passwordMode;
    /**
     * 密码个数
     */
    private int passwordLength;
    /**
     * 光标闪动间隔时间
     */
    private long cursorFlashTime;
    /**
     * 每个密码间的间隔
     */
    private int passwordPadding;
    /**
     * 单个密码大小
     */
    private int passwordSize;
    /**
     * 默认边框颜色
     */
    private int defaultBorderColor;

    /**
     * 选中边框颜色
     */
    private int selectedBorderColor;

    /**
     * 文字颜色
     */
    private int textColor;
    /**
     * 下划线粗细
     */
    private int borderWidth;
    /**
     * 光标位置
     */
    private int cursorPosition;
    /**
     * 光标粗细
     */
    private int cursorWidth;
    /**
     * 光标长度
     */
    private int cursorHeight;
    /**
     * 光标颜色
     */
    private int cursorColor;
    /**
     * 光标是否正在显示
     */
    private boolean isCursorShowing;
    /**
     * 是否开启光标
     */
    private boolean isCursorEnable;
    /**
     * 是否输入完毕
     */
    private boolean isInputComplete;
    /**
     * 密文符号大小
     */
    private int cipherTextSize;
    /**
     * 是否开启密文
     */
    private boolean cipherEnable;
    /**
     * 密文符号
     */
    private static String CIPHER_TEXT = "*";
    /**
     * 密码数组
     */
    private String[] password;
    private PasswordListener passwordListener;
    private KeyBoardShowListener keyboardShowListener;
    private Paint paint;
    private Timer timer;
    private TimerTask timerTask;

    public PasswordView(Context context) {
        super(context);
    }

    /**
     * 当前只支持从xml中构建该控件
     *
     * @param context
     * @param attrs
     */
    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttribute(attrs);
    }

    private void readAttribute(AttributeSet attrs) {
        Resources resources = getContext().getResources();
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PasswordView);
            textColor = typedArray.getColor(R.styleable.PasswordView_textColor, Color.GRAY);
            defaultBorderColor = typedArray.getColor(R.styleable.PasswordView_pwvBorderColor, Color.BLACK);
            selectedBorderColor = typedArray.getColor(R.styleable.PasswordView_pwvSelectBorderColor, defaultBorderColor);
            passwordMode = PasswordMode.formMode(typedArray.getInteger(R.styleable.PasswordView_mode, PasswordMode.UNDERLINE.getMode()));
            passwordLength = typedArray.getInteger(R.styleable.PasswordView_passwordLength, 6);
            cursorFlashTime = typedArray.getInteger(R.styleable.PasswordView_cursorFlashTime, 500);
            borderWidth = typedArray.getDimensionPixelSize(R.styleable.PasswordView_pwvBorderWidth,
                    resources.getDimensionPixelSize(R.dimen.common_password_border_width));
            cursorWidth = resources.getDimensionPixelSize(R.dimen.common_password_border_width);
            cursorColor = typedArray.getColor(R.styleable.PasswordView_cursorColor, Color.GRAY);
            isCursorEnable = typedArray.getBoolean(R.styleable.PasswordView_isCursorEnable, true);
            passwordSize = typedArray.getDimensionPixelSize(R.styleable.PasswordView_passwordSize, dp2px(40));
            passwordPadding = typedArray.getDimensionPixelSize(R.styleable.PasswordView_passwordPadding,
                    passwordMode == PasswordMode.UNDERLINE ? dp2px(15) : dp2px(-3));
            cipherEnable = typedArray.getBoolean(R.styleable.PasswordView_cipherEnable, false);
            typedArray.recycle();
        }
        password = new String[passwordLength];
        init();
    }

    private void init() {
        setFocusableInTouchMode(true);
        paint = new Paint();
        paint.setAntiAlias(true);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                isCursorShowing = !isCursorShowing;
                postInvalidate();
            }
        };
        timer = new Timer();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                //没有指定大小，宽度 = 单个密码框大小 * 密码位数 + 密码框间距 *（密码位数 - 1）
                width = passwordSize * passwordLength + passwordPadding * (passwordLength - 1);
                break;
            case MeasureSpec.EXACTLY:
                //指定大小，宽度 = 指定的大小
                width = MeasureSpec.getSize(widthMeasureSpec);
                //密码框大小等于 (宽度 - 密码框间距 *(密码位数 - 1)) / 密码位数
                passwordSize = (width - (passwordPadding * (passwordLength - 1))) / passwordLength;
                break;
            default:
                break;
        }
        setMeasuredDimension(width, passwordSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //文本大小
        cipherTextSize = passwordSize / 2;
        //光标长度
        cursorHeight = passwordSize / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (passwordMode == PasswordMode.UNDERLINE) {
            //绘制下划线
            drawUnderLine(canvas, paint);
        } else {
            //绘制方框
            drawRect(canvas, paint);
        }
        //绘制光标
        drawCursor(canvas, paint);
        //绘制密码文本
        drawCipherText(canvas, paint);
    }

    /**
     * 删除
     */
    private String delete() {
        String deleteText = null;
        if (cursorPosition > 0) {
            deleteText = password[cursorPosition - 1];
            password[cursorPosition - 1] = null;
            cursorPosition--;
        } else if (cursorPosition == 0) {
            deleteText = password[cursorPosition];
            password[cursorPosition] = null;
        }
        isInputComplete = false;
        if (passwordListener != null) {
            passwordListener.onPasswordInput(false);
        }
        return deleteText;
    }

    /**
     * 增加
     */
    private String add(String c) {
        String addText = null;
        if (cursorPosition < passwordLength) {
            addText = c;
            password[cursorPosition] = c;
            cursorPosition++;
            if (cursorPosition == passwordLength) {
                isInputComplete = true;
                if (passwordListener != null) {
                    passwordListener.onPasswordInput(true);
                }
            }
        }

        return addText;
    }

    /**
     * 按下删除按键
     */
    public void deleteKey() {
        if (TextUtils.isEmpty(password[0])) {
            return;
        }
        String text = delete();
        if (passwordListener != null) {
            passwordListener.passwordDelete(text);
        }
        postInvalidate();
    }

    /**
     * 按下输入按键
     *
     * @param input
     */
    public void addKey(String input) {
        if (isInputComplete) {
            return;
        }
        String text = add(input);
        if (passwordListener != null) {
            passwordListener.passwordChange(text);
        }
        postInvalidate();
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        StringBuilder buffer = new StringBuilder();
        for (String c : password) {
            if (TextUtils.isEmpty(c)) {
                continue;
            }
            buffer.append(c);
        }
        return buffer.toString();
    }

    /**
     * 绘制密码替代符号
     *
     * @param canvas
     * @param paint
     */
    private void drawCipherText(Canvas canvas, Paint paint) {
        paint.setColor(textColor);
        paint.setTextSize(cipherTextSize);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        //文字居中的处理
        Rect rect = new Rect();
        canvas.getClipBounds(rect);
        int height = rect.height();
        paint.getTextBounds(CIPHER_TEXT, 0, CIPHER_TEXT.length(), rect);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        float y = height / 2f + fontHeight / 2f - fontMetrics.bottom;

        //根据输入的密码位数，进行for循环绘制
        for (int i = 0; i < password.length; i++) {
            if (!TextUtils.isEmpty(password[i])) {
                // x = paddingLeft + 单个密码框大小/2 + ( 密码框大小 + 密码框间距 ) * i
                // y = paddingTop + 文字居中所需偏移量
                if (cipherEnable) {
                    //没有开启明文显示，绘制密码密文
                    canvas.drawText(CIPHER_TEXT,
                            (getPaddingLeft() + passwordSize / 2) + (passwordSize + passwordPadding) * i,
                            getPaddingTop() + y, paint);
                } else {
                    //明文显示，直接绘制密码
                    canvas.drawText(password[i],
                            (getPaddingLeft() + passwordSize / 2) + (passwordSize + passwordPadding) * i,
                            getPaddingTop() + y, paint);
                }
            }
        }
    }

    /**
     * 绘制光标
     *
     * @param canvas
     * @param paint
     */
    private void drawCursor(Canvas canvas, Paint paint) {
        //画笔初始化
        paint.setColor(cursorColor);
        paint.setStrokeWidth(cursorWidth);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        //光标未显示 && 开启光标 && 输入位数未满 && 获得焦点
        if (!isCursorShowing && isCursorEnable && !isInputComplete && hasFocus()) {
            // 起始点x = paddingLeft + 单个密码框大小 / 2 + (单个密码框大小 + 密码框间距) * 光标下标
            // 起始点y = paddingTop + (单个密码框大小 - 光标大小) / 2
            // 终止点x = 起始点x
            // 终止点y = 起始点y + 光标高度
            canvas.drawLine((getPaddingLeft() + passwordSize / 2) + (passwordSize + passwordPadding) * cursorPosition,
                    getPaddingTop() + (passwordSize - cursorHeight) / 2,
                    (getPaddingLeft() + passwordSize / 2) + (passwordSize + passwordPadding) * cursorPosition,
                    getPaddingTop() + (passwordSize + cursorHeight) / 2,
                    paint);
        }
    }

    /**
     * 绘制密码框下划线
     *
     * @param canvas
     * @param paint
     */
    private void drawUnderLine(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        for (int i = 0; i < passwordLength; i++) {
            if (cursorPosition == i) {
                paint.setColor(selectedBorderColor);
            } else {
                paint.setColor(defaultBorderColor);
            }
            canvas.drawLine(
                    getPaddingLeft() + (passwordSize + passwordPadding) * i,
                    getPaddingTop() + passwordSize - borderWidth,
                    getPaddingLeft() + (passwordSize + passwordPadding) * i + passwordSize,
                    getPaddingTop() + passwordSize - borderWidth,
                    paint);
        }
    }

    private void drawRect(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        RectF rect;
        for (int i = 0; i < passwordLength; i++) {
            if (cursorPosition == i) {
                paint.setColor(selectedBorderColor);
            } else {
                paint.setColor(defaultBorderColor);
            }
            rect = new RectF(
                    getPaddingLeft() + (passwordSize + passwordPadding) * i + borderWidth,
                    getPaddingTop() + borderWidth,
                    getPaddingLeft() + (passwordSize + passwordPadding) * i + passwordSize - borderWidth,
                    getPaddingTop() + passwordSize - borderWidth);
            canvas.drawRoundRect(rect, borderWidth / 2, borderWidth / 2, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            requestFocus();
            if (keyboardShowListener != null) {
                keyboardShowListener.onKeyBoardShow();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (!hasWindowFocus && keyboardShowListener != null) {
            keyboardShowListener.onKeyBoardHide();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        timer.scheduleAtFixedRate(timerTask, 0, cursorFlashTime);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        timer.cancel();
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        return super.onCreateInputConnection(outAttrs);
    }

    public void setPasswordListener(PasswordListener passwordListener) {
        this.passwordListener = passwordListener;
    }

    public void setKeyboardShowListener(KeyBoardShowListener keyboardShowListener) {
        this.keyboardShowListener = keyboardShowListener;
    }

    public void setPasswordSize(int passwordSize) {
        this.passwordSize = passwordSize;
        postInvalidate();
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
        postInvalidate();
    }

    public void setCursorColor(int cursorColor) {
        this.cursorColor = cursorColor;
        postInvalidate();
    }

    public void setCursorEnable(boolean cursorEnable) {
        isCursorEnable = cursorEnable;
        postInvalidate();
    }

    public void setCipherEnable(boolean cipherEnable) {
        this.cipherEnable = cipherEnable;
        postInvalidate();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putStringArray("password", password);
        bundle.putInt("cursorPosition", cursorPosition);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            password = bundle.getStringArray("password");
            cursorPosition = bundle.getInt("cursorPosition");
            state = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(state);
    }

    /**
     * 是否
     */
    public interface KeyBoardShowListener {

        /**
         * 拉起键盘
         */
        void onKeyBoardShow();

        /**
         * 隐藏键盘
         */
        void onKeyBoardHide();
    }

    /**
     * 密码监听者
     */
    public interface PasswordListener {
        /**
         * 删除监听
         *
         * @param c 删除的字符
         */
        void passwordDelete(String c);

        /**
         * 输入监听
         *
         * @param c 输入的字符
         */
        void passwordChange(String c);

        /**
         * 密码是否可输入
         *
         * @param complete
         */
        void onPasswordInput(boolean complete);

    }

    public static int dp2px(float dp) {
        return (int)(dp * Resources.getSystem().getDisplayMetrics().density + 0.5F);
    }

}
