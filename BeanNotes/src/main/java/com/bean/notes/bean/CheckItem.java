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

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "check_items")
public class CheckItem implements IContent {

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PARENT_ID = "parent_id";
    public static final String COLUMN_CREATE_TIME = "createTime";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_CHECKED = "checked";

    public CheckItem() {
    }

    @DatabaseField(generatedId = true, useGetSet = true, columnName = COLUMN_ID)
    private long _id;
    @DatabaseField(useGetSet = true, dataType = DataType.LONG, columnName = COLUMN_PARENT_ID)
    private long parent_id;
    @DatabaseField(useGetSet = true, dataType = DataType.DATE_LONG, columnName = COLUMN_CREATE_TIME)
    private Date createTime;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING, columnName = COLUMN_TEXT)
    private String text;
    @DatabaseField(useGetSet = true, dataType = DataType.BOOLEAN, columnName = COLUMN_CHECKED)
    private boolean checked;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int getContentType() {
        return TYPE_CHECK_ITEM;
    }

    @Override
    public long getModifyDate() {
        return getCreateTime().getTime();
    }
}
