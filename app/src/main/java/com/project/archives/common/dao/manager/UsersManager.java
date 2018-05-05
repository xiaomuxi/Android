package com.project.archives.common.dao.manager;

import android.database.Cursor;

import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.dao.Users;
import com.project.archives.common.dao.UsersDao;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/4/30.
 */

public class UsersManager {
    private static UsersManager mInstance;
    private UsersDao usersDao;

    private UsersManager() {
        usersDao = GreenDaoHelper.getInstance().getDaoSession().getUsersDao();
    }

    public static UsersManager getInstance() {
        if (mInstance == null) {
            mInstance = new UsersManager();
        }

        return mInstance;
    }

    public long getCount() {
        return usersDao.count();
    }

    public List<Users> getUserList(String userName, String startAge, String endAge) {

        QueryBuilder<Users> queryBuilder = usersDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(UsersDao.Properties.RealName.like("%" +userName + "%"));
        }

        if (!StringUtils.isEmpty(startAge) && !StringUtils.isEmpty(endAge)) {
            queryBuilder.where(UsersDao.Properties.Age.ge(startAge), UsersDao.Properties.Age.le(endAge));
        }
        else if(!StringUtils.isEmpty(startAge)) {
            queryBuilder.where(UsersDao.Properties.Age.ge(startAge));
        }
        else if(!StringUtils.isEmpty(endAge)) {
            queryBuilder.where(UsersDao.Properties.Age.le(endAge));
        }

        queryBuilder.orderDesc(UsersDao.Properties.AddDate);

        return queryBuilder.list();
    }

    public ArrayList<String> getAllCompany(int companyType) {
        String SQL_DISTINCT_COMPANY = "SELECT DISTINCT " + UsersDao.Properties.Init.columnName+" FROM "+UsersDao.TABLENAME;
        String COMPANY_TYPE = " WHERE " + UsersDao.Properties.CbInit.columnName + "=" + companyType;
        if (companyType > 0) {
            SQL_DISTINCT_COMPANY = SQL_DISTINCT_COMPANY + COMPANY_TYPE;
        }

        ArrayList<String> result = new ArrayList<String>();
        Cursor c = GreenDaoHelper.getInstance().getDaoSession().getDatabase().rawQuery(SQL_DISTINCT_COMPANY, null);
        try{
            if (c.moveToFirst()) {
                do {
                    result.add(c.getString(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }

        return result;
    }

}
