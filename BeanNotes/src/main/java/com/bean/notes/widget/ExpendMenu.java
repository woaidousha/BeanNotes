/*---------------------------------------------------------------------------------------------
 *                       Copyright (c) 2013 Capital Alliance Software(Pekall) 
 *                                    All Rights Reserved
 *    NOTICE: All information contained herein is, and remains the property of Pekall and
 *      its suppliers,if any. The intellectual and technical concepts contained herein are
 *      proprietary to Pekall and its suppliers and may be covered by P.R.C, U.S. and Foreign
 *      Patents, patents in process, and are protected by trade secret or copyright law.
 *      Dissemination of this information or reproduction of this material is strictly 
 *      forbidden unless prior written permission is obtained from Pekall.
 *                                     www.pekall.com
 *--------------------------------------------------------------------------------------------- 
*/
package com.bean.notes.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.bean.notes.tools.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ExpendMenu extends ViewGroup {

    private Context mContext;
    private int mCenterX;
    private int mCenterY;

    private int mRadius;

    private boolean mTouched;
    private float mTouchX;
    private float mTouchY;

    private ArrayList<DivideLine> mDivideLineRadians = null;
    private int mTouchBgIndex = -1;


    class DivideLine {
        DivideLine() {
        }

        DivideLine(double slope, float endX, float endY) {
            this.slope = slope;
            this.endX = endX;
            this.endY = endY;
        }

        double slope;
        float endX;
        float endY;

        public double getDegree() {
            return Math.toDegrees(slope);
        }

        @Override
        public String toString() {
            return "DivideLine{" +
                    "slope=" + slope +
                    ", endX=" + endX +
                    ", endY=" + endY +
                    ", degree=" + Math.toDegrees(slope) +
                    '}';
        }
    }

    public ExpendMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int index = 0; index < getChildCount(); index++) {
            final View child = getChildAt(index);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            mRadius = getMeasuredWidth() / 3;
            int dx = getMeasuredWidth() / 2;
            int dy = getMeasuredHeight();
            int childCount = getChildCount();
            double radian = Math.toRadians(180 / (childCount - 1));
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                int viewW = view.getMeasuredWidth() / 2;
                int viewH = view.getMeasuredHeight();
                int x = (int) (-1 * mRadius * Math.cos(radian * i));
                int y = (int) (-1 * mRadius * Math.sin(radian * i));
                view.layout(x - viewW + dx, y - viewH + dy, x + viewW + dx, y + dy);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        View view = getChildAt(0);
        int viewH = view.getMeasuredHeight() / 2;
        mCenterX = width / 2;
        mCenterY = getHeight() - viewH;
        drawTouchBackground(canvas);
        drawDivideLine(canvas);
    }

    private void drawDivideLine(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paint.setColor(Color.GRAY);
        if (mDivideLineRadians == null) {
            calculateDivideLine();
        }
        for (DivideLine line : mDivideLineRadians) {
            canvas.drawLine(mCenterX, mCenterY, line.endX + mCenterX, line.endY + mCenterY, paint);
        }
    }

    private void drawTouchBackground(Canvas canvas) {
        if (mDivideLineRadians == null) {
            return;
        }
        if (!mTouched) {
            return;
        }
        DivideLine line1 = new DivideLine(0, getHeight(), 0);
        DivideLine line2 = new DivideLine(getWidth(), getHeight(), (float) Math.PI);
        if (mTouchY > mCenterY) {
            if (mTouchX < mCenterX) {
                mTouchBgIndex = 0;
                line2 = mDivideLineRadians.get(0);
            } else {
                mTouchBgIndex = mDivideLineRadians.size() - 1;
                line1 = mDivideLineRadians.get(mTouchBgIndex);
            }
        } else {
            double radian = Math.atan(1.0 * (mCenterY - mTouchY) / (mCenterX - mTouchX));
            for (int i = 0; i < mDivideLineRadians.size(); i++) {
                DivideLine line = mDivideLineRadians.get(i);
                if (radian <= line.slope) {
                    mTouchBgIndex = i;
                    line2 = line;
                    if (i != 0) {
                        line1 = mDivideLineRadians.get(i - 1);
                    }
                    break;
                }
            }
            if (mTouchBgIndex == mDivideLineRadians.size() - 1) {
                line1 = mDivideLineRadians.get(mDivideLineRadians.size() - 1);
            }
        }
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        RectF rectF = new RectF(0, mCenterY - getHeight() / 2, getWidth(), mCenterY + getHeight() / 2);
        LogUtil.lyl(line1.toString());
        LogUtil.lyl(line2.toString());
        canvas.drawArc(rectF, (float) Math.toDegrees(line1.getDegree()) + 180, (float) Math.toDegrees(line2.getDegree()) + 180, true, paint);
    }

    public void calculateDivideLine() {
        int radius = getWidth() / 2;
        mDivideLineRadians = new ArrayList<DivideLine>();
        for (int i = 0; i < getChildCount() - 1; i++) {
            DivideLine line = new DivideLine();
            View view1 = getChildAt(i);
            int w1 = view1.getWidth() / 2;
            int h1 = view1.getHeight() / 2;
            View view2 = getChildAt(i + 1);
            int w2 = view2.getWidth() / 2;
            int h2 = view2.getHeight() / 2;
            float x1 = view1.getX() + w1;
            float y1 = view1.getY() + h1;
            float x2 = view2.getX() + w2;
            float y2 = view2.getY() + h2;
            float x = (x1 + x2) / 2;
            float y = (y1 + y2) / 2;

            double radian = Math.atan(1.0 * (mCenterY - y) / (mCenterX - x));
            if (radian < 0) {
                radian += Math.PI;
            }
            x = (float) (-1 * radius * Math.cos(radian));
            y = (float) (-1 * radius * Math.sin(radian));
            line.slope = radian;
            line.endX = x;
            line.endY = y;
            LogUtil.lyl(line.toString());
            mDivideLineRadians.add(line);
        }
        Collections.sort(mDivideLineRadians, new Comparator<DivideLine>() {
            @Override
            public int compare(DivideLine lhs, DivideLine rhs) {
                if (lhs.slope < rhs.slope) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mTouched = true;
                mTouchX = event.getX();
                mTouchY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mTouchX = event.getX();
                mTouchY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mTouched = false;
                mTouchX = 0;
                mTouchY = 0;
                break;
        }
        invalidate();
        return true;
    }
}
