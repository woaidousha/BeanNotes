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
package com.bean.notes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.bean.notes.BeanNotesApp;
import com.bean.notes.bean.CheckItem;
import com.bean.notes.bean.Media;
import com.bean.notes.bean.Note;
import com.bean.notes.bean.WorkSpace;
import com.bean.notes.tools.ColorUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class BeanNotesDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "notes.db";
    private static final int VERSION = 1;

    private static BeanNotesDatabaseHelper sInstance;

    private Dao<WorkSpace, Long> mWorkSpaceDao;

    public BeanNotesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public BeanNotesDatabaseHelper() {
        this(BeanNotesApp.getInstance());
    }

    public static BeanNotesDatabaseHelper getInstance() {
        if (sInstance == null) {
            sInstance = new BeanNotesDatabaseHelper(BeanNotesApp.getInstance());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, WorkSpace.class);
            TableUtils.createTable(connectionSource, Note.class);
            TableUtils.createTable(connectionSource, CheckItem.class);
            TableUtils.createTable(connectionSource, Media.class);
            initFirstLaunchData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
    }

    private void initFirstLaunchData() throws SQLException {
        WorkSpace workSpace = new WorkSpace();
        workSpace.setColor(ColorUtil.COLOR_BASE_0);
        workSpace.setCount(0);
        workSpace.setInited(true);
        workSpace.setName("My Ideas");
        getWorkSpaceDao().createIfNotExists(workSpace);
        workSpace = new WorkSpace();
        workSpace.setColor(ColorUtil.COLOR_BASE_1);
        workSpace.setCount(0);
        workSpace.setInited(true);
        workSpace.setName("Local Connection");
        getWorkSpaceDao().createIfNotExists(workSpace);
    }

    public Dao<WorkSpace, Long> getWorkSpaceDao() throws SQLException {
        if (mWorkSpaceDao == null) {
            mWorkSpaceDao = getDao(WorkSpace.class);
        }
        return mWorkSpaceDao;
    }
}
