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

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.bean.notes.R;
import com.bean.notes.tools.AnimationUtil;
import com.bean.notes.widget.OperatorBar;

import static com.bean.notes.tools.Constant.OperatorMenu.*;
import static com.bean.notes.ui.BaseIndexFragment.FM_INDEX_NOTE;
import static com.bean.notes.ui.BaseIndexFragment.FM_INDEX_NOTELIST;
import static com.bean.notes.ui.BaseIndexFragment.FM_INDEX_WORKSPACE;

public class MainActivity extends SherlockFragmentActivity implements OperatorBar.OnOperatorItemClickListener, View.OnClickListener, ISwitchFragmentListener {

    private static final int MSG_UPDATE_COLOR = 0;

    private int mCurrentFragment;
    private boolean mSearchMode;
    private int mActionAndBottomBg;
    private int mLastmActionAndBottomBg;
    private boolean mColorMode;
    private String mTitle;
    private long mCurrentId = -1;
    private boolean mFirstLaunch = true;

    private FragmentManager mFragmentManager;

    private OperatorBar mOperatorBar;

    private View mMenuPanel;
    private ImageView mMenuItemSearch;
    private View mBottomBar;
    private ImageView mMenuItemAttach;
    private ImageView mMenuItemShare;
    private ImageView mMenuItemStar;
    private ImageView mMenuItemDelete;

    private Note mNote;
    private NoteList mNoteList;
    private WorkSpaceList mWorkSpaceList;

