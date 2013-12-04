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
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bean.notes.R;

public class OperatorBar extends RelativeLayout implements View.OnClickListener, ExpendMenu.OnTouchUpListener {

    private static final String TAG = "OperatorBar";

    private ImageView mBtnCapture;
    private View mBtnCaptureBg;
    private View mWheelColors;
    private View mWheelBg;
    private View mContentShadow;
    private ExpendMenu mExpendMenu;

    private GradientDrawable mCaptureBgDrawable;

    private OnOperatorItemClickListener mOperatorItemClickListener;

    public interface OnOperatorItemClickListener {
        public void onOperatorItemClick(int operatorIndex);
    }

    public OperatorBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.operator_bar, this, true);
        mBtnCapture = (ImageView) findViewById(R.id.btn_capture);
        mBtnCaptureBg = findViewById(R.id.btn_capture_bg);
        mWheelColors = findViewById(R.id.wheel_colors);
        mWheelBg = findViewById(R.id.wheel_bg);
        mCaptureBgDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.wheel_bg);
        mBtnCaptureBg.setBackgroundDrawable(mCaptureBgDrawable);
        mContentShadow = findViewById(R.id.content_shadow);
        mExpendMenu = (ExpendMenu) findViewById(R.id.expend_menu);
        mContentShadow.setOnClickListener(this);
        mBtnCapture.setOnClickListener(this);
        mExpendMenu.setOnTouchUpListener(this);
    }

    public void setOnMenuItemClickListener(OnOperatorItemClickListener menuItemClickListener) {
        this.mOperatorItemClickListener = menuItemClickListener;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_capture) {
            mBtnCapture.setSelected(!mBtnCapture.isSelected());
            refreshWheelViews();
        } else if (viewId == R.id.content_shadow) {
            mBtnCapture.setSelected(false);
            refreshWheelViews();
        }
    }

    private void refreshWheelViews() {
        boolean selected = mBtnCapture.isSelected();
        mBtnCapture.setImageResource(selected ? R.drawable.btn_capture_on : R.drawable.btn_capture_off);

        mBtnCaptureBg.setVisibility(selected ? View.GONE : View.VISIBLE);
        mContentShadow.setVisibility(selected ? View.VISIBLE : View.GONE);
        mWheelColors.setVisibility(selected ? View.VISIBLE : View.GONE);
        mWheelBg.setVisibility(selected ? View.VISIBLE : View.GONE);
        mExpendMenu.setVisibility(selected ? View.VISIBLE : View.GONE);

        Animation animation = AnimationUtils.loadAnimation(getContext(), isClosed() ? R.anim.pop_in_fast : R.anim.pop_away);
        mWheelColors.startAnimation(animation);
        mWheelBg.startAnimation(animation);
        Animation animationMenu = AnimationUtils.loadAnimation(getContext(), isClosed() ? R.anim.pop_in_fast_menu : R.anim.pop_away_menu);
        mExpendMenu.startAnimation(animationMenu);
    }

    public boolean isClosed() {
        return mBtnCapture.isSelected();
    }

    @Override
    public void onOnTouchUp(int operatorIndex) {
        mBtnCapture.setSelected(false);
        refreshWheelViews();
        if (mOperatorItemClickListener != null) {
            mOperatorItemClickListener.onOperatorItemClick(operatorIndex);
        }
    }

    public void setWheelBgColor(int color) {
        if (mCaptureBgDrawable != null) {
            mCaptureBgDrawable.setColor(color);
            mBtnCaptureBg.invalidate();
        }
    }
}
