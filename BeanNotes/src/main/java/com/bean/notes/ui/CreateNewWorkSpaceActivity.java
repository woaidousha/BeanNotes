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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.actionbarsherlock.app.SherlockActivity;
import com.bean.notes.R;
import com.bean.notes.bean.WorkSpace;
import com.bean.notes.db.BeanNotesDatabaseHelper;
import com.bean.notes.tools.ColorUtil;
import com.bean.notes.tools.LogUtil;
import com.bean.notes.widget.ColorPicker;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class CreateNewWorkSpaceActivity extends SherlockActivity implements View.OnClickListener {

    private static final String TAG = "CreateNewWorkSpaceActivity";

    private ColorPicker mColorPicker;
    private EditText mWorkspaceNameEditor;
    private Button mCreateWorkspaceBtn;

    private WorkspaceNameWatcher mNameWatcher;

    private Dao<WorkSpace, Long> mWorkspaceDao;

    private boolean mWorkspaceExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_workspace_activity);
        try {
            mWorkspaceDao = BeanNotesDatabaseHelper.getInstance().getWorkSpaceDao();
        } catch (SQLException e) {
            LogUtil.e(TAG, "init workspace dao error:", e);
        }
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWorkspaceNameEditor.removeTextChangedListener(mNameWatcher);
    }

    private void initViews() {
        mColorPicker = (ColorPicker) findViewById(R.id.color_picker);
        mWorkspaceNameEditor = (EditText) findViewById(R.id.workspace_name);
        mNameWatcher = new WorkspaceNameWatcher();
        mWorkspaceNameEditor.addTextChangedListener(mNameWatcher);
        mCreateWorkspaceBtn = (Button) findViewById(R.id.create_workspace_btn);
        mCreateWorkspaceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.create_workspace_btn) {
            createNewWorkspace();
        }
    }

    private void createNewWorkspace() {
        if (mWorkspaceExist) {
            return;
        }
        String workspaceName = mWorkspaceNameEditor.getText().toString();
        if (TextUtils.isEmpty(workspaceName)) {
            return;
        }
        int selectorColor = mColorPicker.getSelectColor();
        WorkSpace workSpace = new WorkSpace();
        workSpace.setName(workspaceName);
        workSpace.setColor(ColorUtil.getColorBaseByWorkspaceSelector(selectorColor));
        try {
            mWorkspaceDao.createIfNotExists(workSpace);
            finish();
        } catch (Exception e) {
            LogUtil.e(TAG, "", e);
        }
    }

    private void refreshExistAlert() {
        if (mWorkspaceExist) {
            mWorkspaceNameEditor.setError("has the same workspace");
        } else {
            mWorkspaceNameEditor.setError(null);
        }
    }

    class WorkspaceNameWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String workspaceName = mWorkspaceNameEditor.getText().toString();
            if (TextUtils.isEmpty(workspaceName)) {
                return;
            }
            try {
                List<WorkSpace> workSpaceList = mWorkspaceDao.queryForEq(WorkSpace.COLUMN_NAME, workspaceName);
                int count = workSpaceList.size();
                mWorkspaceExist = count > 0;
                refreshExistAlert();
            } catch (SQLException e) {
                LogUtil.e(TAG, "query same workspace name :" + workspaceName + " error:", e);
            }
        }
    }
}
