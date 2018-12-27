package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.DutyReports;
import com.project.archives.common.dao.DutyReportsDao;
import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by inrokei on 2018/4/30.
 */

public class DutyReportsManager {
    private static DutyReportsManager mInstance;
    private DutyReportsDao dutyReportsDao;

    private DutyReportsManager() {
        dutyReportsDao = GreenDaoHelper.getInstance().getDaoSession().getDutyReportsDao();
    }

    public static DutyReportsManager getInstance() {
        if (mInstance == null) {
            mInstance = new DutyReportsManager();
        }

        return mInstance;
    }

    public long getCount() {
        return dutyReportsDao.queryBuilder().where(DutyReportsDao.Properties.IsDelete.eq("1")).buildCount().count();
    }

    public long getCountByQueryWithCompanys(List<String> companys, String startTime, String endTime) {
        QueryBuilder<DutyReports> queryBuilder = dutyReportsDao.queryBuilder();

        if (companys != null) {
            queryBuilder.where(DutyReportsDao.Properties.Init.in(companys));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.ge(startTime), DutyReportsDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(DutyReportsDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.where(DutyReportsDao.Properties.IsDelete.eq("1")).buildCount().count();
    }

    public long getCountByQuery(String company, String startTime, String endTime) {
        QueryBuilder<DutyReports> queryBuilder = dutyReportsDao.queryBuilder();

        if (!StringUtils.isEmpty(company)) {
            queryBuilder.where(DutyReportsDao.Properties.Init.eq(company));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.ge(startTime), DutyReportsDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(DutyReportsDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.where(DutyReportsDao.Properties.IsDelete.eq("1")).buildCount().count();
    }


    public long getCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }

        return dutyReportsDao.queryBuilder().where(DutyReportsDao.Properties.Name.eq(name), DutyReportsDao.Properties.IsDelete.eq("1")).buildCount().count();

    }

    public List<DutyReports> getDutyReportsList(String userName, String companyName, String startTime, String endTime) {

        QueryBuilder<DutyReports> queryBuilder = dutyReportsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(DutyReportsDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(DutyReportsDao.Properties.Init.eq(companyName));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.ge(startTime), DutyReportsDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.orderDesc(DutyReportsDao.Properties.AddDate);

        return queryBuilder.list();
    }

    public List<DutyReports> getDutyReportsListWithCompanys(String userName, List<String> companyNames, String startTime, String endTime) {

        QueryBuilder<DutyReports> queryBuilder = dutyReportsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(DutyReportsDao.Properties.Name.eq(userName));
        }

        if (companyNames != null) {
            queryBuilder.where(DutyReportsDao.Properties.Init.in(companyNames));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.ge(startTime), DutyReportsDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(DutyReportsDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.where(DutyReportsDao.Properties.IsDelete.eq("1"));
        queryBuilder.orderDesc(DutyReportsDao.Properties.AddDate);

        return queryBuilder.list();
    }

    public List<DutyReports> getDutyReportListByName(String name) {

        QueryBuilder<DutyReports> queryBuilder = dutyReportsDao.queryBuilder();
        if (!StringUtils.isEmpty(name)) {
            queryBuilder.where(DutyReportsDao.Properties.Name.like("%" + name + "%"));
        }

        queryBuilder.where(DutyReportsDao.Properties.IsDelete.eq("1"));
        queryBuilder.orderDesc(DutyReportsDao.Properties.AddDate);

        return queryBuilder.list();
    }

    public List<DutyReports> getDutyReportsListByAge(String startAge, String endAge) {
        if (!StringUtils.isEmpty(startAge) && !StringUtils.isEmpty(endAge)) {
            return dutyReportsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge +" and Age<="+ endAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(startAge)) {
            return dutyReportsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(endAge)) {
            return dutyReportsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age<="+ endAge + ")")).build().list();
        }

        return dutyReportsDao.loadAll();
    }
}
