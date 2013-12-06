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
public class Note implements Switchable {

    public Note() {
    }

    @DatabaseField(generatedId = true, useGetSet = true)
    private long _id;
    @DatabaseField(useGetSet = true, dataType = DataType.LONG)
    private long parent_id;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING)
    private String name;
    @DatabaseField(useGetSet = true, dataType = DataType.DATE_LONG)
    private Date createTime;
    @DatabaseField(useGetSet = true, dataType = DataType.BOOLEAN)
    private boolean reminder;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING)
    private String text;
    @DatabaseField(useGetSet = true, dataType = DataType.DOUBLE)
    private double latitude;
    @DatabaseField(useGetSet = true, dataType = DataType.DOUBLE)
    private double longitude;

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
}
