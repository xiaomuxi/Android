package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.Endings;
import com.project.archives.common.dao.EndingsDao;
import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by inrokei on 2018/4/30.
 */

public class EndingsManager {
    private static EndingsManager mInstance;
    private EndingsDao endingsDao;

    private EndingsManager() {
        endingsDao = GreenDaoHelper.getInstance().getDaoSession().getEndingsDao();
    }

    public static EndingsManager getInstance() {
        if (mInstance == null) {
            mInstance = new EndingsManager();
        }

        return mInstance;
    }

    public long getCount() {
        return endingsDao.count();
    }

//    public long getCountByQuery(Date startTime, Date endTime) {
//        QueryBuilder<Endings> queryBuilder = endingsDao.queryBuilder();
//        queryBuilder.where(EndingsDao.Properties.AddDate.ge(startTime), EndingsDao.Properties.AddDate.le(endTime));
//
//        return queryBuilder.buildCount().count();
//    }

    public long getCountByQuery(String company, String startTime, String endTime) {
        QueryBuilder<Endings> queryBuilder = endingsDao.queryBuilder();

        if (!StringUtils.isEmpty(company)) {
            queryBuilder.where(EndingsDao.Properties.Init.eq(company));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(EndingsDao.Properties.AddDate.ge(startTime), EndingsDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(EndingsDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(EndingsDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.buildCount().count();
    }


    public long getCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }

        return endingsDao.queryBuilder().where(EndingsDao.Properties.Name.eq(name)).buildCount().count();

    }

    public List<Endings> getEndingList(String userName, String companyName, String startTime, String endTime) {

        QueryBuilder<Endings> queryBuilder = endingsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(EndingsDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(EndingsDao.Properties.Init.like("%" + companyName + "%"));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(EndingsDao.Properties.AddDate.ge(startTime), EndingsDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(EndingsDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(EndingsDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.orderDesc(EndingsDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<Endings> getEndingsListByAge(String  startAge, String endAge) {
        if (!StringUtils.isEmpty(startAge) && !StringUtils.isEmpty(endAge)) {
            return endingsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge +" and Age<="+ endAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(startAge)) {
            return endingsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(endAge)) {
            return endingsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age<="+ endAge + ")")).build().list();
        }

        return endingsDao.loadAll();
    }
}
