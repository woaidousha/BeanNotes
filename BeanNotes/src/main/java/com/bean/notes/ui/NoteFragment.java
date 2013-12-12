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

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.bean.notes.R;
import com.bean.notes.bean.*;
import com.bean.notes.db.BeanNotesDatabaseHelper;
import com.bean.notes.tools.ContentDateComparator;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteFragment extends BaseIndexFragment {

    private IMenuItemStateListener mMenuItemStateListener;

    private List<IContent> mNoteContents;
    private CheckItems mCheckItems;
    private Note mNote;

    private Dao<Media, Long> mMediaDao;
    private Dao<CheckItem, Long> mCheckItemDao;

    private boolean mTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        star(mNote.getStared());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mMediaDao = BeanNotesDatabaseHelper.getInstance().getMediaDao();
            mCheckItemDao = BeanNotesDatabaseHelper.getInstance().getCheckItemDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Bundle bundle = getArguments();
        Switchable switchable = (Switchable) bundle.get(ARGV_SWITCHABLE);
        setSwitchable(switchable);
        mNote = (Note) switchable;
        try {
            mNoteContents = new ArrayList<IContent>();
            List<Media> medias = mMediaDao.queryForEq(Media.COLUMN_PARENT_ID, mNote.getContentId());
            if (medias != null && medias.size() > 0) {
                mNoteContents.addAll(medias);
            }
            List<CheckItem> items = mCheckItemDao.queryForEq(CheckItem.COLUMN_PARENT_ID, mNote.getContentId());
            if (items != null && items.size() > 0) {
                mCheckItems = new CheckItems();
                mCheckItems.setCheckItems(items);
            }
            Collections.sort(mNoteContents, new ContentDateComparator());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getFragmentIndex() {
        return FM_INDEX_NOTE;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.bottom_bar_attach) {
        } else if (viewId == R.id.bottom_bar_share) {
        } else if (viewId == R.id.bottom_bar_star) {
        } else if (viewId == R.id.bottom_bar_delete) {
        }
    }

    public void setMenuItemStateListener(IMenuItemStateListener menuItemStateListener) {
        this.mMenuItemStateListener = menuItemStateListener;
    }

    private void attach() {
        boolean disable = mTest;
        if (mMenuItemStateListener != null) {
            mMenuItemStateListener.onAttachStateChange(disable);
        }
    }

    private void share() {
        boolean disable = mTest;
        if (mMenuItemStateListener != null) {
            mMenuItemStateListener.onShareStateChange(disable);
        }
    }

    private void star(boolean stared) {
        boolean disable = mTest;
        if (mMenuItemStateListener != null) {
            mMenuItemStateListener.onStarStateChange(stared, disable);
        }
    }

    private void delete() {
        boolean disable = mTest;
        if (mMenuItemStateListener != null) {
            mMenuItemStateListener.onDeleteStateChange(disable);
        }
    }

    class NoteContentAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mNoteContents == null ? 0 : mNoteContents.size();
        }

        @Override
        public Object getItem(int position) {
            return mNoteContents.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return null;
        }

        @Override
        public int getViewTypeCount() {
            return IContent.VIEW_TYPE_COUNT;
        }

        @Override
        public int getItemViewType(int position) {
            IContent content = (IContent) getItem(position);
            return content.getContentType();
        }

    }
}
