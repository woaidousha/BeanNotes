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

import com.bean.notes.ui.Switchable;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "notes")
public class Note implements Switchable, IContent {

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PARENT_ID = "parent_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CREATE_TIME = "createTime";
    public static final String COLUMN_REMINDER = "reminder";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_STARED = "stared";

    public Note() {
    }

    @DatabaseField(generatedId = true, useGetSet = true, columnName = COLUMN_ID)
    private long _id;
    @DatabaseField(useGetSet = true, dataType = DataType.LONG, columnName = COLUMN_PARENT_ID)
    private long parent_id;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING, columnName = COLUMN_NAME)
    private String name;
    @DatabaseField(useGetSet = true, dataType = DataType.DATE_LONG, columnName = COLUMN_CREATE_TIME)
    private Date createTime;
    @DatabaseField(useGetSet = true, dataType = DataType.BOOLEAN, columnName = COLUMN_REMINDER)
    private boolean reminder;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING, columnName = COLUMN_TEXT)
    private String text;
    @DatabaseField(useGetSet = true, dataType = DataType.DOUBLE, columnName = COLUMN_LATITUDE)
    private double latitude;
    @DatabaseField(useGetSet = true, dataType = DataType.DOUBLE, columnName = COLUMN_LONGITUDE)
    private double longitude;
    @DatabaseField(useGetSet = true, dataType = DataType.BOOLEAN, columnName = COLUMN_STARED)
    private boolean stared;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean getReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean getStared() {
        return stared;
    }

    public void setStared(boolean stared) {
        this.stared = stared;
    }

    @Override
    public long getContentId() {
        return _id;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public Integer getActivityColor() {
        return null;
    }

    @Override
    public String toString() {
        return "NoteFragment{" +
                "_id=" + _id +
                ", parent_id=" + parent_id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", reminder=" + reminder +
                ", text='" + text + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", stared=" + stared +
                '}';
    }

    @Override
    public int getContentType() {
        return TYPE_TEXT;
    }

    @Override
    public long getModifyDate() {
        return getCreateTime().getTime();
    }
}