    private MenuItemListener mMenuItemListener;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MSG_UPDATE_COLOR:
                    handlerUpdateColor(msg.arg1);
                    break;
            }

        }

        private void handlerUpdateColor(int color) {
            ColorDrawable currentActionDrawable = new ColorDrawable(color);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Title");
            actionBar.setBackgroundDrawable(currentActionDrawable);
            actionBar.setTitle(mTitle);
            ColorDrawable currentBottomDrawable = new ColorDrawable(color);
            mMenuPanel.setBackgroundDrawable(currentBottomDrawable);
            mOperatorBar.setWheelBgColor(color);
        }
    };

    class MenuItemListener implements IMenuItemStateListener {
        @Override
        public void onAttachStateChange(boolean disable) {
            mMenuItemAttach.setImageResource(disable ? R.drawable.ic_attach_disabled : R.drawable.ic_attach);
            mMenuItemAttach.setOnClickListener(disable ? null : getNote());
        }

        @Override
        public void onShareStateChange(boolean disable) {
            mMenuItemShare.setImageResource(disable ? R.drawable.ic_share_disabled : R.drawable.ic_share);
            mMenuItemShare.setOnClickListener(disable ? null : getNote());
        }

        @Override
        public void onStarStateChange(boolean stared, boolean disable) {
            mMenuItemStar.setImageResource(disable ? R.drawable.ic_star_off_disabled : R.drawable.ic_star_off);
            if (!disable && stared) {
                mMenuItemStar.setImageResource(R.drawable.ic_star_on);
            }
            mMenuItemStar.setOnClickListener(disable ? null : getNote());
        }

        @Override
        public void onDeleteStateChange(boolean disable) {
            mMenuItemDelete.setImageResource(disable ? R.drawable.ic_delete_disabled : R.drawable.ic_delete);
            mMenuItemDelete.setOnClickListener(disable ? null : getNote());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initData();
        initViews();
        initFragments();
    }

    private void initData() {
        mTitle = "";
        mActionAndBottomBg = getResources().getColor(R.color.bottom_bar_bg);
    }

    private void initViews() {
        mOperatorBar = (OperatorBar) findViewById(R.id.operator_bar);
        mMenuItemSearch = (ImageView) findViewById(R.id.bottom_bar_search);
        mMenuItemSearch.setOnClickListener(this);
        mMenuPanel = findViewById(R.id.menu_panel);
        mBottomBar = findViewById(R.id.bottom_bar);
        mMenuItemAttach = (ImageView) findViewById(R.id.bottom_bar_attach);
        mMenuItemShare = (ImageView) findViewById(R.id.bottom_bar_share);
        mMenuItemStar = (ImageView) findViewById(R.id.bottom_bar_star);
        mMenuItemDelete = (ImageView) findViewById(R.id.bottom_bar_delete);
        mMenuItemAttach.setOnClickListener(getNote());
        mMenuItemShare.setOnClickListener(getNote());
        mMenuItemStar.setOnClickListener(getNote());
        mMenuItemDelete.setOnClickListener(getNote());
        mOperatorBar.setOnMenuItemClickListener(this);
    }

    private void initFragments() {
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = FM_INDEX_WORKSPACE;
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, getWorkSpaceList());
        transaction.commit();
        mFirstLaunch = false;
    }

    private void doSwitchFragment(Switchable switchable, boolean next) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        ActionBar actionBar = getSupportActionBar();
        boolean homeAsUpEnable = false;
        if (next) {
            if (mCurrentFragment == FM_INDEX_WORKSPACE) {
                mCurrentId = -1;
                transaction.replace(R.id.fragment_container, getWorkSpaceList());
            } else if (mCurrentFragment == FM_INDEX_NOTELIST) {
                NoteList noteList = getNoteList();
                transaction.replace(R.id.fragment_container, noteList);
                Bundle bundle = new Bundle();
                bundle.putSerializable(BaseIndexFragment.ARGV_SWITCHABLE, switchable);
                noteList.setArguments(bundle);
                transaction.addToBackStack(null);
                homeAsUpEnable = true;
            } else if (mCurrentFragment == FM_INDEX_NOTE) {
                Note note = getNote();
                Bundle bundle = new Bundle();
                bundle.putSerializable("switchable", switchable);
                note.setArguments(bundle);
                transaction.replace(R.id.fragment_container, note);
                transaction.addToBackStack(null);
                homeAsUpEnable = true;
            }
        } else {
            getSupportFragmentManager().popBackStack();
        }
        actionBar.setDisplayHomeAsUpEnabled(homeAsUpEnable);
        transaction.commit();
        toggleBottomBar();
        switchActionAndMenuBg();
    }

    @Override
    public void switchFragment(boolean next, Switchable switchable) {
        mCurrentFragment = next ? mCurrentFragment + 1 : mCurrentFragment - 1;
        mLastmActionAndBottomBg = mActionAndBottomBg;
        if (switchable != null) {
            if (switchable.getActivityColor() != null) {
                mActionAndBottomBg = switchable.getActivityColor();
            }
            mTitle = switchable.getTitle();
            mCurrentId = switchable.getContentId();
        }
        doSwitchFragment(switchable, next);
    }

    private WorkSpaceList getWorkSpaceList() {
        if (mWorkSpaceList == null) {
            mWorkSpaceList = new WorkSpaceList();
            mWorkSpaceList.setSwitchFragmentListener(this);
        }
        return mWorkSpaceList;
    }

    public NoteList getNoteList() {
        if (mNoteList == null) {
            mNoteList = new NoteList();
            mNoteList.setSwitchFragmentListener(this);
        }
        return mNoteList;
    }

    public Note getNote() {
        if (mNote == null) {
            mNote = new Note();
            mNote.setSwitchFragmentListener(this);
            mMenuItemListener = new MenuItemListener();
            mNote.setMenuItemStateListener(mMenuItemListener);
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
    }

    @Override
    public void onBackPressed() {
        if (!mOperatorBar.isClosed()) {
            mOperatorBar.close();
        } else {
            onBackUI(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == com.actionbarsherlock.R.id.abs__home
            || itemId == android.R.id.home) {
            onBackUI(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onBackUI(boolean needFinish) {
        if (mCurrentFragment == FM_INDEX_WORKSPACE) {
            if (needFinish) {
                super.onBackPressed();
            }
            return;
        }
        switchFragment(false, null);
    }

    private void toggleBottomBar() {
        boolean searchMode = mSearchMode;
        mSearchMode = mCurrentFragment != FM_INDEX_NOTE;
        if (!mFirstLaunch && mSearchMode == searchMode) {
            return;
        }
        final View in = mSearchMode ? mMenuItemSearch : mBottomBar;
        final View out = mSearchMode ? mBottomBar : mMenuItemSearch;
        final float outHeight = out.getHeight();
        final float inHeight = in.getHeight();
        Animation outAnim = new TranslateAnimation(0, 0, 0, outHeight);
        outAnim.setInterpolator(new LinearInterpolator());
        outAnim.setDuration(AnimationUtil.BOTTOM_BAR_DURATION);
        outAnim.setFillAfter(false);
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                out.setVisibility(View.GONE);
                Animation inAnim = new TranslateAnimation(0, 0, inHeight, 0);
                inAnim.setInterpolator(new LinearInterpolator());
                inAnim.setDuration(AnimationUtil.BOTTOM_BAR_DURATION);
                inAnim.setFillAfter(false);
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
                in.startAnimation(inAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        out.startAnimation(outAnim);
    }

    private void switchActionAndMenuBg() {
        boolean colorMode = mColorMode;
        mColorMode = mCurrentFragment != FM_INDEX_WORKSPACE;
        if (mFirstLaunch) {
            mHandler.removeMessages(MSG_UPDATE_COLOR);
            Message msg = mHandler.obtainMessage(MSG_UPDATE_COLOR, mActionAndBottomBg, 0);
            mHandler.sendMessage(msg);
            return;
        }
        mActionAndBottomBg = mColorMode ? mActionAndBottomBg : getResources().getColor(R.color.action_bar_title);
        if (mColorMode == colorMode
            && mLastmActionAndBottomBg == mActionAndBottomBg) {
            return;
        }
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), mLastmActionAndBottomBg, mActionAndBottomBg);
        colorAnimator.setDuration(AnimationUtil.ACTION_BOTTOM_BAR_DURATION);
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentColor = (Integer) animation.getAnimatedValue();
                mHandler.removeMessages(MSG_UPDATE_COLOR);
                Message msg = mHandler.obtainMessage(MSG_UPDATE_COLOR, currentColor, 0);
                mHandler.sendMessage(msg);
            }
        });
        mMenuItemSearch.setImageResource(mColorMode ? R.drawable.ic_search : R.drawable.ic_search_dark);
        colorAnimator.start();
        colorAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

}
