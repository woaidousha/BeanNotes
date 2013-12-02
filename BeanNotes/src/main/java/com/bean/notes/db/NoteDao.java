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
import com.bean.notes.bean.Note;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.internal.DaoConfig;

public class NoteDao extends AbstractDao<Note, Long> {

    public NoteDao(DaoConfig config) {
        super(config);
    }

    public NoteDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'NOTE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'PARENT_ID' INTEGER," + // 1: parent id
                "'NAME' TEXT," + // 2: name
                "'CREATETIME' INTEGER," + // 3: create time
                "'REMINDER' INTEGER," + // 4: reminder
                "'TEXT' TEXT," + // 5: text
                "'LATITUDE' TEXT," + // 6: latitude
                "'LONGITUDE' TEXT);"); // 7: longitude
    }

    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'NOTE'";
        db.execSQL(sql);
    }

    @Override
    protected Note readEntity(Cursor cursor, int i) {
        return null;
    }

    @Override
    protected Long readKey(Cursor cursor, int i) {
        return null;
    }

    @Override
    protected void readEntity(Cursor cursor, Note note, int i) {

    }

    @Override
    protected void bindValues(SQLiteStatement sqLiteStatement, Note note) {

    }

    @Override
    protected Long updateKeyAfterInsert(Note note, long l) {
        return null;
    }

    @Override
    protected Long getKey(Note note) {
        return null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }
}
