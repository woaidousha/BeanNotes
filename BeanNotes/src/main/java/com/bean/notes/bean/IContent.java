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

public interface IContent {

    public static final int TYPE_UNKNOW         = 0;
    public static final int TYPE_TEXT           = 1;
    public static final int TYPE_IMAGE          = 2;
    public static final int TYPE_AUDIO          = 3;
    public static final int TYPE_REMINDER       = 4;
    public static final int TYPE_CHECK_ITEM     = 5;
    public static final int TYPE_CHECK_ITEMS    = 6;

    public static final int VIEW_TYPE_COUNT     = 5;

    public int getContentType();

    public long getModifyDate();
}
