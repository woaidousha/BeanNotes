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
package com.bean.notes.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "workspace_note_map")
public class WorkSpaceNoteMap {

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORK_SPACE_ID = "work_space_id";
    public static final String COLUMN_NOTE_ID = "note_id";

    public WorkSpaceNoteMap() {
    }

    @DatabaseField(generatedId = true, useGetSet = true, columnName = COLUMN_ID)
    private long _id;
    @DatabaseField(useGetSet = true, columnName = COLUMN_WORK_SPACE_ID, foreignAutoRefresh = true, foreign = true)
    private WorkSpace workSpace;
    @DatabaseField(useGetSet = true, columnName = COLUMN_NOTE_ID, foreignAutoRefresh = true, foreign = true)
    private Note note;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public WorkSpace getWorkSpace() {
        return workSpace;
    }

    public void setWorkSpace(WorkSpace workSpace) {
        this.workSpace = workSpace;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "WorkSpaceNoteMap{" +
                "_id=" + _id +
                ", workSpace=" + workSpace +
                ", note=" + note +
                '}';
    }
}
