package com.zqc.dispatchtouchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import static com.zqc.dispatchtouchevent.Constants.MY_GESTURE_TAG;
import static com.zqc.dispatchtouchevent.Constants.TAG;


public class MyView extends TextView {
    private Context mContext;
    private GestureDetector mGesture;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "MyView");
        mContext = context;
        //手势初始化
        mGesture = new GestureDetector(mContext, mGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "MyView onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyView onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "MyView onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "MyView onTouchEvent ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyView onTouchEvent ACTION_UP");
                break;
            default:
                Log.e(TAG, "MyView onTouchEvent " + event.getAction());
                break;
        }
//        设置手势监听
        mGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "MyView dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    /**
     * 快速两下 ：onDown -->	onSingleTapup --> onDoubleTap(2) --> OnDoubleTapEvent(2) --> onDown	--> OnDoubleTapEvent(2)
     * 缓慢两下 ：onDown -->	onSingleTapup --> onSingleTapConfirmed(2) --> onDown --> onSingleTapup --> onSingleTapConfirmed(2)
     */
    private GestureDetector.OnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
        // 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
        public boolean onDown(MotionEvent arg0) {
            Log.e(MY_GESTURE_TAG, "onDown");
            //Toast.makeText(mContext, "onDown", Toast.LENGTH_SHORT).show();
            return true;
        }

        //双击事件
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.e(MY_GESTURE_TAG, "onDoubleTap");
            //Toast.makeText(mContext, "onDoubleTap", Toast.LENGTH_SHORT).show();
            return super.onDoubleTap(e);
        }

        /**
         * 双击间隔中发生的动作。指触发onDoubleTap以后，在双击之间发生的其它动作，包含down、up和move事件
         */
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.e(MY_GESTURE_TAG, "onDoubleTapEvent action:"+e.getAction());
            //Toast.makeText(mContext, "onDoubleTapEvent", Toast.LENGTH_SHORT).show();
            return super.onDoubleTapEvent(e);
        }

        /**
         * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
         * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
         * * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
         * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
         *
         * 而onDown也是由一个MotionEventACTION_DOWN触发的，但是他没有任何限制，
         * 也就是说当用户点击的时候，首先MotionEventACTION_DOWN，onDown就会执行，
         * 如果在按下的瞬间没有松开或者是拖动的时候onShowPress就会执行，如果是按下的时间超过瞬间
         * （这块我也不太清楚瞬间的时间差是多少，一般情况下都会执行onShowPress），拖动了，就不执行onShowPress。
         */
        public void onShowPress(MotionEvent e) {
            Log.e(MY_GESTURE_TAG, "onShowPress");
            //Toast.makeText(mContext, "onShowPress", Toast.LENGTH_SHORT).show();
        }

        // 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e(MY_GESTURE_TAG, "onSingleTapUp");
            //Toast.makeText(mContext, "onSingleTapUp", Toast.LENGTH_SHORT).show();
            return true;
        }

        /**
         * 单击事件。用来判定该次点击是SingleTap而不是DoubleTap，如果连续点击两次就是DoubleTap手势，如果只点击一次，系统等待一段时间后没有收到第二次点击
         * 则判定该次点击为SingleTap而不是DoubleTap，然后触发SingleTapConfirmed事件。触发顺序是：OnDown->OnsingleTapUp->OnsingleTapConfirmed
         * 关于onSingleTapConfirmed和onSingleTapUp的一点区别： OnGestureListener有这样的一个方法onSingleTapUp，和onSingleTapConfirmed容易混淆。二者的
         * 区别是：onSingleTapUp，只要手抬起就会执行，而对于onSingleTapConfirmed来说，如果双击的话，则onSingleTapConfirmed不会执行。
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.e(MY_GESTURE_TAG, "onSingleTapConfirmed");
            //Toast.makeText(mContext, "onSingleTapConfirmed", Toast.LENGTH_SHORT).show();
            return super.onSingleTapConfirmed(e);
        }

        /**
         *  用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
         *  参数解释：
                e1：第1个ACTION_DOWN MotionEvent
                e2：最后一个ACTION_MOVE MotionEvent
                velocityX：X轴上的移动速度，像素/秒
                velocityY：Y轴上的移动速度，像素/秒
          */
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e(MY_GESTURE_TAG, "onFling");
            //Toast.makeText(mContext, "onFling", Toast.LENGTH_SHORT).show();
            return true;
        }

        // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e(MY_GESTURE_TAG, "onScroll");
            //Toast.makeText(mContext, "onScroll", Toast.LENGTH_SHORT).show();
            return true;
        }

        // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
        public void onLongPress(MotionEvent e) {
            Log.e(MY_GESTURE_TAG, "onLongPress");
            //Toast.makeText(mContext, "onLongPress", Toast.LENGTH_SHORT).show();
        }
    };
}
