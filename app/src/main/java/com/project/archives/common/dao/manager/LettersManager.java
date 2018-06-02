package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.dao.Letters;
import com.project.archives.common.dao.LettersDao;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

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
        return lettersDao.queryBuilder().where(LettersDao.Properties.IsDelete.eq("1")).buildCount().count();
    }

    public long getCountByQueryWithCompanys(List<String> companys, String startTime, String endTime) {
        QueryBuilder<Letters> queryBuilder = lettersDao.queryBuilder();

        if (companys != null) {
            queryBuilder.where(LettersDao.Properties.Init.in(companys));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(startTime), LettersDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(LettersDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.where(LettersDao.Properties.IsDelete.eq("1")).buildCount().count();
    }

    public long getCountByQuery(String company, String startTime, String endTime) {
        QueryBuilder<Letters> queryBuilder = lettersDao.queryBuilder();

        if (!StringUtils.isEmpty(company)) {
            queryBuilder.where(LettersDao.Properties.Init.eq(company));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(startTime), LettersDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(LettersDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.where(LettersDao.Properties.IsDelete.eq("1")).buildCount().count();
    }


    public long getCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }

        return lettersDao.queryBuilder().where(LettersDao.Properties.Name.eq(name), LettersDao.Properties.IsDelete.eq("1")).buildCount().count();

    }

    public List<Letters> getLetterListWithCompanys(String userName, List<String> companyNames, String startTime, String endTime) {

        QueryBuilder<Letters> queryBuilder = lettersDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(LettersDao.Properties.Name.eq(userName));
        }

        if (companyNames != null) {
            queryBuilder.where(LettersDao.Properties.Init.in(companyNames));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(startTime), LettersDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.where(LettersDao.Properties.IsDelete.eq("1"));
        queryBuilder.orderDesc(LettersDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<Letters> getLetterList(String userName, String companyName, String startTime, String endTime) {

        QueryBuilder<Letters> queryBuilder = lettersDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(LettersDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(LettersDao.Properties.Init.eq(companyName));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(startTime), LettersDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(LettersDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.where(LettersDao.Properties.IsDelete.eq("1"));
        queryBuilder.orderDesc(LettersDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<Letters> getLetterListByNameTrueDegreeAndResultSituation(String userName, String trueDegree, int resultSituation) {

        QueryBuilder<Letters> queryBuilder = lettersDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(LettersDao.Properties.Name.like("%" + userName + "%"));
        }

        if (!StringUtils.isEmpty(trueDegree)) {
            queryBuilder.where(LettersDao.Properties.TrueDegree.eq(trueDegree));
        }

        if (resultSituation != 0) {
            queryBuilder.where(LettersDao.Properties.ResultSituation.eq(resultSituation));
        }

        queryBuilder.where(LettersDao.Properties.IsDelete.eq("1"));
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
