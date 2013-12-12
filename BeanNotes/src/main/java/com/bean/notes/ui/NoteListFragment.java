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
import android.widget.*;
import com.bean.notes.R;
import com.bean.notes.bean.Note;
import com.bean.notes.db.BeanNotesDatabaseHelper;
import com.bean.notes.tools.TimeUtil;

import java.sql.SQLException;
import java.util.List;

public class NoteListFragment extends BaseIndexFragment implements AdapterView.OnItemClickListener {

    private ListView mNotesListView;

    private List<Note> mNotesList;
    private NoteListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_list_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mNotesListView = (ListView) view.findViewById(R.id.note_list);
        mNotesListView.setOnItemClickListener(this);
        mNotesListView.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle bundle = getArguments();
        Switchable switchable = (Switchable) bundle.get(ARGV_SWITCHABLE);
        setSwitchable(switchable);
        BeanNotesDatabaseHelper helper = BeanNotesDatabaseHelper.getInstance();
        try {
            mNotesList = helper.getNoteListDao().queryForEq(Note.COLUMN_PARENT_ID, switchable.getContentId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mAdapter = new NoteListAdapter();
    }

    @Override
    protected int getFragmentIndex() {
        return FM_INDEX_NOTELIST;
    }

    @Override
    public void onClick(View v) {
        if (mSwitchFragmentListener != null) {
            mSwitchFragmentListener.switchFragment(true, null);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = (Note) mAdapter.getItem(position);
        if (mSwitchFragmentListener != null) {
            mSwitchFragmentListener.switchFragment(true, note);
        }
    }

    class NoteListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mNotesList == null ? 0 : mNotesList.size();
        }

        @Override
        public Object getItem(int position) {
            return mNotesList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.notelist_item, null);
                viewHolder = new ViewHolder();
                viewHolder.noteThumb = (ImageView) convertView.findViewById(R.id.note_thumb);
                viewHolder.noteName = (TextView) convertView.findViewById(R.id.note_name);
                viewHolder.noteDescription = (TextView) convertView.findViewById(R.id.note_description);
                viewHolder.noteDate = (TextView) convertView.findViewById(R.id.note_date);
                viewHolder.noteStar = (ImageView) convertView.findViewById(R.id.note_star);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Note note = (Note) getItem(position);
            viewHolder.noteName.setText(note.getName());
            viewHolder.noteDescription.setText(note.getText());
            viewHolder.noteDate.setText(TimeUtil.formatTimeStampString(getActivity(), note.getCreateTime().getTime(), true));
            viewHolder.noteStar.setVisibility(note.getStared() ? View.VISIBLE : View.GONE);
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView noteThumb;
        TextView noteName;
        TextView noteDescription;
        TextView noteDate;
        ImageView noteStar;
    }
}
