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

import com.bean.notes.tools.ContentDateComparator;

import java.util.Collections;
import java.util.List;

public class CheckItems implements IContent {

    private List<CheckItem> mCheckItems;

    public List<CheckItem> getCheckItems() {
        return mCheckItems;
    }

    public void setCheckItems(List<CheckItem> mCheckItems) {
        this.mCheckItems = mCheckItems;
    }

    @Override
    public int getContentType() {
        return TYPE_CHECK_ITEMS;
    }

    @Override
    public long getModifyDate() {
        if (mCheckItems != null && mCheckItems.size() > 0) {
            Collections.sort(mCheckItems, new ContentDateComparator());
            return mCheckItems.get(0).getCreateTime().getTime();
        }
        return -1;
    }
}
