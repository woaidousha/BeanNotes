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
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.bean.notes.R;
import com.bean.notes.widget.OperatorBar;

import static com.bean.notes.tools.Constant.OperatorMenu.*;

public class MainActivity extends SherlockFragmentActivity implements OperatorBar.OnMenuItemClickListener, View.OnClickListener {

    private OperatorBar mOperatorBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mOperatorBar = (OperatorBar) findViewById(R.id.operator_bar);
        mOperatorBar.setOnMenuItemClickListener(this);
    }

    @Override
    public void onMenuItemClick(int menuIndex) {
        switch (menuIndex) {
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
    }
}
