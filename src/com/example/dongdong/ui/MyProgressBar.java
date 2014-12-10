package com.example.dongdong.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.example.dongdong.R;
/**
 * Created by xxf on 2014/12/7.
 *public */
class MyProgressBar extends ProgressBar {
    private String text;
    private Paint mPaint;
    private static final int TEXT_SIZE=50;

    public MyProgressBar(Context context, AttributeSet attrs) {    //必须要写的构造方法
        super(context, attrs);
        initText();
    }

    @Override
    public synchronized void setProgress(int progress) {
        setText(progress);
        super.setProgress(progress);
    }

    // 设置文字内容
    private void setText(int progress) {
        this.text = progress+"%";
    }

    // 初始化，画笔
    private void initText() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(Color.WHITE);
        this.mPaint.setTextSize(TEXT_SIZE);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
        int x = (getWidth() / 2) - rect.centerX();
        int y = (getHeight() / 2) - rect.centerY();
        canvas.drawText(this.text, x, y, this.mPaint);
    }
}