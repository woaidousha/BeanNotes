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

@DatabaseTable(tableName = "workspace")
public class WorkSpace {

    public WorkSpace() {
    }

    @DatabaseField(generatedId = true, useGetSet = true)
    private Long _id;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING)
    private String name;
    @DatabaseField(useGetSet = true, dataType = DataType.BOOLEAN)
    private boolean security;
    @DatabaseField(useGetSet = true, dataType = DataType.INTEGER)
    private int color;
    @DatabaseField(useGetSet = true, dataType = DataType.INTEGER)
    private int count;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING)
    private String description;
    @DatabaseField(useGetSet = true, dataType = DataType.BOOLEAN)
    private boolean inited;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSecurity() {
        return security;
    }

    public void setSecurity(boolean security) {
        this.security = security;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getInited() {
        return inited;
    }

    public void setInited(boolean inited) {
        this.inited = inited;
    }

    @Override
    public String toString() {
        return "WorkSpace{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", security=" + security +
                ", color=" + color +
                ", count=" + count +
                ", description='" + description + '\'' +
                '}';
    }
}
