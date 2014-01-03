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
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bean.notes.R;
import com.bean.notes.bean.WorkSpace;
import com.bean.notes.tools.ColorUtil;

import java.util.List;

public class WorkSpaceListFragment extends BaseIndexFragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<List<WorkSpace>> {

    private static final int LOADER_WORKSPACES_ID = 0x1001;

    private ListView mWorkSpaceListView;
    private WorkSpaceAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.work_space_list_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mWorkSpaceListView = (ListView) view.findViewById(R.id.workspace_list);
        mWorkSpaceListView.setOnItemClickListener(this);
        mAdapter = new WorkSpaceAdapter();
        mWorkSpaceListView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_WORKSPACES_ID, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getLoaderManager().hasRunningLoaders()) {
            getLoaderManager().restartLoader(LOADER_WORKSPACES_ID, null, this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getLoaderManager().destroyLoader(LOADER_WORKSPACES_ID);
    }

    @Override
    protected int getFragmentIndex() {
        return FM_INDEX_WORKSPACE;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WorkSpace workSpace = (WorkSpace) mAdapter.getItem(position);
        if (workSpace.isCreateNew()) {

        } else {
            if (mSwitchFragmentListener != null) {
                mSwitchFragmentListener.switchFragment(true, workSpace);
            }
        }
    }

    @Override
    public Loader<List<WorkSpace>> onCreateLoader(int i, Bundle bundle) {
        return new WorkSpaceLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<WorkSpace>> listLoader, List<WorkSpace> workSpaces) {
        mAdapter.setData(workSpaces);
        mWorkSpaceListView.invalidate();
    }

    @Override
    public void onLoaderReset(Loader<List<WorkSpace>> listLoader) {
        mAdapter.setData(null);
        mWorkSpaceListView.invalidate();
    }

    private class WorkSpaceAdapter extends BaseAdapter {

        private List<WorkSpace> mWorkSpaces;

        public void setData(List<WorkSpace> workSpaces) {
            mWorkSpaces = workSpaces;
        }

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
