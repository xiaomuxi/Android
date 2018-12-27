package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.dao.Zancuns;
import com.project.archives.common.dao.ZancunsDao;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

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
        return zancunsDao.queryBuilder().where(ZancunsDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getCountByQueryWithCompanys(List<String> companys, String startTime, String endTime) {
        QueryBuilder<Zancuns> queryBuilder = zancunsDao.queryBuilder();

        if (companys != null) {
            queryBuilder.where(ZancunsDao.Properties.Init.in(companys));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(startTime), ZancunsDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(ZancunsDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.where(ZancunsDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getCountByQuery(String company, String startTime, String endTime) {
        QueryBuilder<Zancuns> queryBuilder = zancunsDao.queryBuilder();

        if (!StringUtils.isEmpty(company)) {
            queryBuilder.where(ZancunsDao.Properties.Init.eq(company));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(startTime), ZancunsDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(ZancunsDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.where(ZancunsDao.Properties.IsDelete.eq(1)).buildCount().count();
    }

    public long getCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }

        return zancunsDao.queryBuilder().where(ZancunsDao.Properties.Name.eq(name), ZancunsDao.Properties.IsDelete.eq(1)).buildCount().count();

    }

    public List<Zancuns> getZancunListWithCompanys(String userName, List<String> companyNames, String startTime, String endTime) {

        QueryBuilder<Zancuns> queryBuilder = zancunsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(ZancunsDao.Properties.Name.eq(userName));
        }

        if (companyNames != null) {
            queryBuilder.where(ZancunsDao.Properties.Init.in(companyNames));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(startTime), ZancunsDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.le(endTime));
        }
        queryBuilder.where(ZancunsDao.Properties.IsDelete.eq(1));
        queryBuilder.orderDesc(ZancunsDao.Properties.AddDate);
        return queryBuilder.build().list();
    }

    public List<Zancuns> getZancunList(String userName, String companyName, String startTime, String endTime) {

        QueryBuilder<Zancuns> queryBuilder = zancunsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(ZancunsDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(ZancunsDao.Properties.Init.eq(companyName));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(startTime), ZancunsDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.where(ZancunsDao.Properties.IsDelete.eq(1));
        queryBuilder.orderDesc(ZancunsDao.Properties.AddDate);
        return queryBuilder.build().list();
    }

    public List<Zancuns> getZancunListByName(String name) {

        QueryBuilder<Zancuns> queryBuilder = zancunsDao.queryBuilder();
        if (!StringUtils.isEmpty(name)) {
            queryBuilder.where(ZancunsDao.Properties.Name.like("%" + name + "%"));
        }

        queryBuilder.where(ZancunsDao.Properties.IsDelete.eq(1));
        queryBuilder.orderDesc(ZancunsDao.Properties.AddDate);
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
