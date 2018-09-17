package com.exam.basecomponent.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 默认禁止滑动的viewpager
 */

public class CViewPager extends ViewPager {

    private boolean isScrollable = false;

    public void setIsScrollable(boolean isScrollable) {
        this.isScrollable = isScrollable;
    }

    public CViewPager(@NonNull Context context) {
        super(context);
    }

    public CViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScrollable && super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScrollable && super.onTouchEvent(ev);
    }
}
