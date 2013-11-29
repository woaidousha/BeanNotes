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
package com.bean.notes.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.bean.notes.R;
import com.bean.notes.tools.AnimationUtil;
import com.bean.notes.widget.OperatorBar;

import static com.bean.notes.tools.Constant.OperatorMenu.*;

public class MainActivity extends SherlockFragmentActivity implements OperatorBar.OnOperatorItemClickListener, View.OnClickListener {

    private Button mToggleBtn;

    private OperatorBar mOperatorBar;

    private ImageView mMenuItemSearch;
    private View mBottomBar;
    private ImageView mMenuItemAttach;
    private ImageView mMenuItemShare;
    private ImageView mMenuItemStar;
    private ImageView mMenuItemDelete;

    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initViews();
    }

    public void initViews() {
        mToggleBtn = (Button) findViewById(R.id.toggle_btn);
        mToggleBtn.setOnClickListener(this);
        mOperatorBar = (OperatorBar) findViewById(R.id.operator_bar);
        mMenuItemSearch = (ImageView) findViewById(R.id.bottom_bar_search);
        mMenuItemSearch.setOnClickListener(this);
        mBottomBar = findViewById(R.id.bottom_bar);
        mMenuItemAttach = (ImageView) findViewById(R.id.bottom_bar_attach);
        mMenuItemShare = (ImageView) findViewById(R.id.bottom_bar_share);
        mMenuItemStar = (ImageView) findViewById(R.id.bottom_bar_star);
        mMenuItemDelete = (ImageView) findViewById(R.id.bottom_bar_delete);
        mOperatorBar.setOnMenuItemClickListener(this);
    }

    public Note getNote() {
        if (mNote == null) {
            mNote = new Note();
        }
        return mNote;
    }

    @Override
    public void onOperatorItemClick(int operatorIndex) {
        switch (operatorIndex) {
            case MENU_ITEM_REMINDER:
                break;
            case MENU_ITEM_CAMERA:
                break;
            case MENU_ITEM_TEXT:
                break;
            case MENU_ITEM_VOICE:
                break;
            case MENU_ITEM_CHECKLIST:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.toggle_btn) {
            toggleBottomBar();
        }
    }

    private void toggleBottomBar() {
        final boolean searchMode = mMenuItemSearch.getVisibility() == View.VISIBLE;
        final View out = searchMode ? mMenuItemSearch : mBottomBar;
        final View in = searchMode ? mBottomBar : mMenuItemSearch;
        final float outHeight = out.getHeight();
        final float inHeight = in.getHeight();
        Animation animation = new TranslateAnimation(0, 0, 0, outHeight);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(AnimationUtil.BOTTOM_BAR_DURATION);
        animation.setFillAfter(false);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                out.setVisibility(View.GONE);
                Animation animation1 = new TranslateAnimation(0, 0, inHeight, 0);
                animation1.setInterpolator(new LinearInterpolator());
                animation1.setDuration(AnimationUtil.BOTTOM_BAR_DURATION);
                animation1.setFillAfter(false);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                in.setVisibility(View.VISIBLE);
                in.startAnimation(animation1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        out.startAnimation(animation);
    }
}
