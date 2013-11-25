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
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.bean.notes.tools.LayoutUtil;

public class ExpendMenu extends ViewGroup {

    private Context mContext;

    private int mRadius;

    public ExpendMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public ExpendMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
            mRadius = getWidth() / 4;
            int dx = getWidth() / 2;
            int dy = getHeight();
            int childCount = getChildCount();
            double radian = Math.PI / childCount;
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                int viewW = view.getMeasuredWidth() / 2;
                int viewH = view.getMeasuredHeight() / 2;
                int x = (int) (LayoutUtil.dip2px(mContext, mRadius) * Math.cos(radian * i));
                int y = (int) (mRadius -  LayoutUtil.dip2px(mContext, mRadius) *  Math.sin(radian * i));
                view.layout(x - viewW + dx, y - viewH + dy, x + viewW + dx, y + viewH + dy);
            }
        }
    }
}
