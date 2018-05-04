package com.project.archives.function.main.manager;

import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.dao.Zancuns;
import com.project.archives.common.dao.ZancunsDao;
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

public class ZancunsManager {

    private static ZancunsManager mInstance;
    private ZancunsDao zancunsDao;

    private ZancunsManager() {
        zancunsDao = GreenDaoHelper.getInstance().getDaoSession().getZancunsDao();
    }

    public static ZancunsManager getInstance() {
        if (mInstance == null) {
            mInstance = new ZancunsManager();
        }

        return mInstance;
    }

    public long getCount() {
        return zancunsDao.count();
    }

    public long getCountByQuery(Date startTime, Date endTime) {
        QueryBuilder<Zancuns> queryBuilder = zancunsDao.queryBuilder();
        queryBuilder.where(ZancunsDao.Properties.AddDate.ge(startTime), ZancunsDao.Properties.AddDate.le(endTime));

        return queryBuilder.buildCount().count();
    }

    public List<Zancuns> getZancunList(String userName, String companyName, String startTime, String endTime) {
        Date start = null;
        Date end = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            if (!StringUtils.isEmpty(startTime)) {
                start = format.parse(startTime);
                System.out.println("时间-------");
                System.out.println(start);
                System.out.println(start.toString());
            }
            if (!StringUtils.isEmpty(endTime)) {
                end = format.parse(startTime);
                System.out.println("时间-------");
                System.out.println(end);
                System.out.println(end.toString());
            }

        }
        catch (Exception e) {
            LogUtils.e(CaseInvesManager.class.getName(), e);
        }

        QueryBuilder<Zancuns> queryBuilder = zancunsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(ZancunsDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(ZancunsDao.Properties.Init.like("%" + companyName + "%"));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(start), ZancunsDao.Properties.AddDate.le(end));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(start));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.le(end));
        }

        queryBuilder.orderDesc(ZancunsDao.Properties.UpdateDate);
        return queryBuilder.build().list();
    }

    public List<Zancuns> getZancunsListByAge(String startAge, String endAge) {
        if (!StringUtils.isEmpty(startAge) && !StringUtils.isEmpty(endAge)) {
            return zancunsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge +" and Age<="+ endAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(startAge)) {
            return zancunsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(endAge)) {
            return zancunsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age<="+ endAge + ")")).build().list();
        }

        return zancunsDao.loadAll();
    }
}