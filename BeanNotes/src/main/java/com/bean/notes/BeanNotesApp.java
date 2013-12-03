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
package com.bean.notes;

import android.app.Application;
import com.bean.notes.db.BeanNotesDatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class BeanNotesApp extends Application {

    private static BeanNotesApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        BeanNotesDatabaseHelper helper = OpenHelperManager.getHelper(this, BeanNotesDatabaseHelper.class);
    }

    public static synchronized BeanNotesApp getInstance() {
        return sInstance;
    }
}
