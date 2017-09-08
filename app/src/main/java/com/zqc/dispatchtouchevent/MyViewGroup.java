package com.zqc.dispatchtouchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import static com.zqc.dispatchtouchevent.Constants.TAG;

public class MyViewGroup extends RelativeLayout {
    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "MyViewGroup");
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "MyViewGroup onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "MyViewGroup dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "MyViewGroup onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyViewGroup onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "MyViewGroup onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "MyViewGroup onTouchEvent ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyViewGroup onTouchEvent ACTION_UP");
                break;
            default:
                Log.e(TAG, "MyViewGroup onTouchEvent " + event.getAction());
                break;
        }
        return super.onTouchEvent(event);
    }
}
