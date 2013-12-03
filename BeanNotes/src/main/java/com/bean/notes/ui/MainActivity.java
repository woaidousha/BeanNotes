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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.bean.notes.R;
import com.bean.notes.tools.AnimationUtil;
import com.bean.notes.widget.OperatorBar;

import static com.bean.notes.tools.Constant.OperatorMenu.*;

public class MainActivity extends SherlockFragmentActivity
    implements OperatorBar.OnOperatorItemClickListener, View.OnClickListener, ISwitchFragment {

    private int mCurrentFragment;
    private boolean mSearchMode;
    private boolean mFirstLaunch = true;

    private FragmentManager mFragmentManager;

    private OperatorBar mOperatorBar;

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
        initViews();
        initFragments();
    }

    private void initViews() {
        mOperatorBar = (OperatorBar) findViewById(R.id.operator_bar);
        mMenuItemSearch = (ImageView) findViewById(R.id.bottom_bar_search);
        mMenuItemSearch.setOnClickListener(this);
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
        mCurrentFragment = BaseIndexFragment.FM_INDEX_WORKSPACE;
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, getWorkSpaceList(), WorkSpaceList.class.getSimpleName());
        transaction.add(R.id.fragment_container, getNoteList(), NoteList.class.getSimpleName());
        transaction.add(R.id.fragment_container, getNote(), Note.class.getSimpleName());
        transaction.hide(getWorkSpaceList());
        transaction.hide(getNoteList());
        transaction.hide(getNote());
        transaction.commit();
        switchFragment();
    }

    private void switchFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mCurrentFragment == getWorkSpaceList().getFragmentIndex()) {
            transaction.show(getWorkSpaceList());
            transaction.hide(getNoteList());
            transaction.hide(getNote());
        } else if (mCurrentFragment == getNoteList().getFragmentIndex()) {
            transaction.hide(getWorkSpaceList());
            transaction.show(getNoteList());
            transaction.hide(getNote());
        } else if (mCurrentFragment == getNote().getFragmentIndex()) {
            transaction.hide(getWorkSpaceList());
            transaction.hide(getNoteList());
            transaction.show(getNote());
        }
        transaction.commit();
        toggleBottomBar();
    }

    @Override
    public void switchFragment(boolean next) {
        mCurrentFragment = next ? mCurrentFragment + 1 : mCurrentFragment - 1;
        switchFragment();
    }

    private WorkSpaceList getWorkSpaceList() {
        if (mWorkSpaceList == null) {
            mWorkSpaceList = new WorkSpaceList();
            mWorkSpaceList.setSwitchFragment(this);
        }
        return mWorkSpaceList;
    }

    public NoteList getNoteList() {
        if (mNoteList == null) {
            mNoteList = new NoteList();
            mNoteList.setSwitchFragment(this);
        }
        return mNoteList;
    }

    public Note getNote() {
        if (mNote == null) {
            mNote = new Note();
            mNote.setSwitchFragment(this);
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

    private void toggleBottomBar() {
        boolean searchMode = mSearchMode;
        mSearchMode = mCurrentFragment != BaseIndexFragment.FM_INDEX_NOTE;
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
        mFirstLaunch = false;
    }
}
