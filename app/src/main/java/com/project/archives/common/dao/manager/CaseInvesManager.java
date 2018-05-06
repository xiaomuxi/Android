package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.CaseInves;
import com.project.archives.common.dao.CaseInvesDao;
import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by inrokei on 2018/4/30.
 */

public class CaseInvesManager {
    private static CaseInvesManager mInstance;
    private CaseInvesDao caseInvesDao;

    private CaseInvesManager() {
        caseInvesDao = GreenDaoHelper.getInstance().getDaoSession().getCaseInvesDao();
    }

    public static CaseInvesManager getInstance() {
        if (mInstance == null) {
            mInstance = new CaseInvesManager();
        }

        return mInstance;
    }

    public long getCount() {
        return caseInvesDao.count();
    }

    public long getCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }

        return caseInvesDao.queryBuilder().where(CaseInvesDao.Properties.Name.eq(name)).buildCount().count();

    }

    public long getCountByQuery(String company, String startTime, String endTime) {

        QueryBuilder<CaseInves> queryBuilder = caseInvesDao.queryBuilder();

        if (!StringUtils.isEmpty(company)) {
            queryBuilder.where(CaseInvesDao.Properties.Init.eq(company));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(CaseInvesDao.Properties.AddDate.ge(startTime), CaseInvesDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(CaseInvesDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(CaseInvesDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.buildCount().count();
    }

    public List<CaseInves> getCaseInvesList(String userName, String companyName, String startTime, String endTime) {

        QueryBuilder<CaseInves> queryBuilder = caseInvesDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(CaseInvesDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(CaseInvesDao.Properties.Init.eq(companyName));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(CaseInvesDao.Properties.AddDate.ge(startTime), CaseInvesDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(CaseInvesDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(CaseInvesDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.orderDesc(CaseInvesDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<CaseInves> getCaseInvesListByNameAndDangZheng(String name, String dang, String zheng) {

        QueryBuilder<CaseInves> queryBuilder = caseInvesDao.queryBuilder();
        if (!StringUtils.isEmpty(name)) {
            queryBuilder.where(CaseInvesDao.Properties.Name.like("%" + name + "%"));
        }

        if (!StringUtils.isEmpty(dang)) {
            queryBuilder.where(CaseInvesDao.Properties.DisTypeD.eq(dang));
        }

        if (!StringUtils.isEmpty(zheng)) {
            queryBuilder.where(CaseInvesDao.Properties.DisTypeX.eq(zheng));
        }

        queryBuilder.orderDesc(CaseInvesDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<CaseInves> getCaseInvesListByAge(String startAge, String endAge) {
        if (!StringUtils.isEmpty(startAge) && !StringUtils.isEmpty(endAge)) {
            return caseInvesDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge +" and Age<="+ endAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(startAge)) {
            return caseInvesDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(endAge)) {
            return caseInvesDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age<="+ endAge + ")")).build().list();
        }

        return caseInvesDao.loadAll();
    }

}
