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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bean.notes.R;

public class Note extends BaseIndexFragment {

    private IMenuItemStateListener mMenuItemStateListener;

    private boolean mTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setOnClickListener(this);
    }

    @Override
    protected int getFragmentIndex() {
        return FM_INDEX_NOTE;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.text_view) {
            if (mSwitchFragmentListener != null) {
                mSwitchFragmentListener.switchFragment(false, null);
            }
        } else if (viewId == R.id.bottom_bar_attach) {
        } else if (viewId == R.id.bottom_bar_share) {
        } else if (viewId == R.id.bottom_bar_star) {
        } else if (viewId == R.id.bottom_bar_delete) {
        }
    }

    public void setMenuItemStateListener(IMenuItemStateListener menuItemStateListener) {
        this.mMenuItemStateListener = menuItemStateListener;
    }

    private void attach() {
        boolean disable = mTest;
        if (mMenuItemStateListener != null) {
            mMenuItemStateListener.onAttachStateChange(disable);
        }
    }

    private void share() {
        boolean disable = mTest;
        if (mMenuItemStateListener != null) {
            mMenuItemStateListener.onShareStateChange(disable);
        }
    }

    private void star() {
        boolean stared = mTest;
        boolean disable = mTest;
        if (mMenuItemStateListener != null) {
            mMenuItemStateListener.onStarStateChange(stared, disable);
        }
    }

    private void delete() {
        boolean disable = mTest;
        if (mMenuItemStateListener != null) {
            mMenuItemStateListener.onDeleteStateChange(disable);
        }
    }
}
