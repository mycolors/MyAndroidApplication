package com.fengniao.mlibs.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;


public class AutoScrollViewPager extends ViewPager {
    private Handler mHandler;
    private Timer mTimer;
    private TimerTask mTimerTask;

    public AutoScrollViewPager(Context context) {
        super(context);
        mHandler = new Handler();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler();
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null && adapter.getCount() > 0)
            start();
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int current = getCurrentItem();
            int count = getAdapter().getCount();
            if (count == 0) {
                stop();
                return;
            }
            int next = (current + 1) % count;
            setCurrentItem(next);
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                stop();
                break;
            default:
                start();
        }
        return super.onTouchEvent(ev);
    }

    private void start() {
        stop();
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(runnable);
            }
        };
        mTimer.schedule(mTimerTask, 3000, 3000);
    }


    private void stop() {
        if (mTimer != null)
            mTimer.cancel();

        if (mTimerTask != null)
            mTimerTask.cancel();

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getAdapter() != null)
            start();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }
}
