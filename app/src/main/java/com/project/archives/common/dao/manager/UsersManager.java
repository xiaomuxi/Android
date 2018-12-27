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
        return usersDao.queryBuilder().where(UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getQuGuanGanBuCount(String init, int type) {
        if (!StringUtils.isEmpty(init)) {
            return usersDao.queryBuilder().where(UsersDao.Properties.Init.eq(init), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }
        if (type != 0) {
            return usersDao.queryBuilder().where(UsersDao.Properties.CbInit.eq(type), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        return usersDao.queryBuilder().where(UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getDangDaiBiaoCount(String init, int type) {
        if (!StringUtils.isEmpty(init)) {
            return usersDao.queryBuilder().where(UsersDao.Properties.Init.eq(init), UsersDao.Properties.IsDang.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }
        if (type != 0) {
            return usersDao.queryBuilder().where(UsersDao.Properties.CbInit.eq(type), UsersDao.Properties.IsDang.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        return usersDao.queryBuilder().where(UsersDao.Properties.IsDang.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getQuWeiWeiYuanCount(String init, int type) {
        if (!StringUtils.isEmpty(init)) {
            return usersDao.queryBuilder().where(UsersDao.Properties.Init.eq(init), UsersDao.Properties.IsDangW.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        if (type != 0) {
            return usersDao.queryBuilder().where(UsersDao.Properties.CbInit.eq(type), UsersDao.Properties.IsDangW.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        return usersDao.queryBuilder().where(UsersDao.Properties.IsDangW.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getJiWeiCount(String init, int type) {
        if (!StringUtils.isEmpty(init)) {
            return usersDao.queryBuilder().where(UsersDao.Properties.Init.eq(init), UsersDao.Properties.IsDangJ.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        if (type != 0) {
            return usersDao.queryBuilder().where(UsersDao.Properties.CbInit.eq(type), UsersDao.Properties.IsDangJ.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        return usersDao.queryBuilder().where(UsersDao.Properties.IsDangJ.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getRenDaCount(String init, int type) {
        if (!StringUtils.isEmpty(init)) {
            return usersDao.queryBuilder().where(UsersDao.Properties.Init.eq(init), UsersDao.Properties.IsRen.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        if (type != 0) {
            return usersDao.queryBuilder().where(UsersDao.Properties.CbInit.eq(type), UsersDao.Properties.IsRen.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        return usersDao.queryBuilder().where(UsersDao.Properties.IsRen.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getZhengXieCount(String init, int type) {
        if (!StringUtils.isEmpty(init)) {
            return usersDao.queryBuilder().where(UsersDao.Properties.Init.eq(init), UsersDao.Properties.IsZheng.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        if (type != 0) {
            return usersDao.queryBuilder().where(UsersDao.Properties.CbInit.eq(type), UsersDao.Properties.IsZheng.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        return usersDao.queryBuilder().where(UsersDao.Properties.IsZheng.eq(1), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getNvRenCount(String init, int type) {
        if (!StringUtils.isEmpty(init)) {
            return usersDao.queryBuilder().where(UsersDao.Properties.Init.eq(init), UsersDao.Properties.Sex.eq(2), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        if (type != 0) {
            return usersDao.queryBuilder().where(UsersDao.Properties.CbInit.eq(type), UsersDao.Properties.Sex.eq(2), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        return usersDao.queryBuilder().where(UsersDao.Properties.Sex.eq(2), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getNotHanZuCount(String init, int type) {
        if (!StringUtils.isEmpty(init)) {
            return usersDao.queryBuilder().where(UsersDao.Properties.Init.eq(init), UsersDao.Properties.National.notEq("汉族"), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        if (type != 0) {
            return usersDao.queryBuilder().where(UsersDao.Properties.CbInit.eq(type), UsersDao.Properties.National.notEq("汉族"), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
        }

        return usersDao.queryBuilder().where(UsersDao.Properties.National.notEq("汉族"), UsersDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public List<Users> getUserList(String userName, String startAge, String endAge, List<String> companys) {

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
        if (companys != null) {
            queryBuilder.where(UsersDao.Properties.Init.in(companys));
        }

        queryBuilder.where(UsersDao.Properties.IsDelete.eq(1));
        queryBuilder.orderAsc(UsersDao.Properties.GerenID);

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
