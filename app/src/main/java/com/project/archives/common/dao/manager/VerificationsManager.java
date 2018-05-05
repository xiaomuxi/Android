package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.dao.Verifications;
import com.project.archives.common.dao.VerificationsDao;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by inrokei on 2018/4/30.
 */

public class VerificationsManager {
    private static VerificationsManager mInstance;
    private VerificationsDao verificationsDao;

    private VerificationsManager() {
        verificationsDao = GreenDaoHelper.getInstance().getDaoSession().getVerificationsDao();
    }

    public static VerificationsManager getInstance() {
        if (mInstance == null) {
            mInstance = new VerificationsManager();
        }

        return mInstance;
    }

    public long getCount() {
        return verificationsDao.count();
    }

    public long getCountByQuery(Date startTime, Date endTime) {
        QueryBuilder<Verifications> queryBuilder = verificationsDao.queryBuilder();
        queryBuilder.where(VerificationsDao.Properties.AddDate.ge(startTime), VerificationsDao.Properties.AddDate.le(endTime));

        return queryBuilder.buildCount().count();
    }

    public long getCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }

        return verificationsDao.queryBuilder().where(VerificationsDao.Properties.Name.eq(name)).buildCount().count();

    }

    public List<Verifications> getVerificationList(String userName, String companyName, String startTime, String endTime) {
        Date start = null;
        Date end = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            if (!StringUtils.isEmpty(startTime)) {
                start = format.parse(startTime);
            }
            if (!StringUtils.isEmpty(endTime)) {
                end = format.parse(startTime);
            }
        }
        catch (Exception e) {
            LogUtils.e(CaseInvesManager.class.getName(), e);
        }

        QueryBuilder<Verifications> queryBuilder = verificationsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(VerificationsDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(VerificationsDao.Properties.Init.like("%" + companyName + "%"));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(start), VerificationsDao.Properties.AddDate.le(end));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(start));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.le(end));
        }

        queryBuilder.orderDesc(VerificationsDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<Verifications> getVerificationsListByAge(String startAge, String endAge) {
        if (!StringUtils.isEmpty(startAge) && !StringUtils.isEmpty(endAge)) {
            return verificationsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge +" and Age<="+ endAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(startAge)) {
            return verificationsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(endAge)) {
            return verificationsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age<="+ endAge + ")")).build().list();
        }

        return verificationsDao.loadAll();
    }
}
