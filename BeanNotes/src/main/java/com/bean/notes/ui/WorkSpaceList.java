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
package com.bean.notes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bean.notes.R;
import com.bean.notes.bean.WorkSpace;
import com.bean.notes.db.BeanNotesDatabaseHelper;
import com.bean.notes.tools.ColorUtil;

import java.sql.SQLException;
import java.util.List;

public class WorkSpaceList extends BaseIndexFragment {

    private ListView mWorkSpaceListView;
    private List<WorkSpace> mWorkSpaces;
    private WorkSpaceAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.work_space_list_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mWorkSpaceListView = (ListView) view.findViewById(R.id.workspace_list);
        mAdapter = new WorkSpaceAdapter();
        try {
            mWorkSpaces = BeanNotesDatabaseHelper.getInstance().getWorkSpaceDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mWorkSpaceListView.setAdapter(mAdapter);
    }

    @Override
    protected int getFragmentIndex() {
        return FM_INDEX_WORKSPACE;
    }

    @Override
    public void onClick(View v) {
        if (mSwitchFragmentListener != null) {
            mSwitchFragmentListener.switchFragment(true);
        }
    }

    private class WorkSpaceAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mWorkSpaces == null ? 0 : mWorkSpaces.size();
        }

        @Override
        public Object getItem(int position) {
            return mWorkSpaces.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.workspace_item, null);
                holder = new ViewHolder();
                holder.mIcon = (ImageView) convertView.findViewById(R.id.workspace_icon);
                holder.mName = (TextView) convertView.findViewById(R.id.workspace_name);
                holder.mDescription = (TextView) convertView.findViewById(R.id.workspace_description);
                holder.mCount = (TextView) convertView.findViewById(R.id.workspace_count);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            WorkSpace workSpace = (WorkSpace) getItem(position);
            holder.mName.setText(workSpace.getName());
            holder.mDescription.setText(workSpace.getDescription());
            holder.mCount.setText(workSpace.getCount() + "");
            convertView.setBackgroundResource(ColorUtil.getWorkSpaceCellSelector(workSpace.getColor()));
            return convertView;
        }

    }

    static class ViewHolder {
        private ImageView mIcon;
        private TextView mName;
        private TextView mDescription;
        private TextView mCount;
    }
}
