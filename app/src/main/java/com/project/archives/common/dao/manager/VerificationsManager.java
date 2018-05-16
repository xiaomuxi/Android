package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.dao.Verifications;
import com.project.archives.common.dao.VerificationsDao;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

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

    public long getCountByQueryWithCompanys(List<String> companys, String startTime, String endTime) {
        QueryBuilder<Verifications> queryBuilder = verificationsDao.queryBuilder();

        if (companys != null) {
            queryBuilder.where(VerificationsDao.Properties.Init.in(companys));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(startTime), VerificationsDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(VerificationsDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.buildCount().count();
    }

    public long getCountByQuery(String company, String startTime, String endTime) {
        QueryBuilder<Verifications> queryBuilder = verificationsDao.queryBuilder();

        if (!StringUtils.isEmpty(company)) {
            queryBuilder.where(VerificationsDao.Properties.Init.eq(company));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(startTime), VerificationsDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(VerificationsDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.buildCount().count();
    }


    public long getCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }

        return verificationsDao.queryBuilder().where(VerificationsDao.Properties.Name.eq(name)).buildCount().count();

    }

    public List<Verifications> getVerificationListWithCompanys(String userName, List<String> companyNames, String startTime, String endTime) {

        QueryBuilder<Verifications> queryBuilder = verificationsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(VerificationsDao.Properties.Name.eq(userName));
        }

        if (companyNames != null) {
            queryBuilder.where(VerificationsDao.Properties.Init.in(companyNames));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(startTime), VerificationsDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.orderDesc(VerificationsDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<Verifications> getVerificationList(String userName, String companyName, String startTime, String endTime) {

        QueryBuilder<Verifications> queryBuilder = verificationsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(VerificationsDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(VerificationsDao.Properties.Init.eq(companyName));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(startTime), VerificationsDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(VerificationsDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.orderDesc(VerificationsDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<Verifications> getVerificationListByNameTakingResultAndResultSituation(String name, String takingResult, int resultSituation) {

        QueryBuilder<Verifications> queryBuilder = verificationsDao.queryBuilder();
        if (!StringUtils.isEmpty(name)) {
            queryBuilder.where(VerificationsDao.Properties.Name.like("%" + name + "%"));
        }

        if (!StringUtils.isEmpty(takingResult)) {
            queryBuilder.where(VerificationsDao.Properties.TakingResult.eq(takingResult));
        }

        if (resultSituation != 0) {
            queryBuilder.where(VerificationsDao.Properties.ResultSituation.eq(resultSituation));
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
