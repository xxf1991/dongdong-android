package com.example.dongdong.ui.widget;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.example.dongdong.R;

/**
 * @author deofly
 * @since 1.0 2014/12/05
 */
public class SwitchButton extends View implements View.OnClickListener {

    private Bitmap mSwitchBottom;
    private Bitmap mSwitchThumb;
    private Bitmap mSwitchFrame;
    private Bitmap mSwitchMask;

    private boolean mSwitchOn = true;

    private int mMoveLength; // max move offset
    private int mDeltX; // move offset
    private Rect mSrc;
    private Rect mDest;
    private Paint mPaint;

    private float mCurrentX;
    private float mLastX;
    private boolean mFlag = false;

    private OnChangeListener mListener;

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        mSwitchBottom = BitmapFactory.decodeResource(getResources(), R.drawable.ic_switch_bottom);
        mSwitchThumb = BitmapFactory.decodeResource(getResources(), R.drawable.ic_switch_thumb_on);
        mSwitchFrame = BitmapFactory.decodeResource(getResources(), R.drawable.ic_switch_frame);
        mSwitchMask = BitmapFactory.decodeResource(getResources(), R.drawable.ic_switch_mask);

        setOnClickListener(this);
        setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        mMoveLength = mSwitchBottom.getWidth() - mSwitchFrame.getWidth();
        mDeltX = 0;
        mDest = new Rect(0, 0, mSwitchFrame.getWidth(), mSwitchFrame.getHeight());
        mSrc = new Rect();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setAlpha(255);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    public void setOnChangeListener(OnChangeListener listener) {
        mListener = listener;
    }

    public interface OnChangeListener {
        public void onChange(SwitchButton sb, boolean state);
    }

    public interface Status {
        public int Close = 0;
        public int Open = 1;
    }

    public void open() {
        mSwitchOn = true;
    }

    public void close() {
        mSwitchOn = false;
    }

    public void setStatus(int status) {
        mSwitchOn = (status == Status.Open) ? true : false;
    }

    public int getStatus() {
        return mSwitchOn ? Status.Open : Status.Close;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mSwitchFrame.getWidth(), mSwitchFrame.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mDeltX > 0 || (mDeltX == 0 && mSwitchOn)) {
            if (mSrc != null) {
                mSrc.set(mMoveLength - mDeltX, 0, mSwitchBottom.getWidth() - mDeltX, mSwitchFrame.getHeight());
            }
        } else if (mDeltX < 0 || (mDeltX == 0 && !mSwitchOn)) {
            if (mSrc != null) {
                mSrc.set(-mDeltX, 0, mSwitchFrame.getWidth() - mDeltX, mSwitchFrame.getHeight());
            }
        }

        // like double buffer
        int count = canvas.saveLayer(new RectF(mDest), null, Canvas.MATRIX_SAVE_FLAG
                | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
                | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                | Canvas.CLIP_TO_LAYER_SAVE_FLAG);

        canvas.drawBitmap(mSwitchBottom, mSrc, mDest, null);
        canvas.drawBitmap(mSwitchThumb, mSrc, mDest, null);
        canvas.drawBitmap(mSwitchFrame, 0, 0, null);
        canvas.drawBitmap(mSwitchMask, 0, 0, mPaint);
        canvas.restoreToCount(count);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentX = event.getX();
                mDeltX = (int) (mCurrentX - mLastX);
                // switch is on and move left or switch is off and move right
                if ((mSwitchOn && mDeltX < 0) || (!mSwitchOn && mDeltX > 0)) {
                    mFlag = true;
                    mDeltX = 0;
                }

                if (Math.abs(mDeltX) > mMoveLength) {
                    mDeltX = mDeltX > 0 ? mMoveLength : - mMoveLength;
                }
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                if (Math.abs(mDeltX) > 0 && Math.abs(mDeltX) < mMoveLength / 2) {
                    mDeltX = 0;
                    invalidate();
                    return true;
                } else if (Math.abs(mDeltX) > mMoveLength / 2 && Math.abs(mDeltX) <= mMoveLength) {
                    mDeltX = mDeltX > 0 ? mMoveLength : -mMoveLength;
                    mSwitchOn = !mSwitchOn;
                    if(mListener != null) {
                        mListener.onChange(this, mSwitchOn);
                    }
                    invalidate();
                    mDeltX = 0;
                    return true;
                } else if(mDeltX == 0 && mFlag) {
                    // already moved
                    mDeltX = 0;
                    mFlag = false;
                    return true;
                }
                return super.onTouchEvent(event);
            default:
                break;
        }
        invalidate();

        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        mDeltX = mSwitchOn ? mMoveLength : -mMoveLength;
        mSwitchOn = !mSwitchOn;
        if(mListener != null) {
            mListener.onChange(this, mSwitchOn);
        }
        invalidate();
        mDeltX = 0;
    }
}
