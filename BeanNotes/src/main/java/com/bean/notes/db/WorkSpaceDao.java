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

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.bean.notes.bean.WorkSpace;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.internal.DaoConfig;

public class WorkSpaceDao extends AbstractDao<WorkSpace, Long> {

    public WorkSpaceDao(DaoConfig config) {
        super(config);
    }

    public WorkSpaceDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'WORKSPACE' (" + //
                   "'_id' INTEGER PRIMARY KEY ," + // 0: id
                   "'NAME' TEXT NOT NULL ," + // 1: text
                   "'SECURITY' INTEGER," + // 2: SECURITY
                   "'COLOR' INTEGER," + // 3: COLOR
                   "'COUNT' INTEGER," + // 4: COUNT
                   "'DESCRIPTION' TEXT);"); // 5: DESCRIPTION
    }

    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'WORKSPACE'";
        db.execSQL(sql);
    }


    @Override
    protected WorkSpace readEntity(Cursor cursor, int i) {
        return null;
    }

    @Override
    protected Long readKey(Cursor cursor, int i) {
        return null;
    }

    @Override
    protected void readEntity(Cursor cursor, WorkSpace workSpace, int i) {

    }

    @Override
    protected void bindValues(SQLiteStatement sqLiteStatement, WorkSpace workSpace) {

    }

    @Override
    protected Long updateKeyAfterInsert(WorkSpace workSpace, long l) {
        return null;
    }

    @Override
    protected Long getKey(WorkSpace workSpace) {
        return null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }
}
