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

import com.bean.notes.BeanNotesApp;
import com.bean.notes.R;

public class ColorUtil {

    public static final int COLOR_BASE_0 = 0;
    public static final int COLOR_BASE_1 = 1;
    public static final int COLOR_BASE_2 = 2;
    public static final int COLOR_BASE_3 = 3;
    public static final int COLOR_BASE_4 = 4;
    public static final int COLOR_BASE_5 = 5;
    public static final int COLOR_BASE_ALL = 6;

    public static int getWorkSpaceCellSelector(int colorBase) {
        int selectorRes = R.drawable.workspace_list_item_base_0_selector;
        switch (colorBase) {
            case COLOR_BASE_1:
                selectorRes = R.drawable.workspace_list_item_base_1_selector;
                break;
            case COLOR_BASE_2:
                selectorRes = R.drawable.workspace_list_item_base_2_selector;
                break;
            case COLOR_BASE_3:
                selectorRes = R.drawable.workspace_list_item_base_3_selector;
                break;
            case COLOR_BASE_4:
                selectorRes = R.drawable.workspace_list_item_base_4_selector;
                break;
            case COLOR_BASE_5:
                selectorRes = R.drawable.workspace_list_item_base_5_selector;
                break;
            case COLOR_BASE_ALL:
                selectorRes = R.drawable.workspace_list_item_all_selector;
                break;
        }
        return selectorRes;
    }

    public static int getActionBarAndBottomBg(int colorBase) {
        int colorRes = R.color.workspace_list_item_bg_base_0_gradient_start;
        switch (colorBase) {
            case COLOR_BASE_1:
                colorRes = R.color.workspace_list_item_bg_base_1_gradient_start;
                break;
            case COLOR_BASE_2:
                colorRes = R.color.workspace_list_item_bg_base_2_gradient_start;
                break;
            case COLOR_BASE_3:
                colorRes = R.color.workspace_list_item_bg_base_3_gradient_start;
                break;
            case COLOR_BASE_4:
                colorRes = R.color.workspace_list_item_bg_base_4_gradient_start;
                break;
            case COLOR_BASE_5:
                colorRes = R.color.workspace_list_item_bg_base_5_gradient_start;
                break;
            case COLOR_BASE_ALL:
                colorRes = R.color.workspace_all_notes_gradient_start;
                break;
        }
        return BeanNotesApp.getInstance().getResources().getColor(colorRes);
    }

}
