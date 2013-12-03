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

@DatabaseTable(tableName = "medias")
public class Media {

    public Media() {
    }

    @DatabaseField(generatedId = true, useGetSet = true)
    private long _id;
    @DatabaseField(useGetSet = true, dataType = DataType.LONG)
    private long parent_id;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING)
    private String mime_type;
    @DatabaseField(useGetSet = true, dataType = DataType.DATE_LONG)
    private Date createTime;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING)
    private String _data;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING)
    private String media_small;
    @DatabaseField(useGetSet = true, dataType = DataType.STRING)
    private String media_middle;

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

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public String getMedia_small() {
        return media_small;
    }

    public void setMedia_small(String media_small) {
        this.media_small = media_small;
    }

    public String getMedia_middle() {
        return media_middle;
    }

    public void setMedia_middle(String media_middle) {
        this.media_middle = media_middle;
    }
}
