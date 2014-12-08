package com.example.dongdong.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.dongdong.R;
import com.example.dongdong.ui.PrivateSettingActivity;

public class PrivateMainFragment extends BaseFragment
        implements View.OnClickListener, View.OnTouchListener {

    private final String TAG = getClass().getSimpleName();

    private static final int DURATION_ANIMATION = 1000;

    private Context mActivity;

    private View mLytMain;
    private View mLytBottom;

    private ImageView mBackground;
    private ImageView mToggle;

    private Animation mAnimUp;
    private Animation mAnimDown;

    private boolean mIsTop = true;
    private boolean mIsRunning = false;

    private GestureDetectorCompat mDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private_main, container, false);

        mLytMain = view.findViewById(R.id.lyt_main);
        mLytBottom = view.findViewById(R.id.lyt_bottom);
        mBackground = (ImageView) view.findViewById(R.id.background);

        mToggle = (ImageView) view.findViewById(R.id.toggle);

        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = mLytBottom.getHeight();
                initAnim(height);
            }
        });

        mLytMain.setClickable(true);
        mLytMain.setOnTouchListener(this);

        mToggle.setOnClickListener(this);
        view.findViewById(R.id.setting).setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mActivity = getSherlockActivity();

        mDetector = new GestureDetectorCompat(mActivity, new DDGestureListener());
    }

    private void initAnim(final int height) {
        if (mAnimUp == null) {
            mAnimUp = new TranslateAnimation(0, 0, 0, -height);

            mAnimUp.setDuration(DURATION_ANIMATION);
            mAnimUp.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    mIsRunning = true;

                    Animation anim = new TranslateAnimation(0, 0, height, 0);
                    anim.setDuration(DURATION_ANIMATION);
                    mLytBottom.startAnimation(anim);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mLytMain.clearAnimation();

                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                            mLytMain.getLayoutParams());
                    layoutParams.setMargins(0, 0, 0, 0);
                    mLytMain.setLayoutParams(layoutParams);
                    mLytMain.offsetTopAndBottom(height);

                    mToggle.setImageResource(R.drawable.ic_arrow_down);
                    mIsTop = !mIsTop;

                    mIsRunning = false;

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }

        if (mAnimDown == null) {
            mAnimDown = new TranslateAnimation(0, 0, 0, height);

            mAnimDown.setDuration(DURATION_ANIMATION);
            mAnimDown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mIsRunning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mLytMain.clearAnimation();

                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                            mLytMain.getLayoutParams());
                    layoutParams.setMargins(0, height, 0, -height);
                    mLytMain.setLayoutParams(layoutParams);

                    mToggle.setImageResource(R.drawable.ic_arrow_up);
                    mIsTop = !mIsTop;

                    mIsRunning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.toggle:
                if (!mIsRunning && mIsTop && mAnimDown != null) {
                    mLytMain.startAnimation(mAnimDown);
                } else if (!mIsRunning && !mIsTop && mAnimUp != null) {
                    mLytMain.startAnimation(mAnimUp);
                }
                break;
            case R.id.setting:
                intent = new Intent(mActivity, PrivateSettingActivity.class);
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private class DDGestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int verticalMinDistance = 20;
        private static final int minVelocity = 1000;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e2.getY() - e1.getY() > verticalMinDistance
                    && Math.abs(velocityY) > minVelocity
                    && !mIsRunning
                    && mIsTop
                    && mAnimDown != null) {
                mLytMain.startAnimation(mAnimDown);
                return false;
            } else if (e1.getY() - e2.getY() > verticalMinDistance
                    && Math.abs(velocityY) > minVelocity
                    && !mIsRunning
                    && !mIsTop
                    && mAnimUp != null) {
                mLytMain.startAnimation(mAnimUp);
                return false;
            }

            return true;
        }
    }
}
