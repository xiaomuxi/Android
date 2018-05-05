package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.dao.Letters;
import com.project.archives.common.dao.LettersDao;
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

public class LettersManager {
    private static LettersManager mInstance;
    private LettersDao lettersDao;

    private LettersManager() {
        lettersDao = GreenDaoHelper.getInstance().getDaoSession().getLettersDao();
    }

    public static LettersManager getInstance() {
        if (mInstance == null) {
            mInstance = new LettersManager();
        }

        return mInstance;
    }

    public long getCount() {
        return lettersDao.count();
    }

    public long getCountByQuery(Date startTime, Date endTime) {
        QueryBuilder<Letters> queryBuilder = lettersDao.queryBuilder();
        queryBuilder.where(LettersDao.Properties.AddDate.ge(startTime), LettersDao.Properties.AddDate.le(endTime));

        return queryBuilder.buildCount().count();
    }

    public long getCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }

        return lettersDao.queryBuilder().where(LettersDao.Properties.Name.eq(name)).buildCount().count();

    }

    public List<Letters> getLetterList(String userName, String companyName, String startTime, String endTime) {
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

        QueryBuilder<Letters> queryBuilder = lettersDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(LettersDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(LettersDao.Properties.Init.like("%" + companyName + "%"));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(start), LettersDao.Properties.AddDate.le(end));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(start));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.le(end));
        }

        queryBuilder.orderDesc(LettersDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<Letters> getLettersListByAge(String startAge, String endAge) {
        if (!StringUtils.isEmpty(startAge) && !StringUtils.isEmpty(endAge)) {
            return lettersDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge +" and Age<="+ endAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(startAge)) {
            return lettersDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(endAge)) {
            return lettersDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age<="+ endAge + ")")).build().list();
        }

        return lettersDao.loadAll();
    }
}
