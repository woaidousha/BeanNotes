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

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.bean.notes.bean.WorkSpace;
import com.bean.notes.db.BeanNotesDatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkSpaceLoader extends AsyncTaskLoader<List<WorkSpace>> {

    public WorkSpaceLoader(Context context) {
        super(context);
    }

    @Override
    public List<WorkSpace> loadInBackground() {
        List<WorkSpace> workSpaces = null;
        try {
            workSpaces = BeanNotesDatabaseHelper.getInstance().getWorkSpaceDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (workSpaces == null) {
            workSpaces = new ArrayList<WorkSpace>();
        }
        workSpaces.add(WorkSpace.getAllWorkSpace(getContext()));
        workSpaces.add(WorkSpace.getCreateNewWorkSpace(getContext()));
        return workSpaces;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

}
