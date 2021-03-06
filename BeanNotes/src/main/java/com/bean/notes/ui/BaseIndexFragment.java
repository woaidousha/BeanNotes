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

import android.view.View;
import com.actionbarsherlock.app.SherlockFragment;

public abstract class BaseIndexFragment extends SherlockFragment implements View.OnClickListener {

    public static final String ARGV_SWITCHABLE = "switchable";

    protected static final int FM_INDEX_WORKSPACE   = 0;
    protected static final int FM_INDEX_NOTELIST    = 1;
    protected static final int FM_INDEX_NOTE        = 2;

    protected ISwitchFragmentListener mSwitchFragmentListener;

    protected Switchable mParentSwitchable;

    protected abstract int getFragmentIndex();

    protected void setSwitchFragmentListener(ISwitchFragmentListener switchFragment) {
        this.mSwitchFragmentListener = switchFragment;
    }

    public ISwitchFragmentListener getSwitchFragment() {
        return mSwitchFragmentListener;
    }

    public Switchable getSwitchable() {
        return mParentSwitchable;
    }

    public void setSwitchable(Switchable switchable) {
        this.mParentSwitchable = switchable;
    }
}
