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
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.bean.notes.tools.LayoutUtil;

public class ExpendMenu extends ViewGroup {

    private Context mContext;
    private int mCenterX;
    private int mCenterY;

    private int mRadius;

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
            mRadius = getWidth() / 6;
            int dx = getWidth() / 2;
            int dy = getHeight();
            int childCount = getChildCount();
            double radian = Math.toRadians(180 / (childCount - 1));
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                int viewW = view.getMeasuredWidth() / 2;
                int viewH = view.getMeasuredHeight();
                int x = (int) (-1 * LayoutUtil.dip2px(mContext, mRadius) * Math.cos(radian * i));
                int y = (int) (-1 * LayoutUtil.dip2px(mContext, mRadius) * Math.sin(radian * i));
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
        drawDivideLine(canvas);
    }

    private void drawDivideLine(Canvas canvas) {
        int radius = getWidth() / 2;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ;
        paint.setColor(Color.GRAY);
        for (int i = 0; i < getChildCount() - 1; i++) {
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
            canvas.drawLine(mCenterX, mCenterY, x + mCenterX, y + mCenterY, paint);
        }
    }
}
