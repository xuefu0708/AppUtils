package com.zxf.apputils.WidgetDemo.Animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by Administrator on 2016/9/1.
 */
public class MyPointView extends View{
    private Point mCurPoint;
    public MyPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurPoint != null){
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(300,300,mCurPoint.getRadius(),paint);
        }
    }

    public void doPointAnim(){
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),new Point(20),new Point(200));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurPoint = (Point)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }
}
 class Point {
    private int radius;

    public Point(int radius){
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
