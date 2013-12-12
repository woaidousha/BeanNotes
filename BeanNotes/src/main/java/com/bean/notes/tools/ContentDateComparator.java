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
package com.bean.notes.tools;

import com.bean.notes.bean.IContent;

import java.util.Comparator;

public class ContentDateComparator implements Comparator<IContent> {
    @Override
    public int compare(IContent lhs, IContent rhs) {
        if (lhs == null) {
            return -1;
        }
        if (rhs == null) {
            return 1;
        }
        if (lhs.getModifyDate() > rhs.getModifyDate()) {
            return 1;
        } else {
            return -1;
        }
    }
}
