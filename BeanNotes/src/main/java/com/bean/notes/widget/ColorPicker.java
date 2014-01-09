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
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.bean.notes.tools.ColorUtil;

public class ColorPicker extends LinearLayout implements View.OnClickListener {

    private int mSpaceSize = 10;

    private int mCellSize = 0;

    private Integer mSelectColor = ColorUtil.sColorsRes[0];

    public ColorPicker(Context context) {
        super(context, null);
    }

    public ColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    private void initViews(int width, int height) {
        for (int colorRes : ColorUtil.sColorsRes) {
            ColorPickerItem view = new ColorPickerItem(getContext());
            view.setColor(colorRes);
            view.setOnClickListener(this);
            view.setTag(colorRes);
            view.setSelected(mSelectColor == colorRes);
            LayoutParams params = new LayoutParams(width, height);
            addView(view, params);
        }
    }

    private void refreshViews() {
        int childCount = ColorUtil.sColorsRes.length;
        for (int i = 0; i < childCount; i++) {
            ColorPickerItem view = (ColorPickerItem) getChildAt(i);
            Integer tag = (Integer) view.getTag();
            view.setSelected(tag == mSelectColor);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        measureChild(width, height);
        initViews(mCellSize, mCellSize);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    private void measureChild(int width, int height) {
        int childCount = ColorUtil.sColorsRes.length;
        width = width - (childCount - 1) * mSpaceSize;
        mCellSize = width / childCount;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = ColorUtil.sColorsRes.length;
            int hDiff = getMeasuredHeight()/ 2 - mCellSize/ 2;
            int x = 0;
            int y = hDiff;
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                int left = x + mSpaceSize * i + mCellSize * i;
                view.layout(left, y , left + mCellSize, y + mCellSize);
            }
        }
    }

    @Override
    public void onClick(View v) {
        mSelectColor = (Integer) v.getTag();
        refreshViews();
    }

    public Integer getSelectColor() {
        return mSelectColor;
    }
}
