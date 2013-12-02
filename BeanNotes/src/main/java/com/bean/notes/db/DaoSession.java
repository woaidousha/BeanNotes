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
package com.bean.notes.db;

import android.database.sqlite.SQLiteDatabase;
import com.bean.notes.bean.WorkSpace;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import java.util.Map;

public class DaoSession extends AbstractDaoSession {
    private final DaoConfig mWorkSpaceDaoConfig;

    private final WorkSpaceDao mWorkSpaceDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        mWorkSpaceDaoConfig = daoConfigMap.get(WorkSpaceDao.class).clone();
        mWorkSpaceDaoConfig.initIdentityScope(type);

        mWorkSpaceDao = new WorkSpaceDao(mWorkSpaceDaoConfig, this);

        registerDao(WorkSpace.class, mWorkSpaceDao);
    }

    public void clear() {
        mWorkSpaceDaoConfig.getIdentityScope().clear();
    }

    public WorkSpaceDao getWorkSpaceDao() {
        return mWorkSpaceDao;
    }

}
