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
package com.bean.notes.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bean.notes.R;

public class ColorPickerItem extends RelativeLayout {

    private ImageView mColorBg;
    private View mCorner;

    public ColorPickerItem(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.color_picker_item, this);
        mColorBg = (ImageView) findViewById(R.id.color_bg);
        mCorner = findViewById(R.id.corner);
    }

    public void setColor(int colorRes) {
        mColorBg.setImageResource(colorRes);
    }

    public void setSelected(boolean selected) {
        mCorner.setVisibility(selected ? View.VISIBLE : View.INVISIBLE);
        setBackgroundResource(selected ? R.drawable.color_picker_frame_selected : R.drawable.color_picker_frame);
    }
}
