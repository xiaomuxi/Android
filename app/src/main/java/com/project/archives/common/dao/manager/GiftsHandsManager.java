package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GiftHands;
import com.project.archives.common.dao.GiftHandsDao;
import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import static com.project.archives.R.drawable.company;

/**
 * Created by inrokei on 2018/4/30.
 */

public class GiftsHandsManager {
    private static GiftsHandsManager mInstance;
    private GiftHandsDao giftHandsDao;

    private GiftsHandsManager() {
        giftHandsDao = GreenDaoHelper.getInstance().getDaoSession().getGiftHandsDao();
    }

    public static GiftsHandsManager getInstance() {
        if (mInstance == null) {
            mInstance = new GiftsHandsManager();
        }

        return mInstance;
    }

    public long getCount() {
        return giftHandsDao.queryBuilder().where(GiftHandsDao.Properties.IsDelete.eq("1")).buildCount().count();
    }

    public long getCountByQueryWithCompanys(List<String> companys, String startTime, String endTime) {
        QueryBuilder<GiftHands> queryBuilder = giftHandsDao.queryBuilder();

        if (companys != null) {
            queryBuilder.where(GiftHandsDao.Properties.Init.in(companys));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(GiftHandsDao.Properties.AddDate.ge(startTime), GiftHandsDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(GiftHandsDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(GiftHandsDao.Properties.Init.eq(company));
        }
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(GiftHandsDao.Properties.AddDate.ge(startTime), GiftHandsDao.Properties.AddDate.le(endTime));
        }
        else if (!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(GiftHandsDao.Properties.AddDate.ge(startTime));
        }
        else if (!StringUtils.isEmpty(endTime)){
            queryBuilder.where(GiftHandsDao.Properties.AddDate.le(endTime));
        }

        return queryBuilder.where(GiftHandsDao.Properties.IsDelete.eq("1")).buildCount().count();
    }


    public long getCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }

        return giftHandsDao.queryBuilder().where(GiftHandsDao.Properties.Name.eq(name), GiftHandsDao.Properties.IsDelete.eq("1")).buildCount().count();

    }

    public List<GiftHands> getGiftHandsListWithCompanys(String userName, List<String> companyNames, String startTime, String endTime) {

        QueryBuilder<GiftHands> queryBuilder = giftHandsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(GiftHandsDao.Properties.Name.eq(userName));
        }

        if (companyNames != null) {
            queryBuilder.where(GiftHandsDao.Properties.Init.in(companyNames));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(GiftHandsDao.Properties.AddDate.ge(startTime), GiftHandsDao.Properties.AddDate.le(endTime));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(GiftHandsDao.Properties.AddDate.ge(startTime));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(GiftHandsDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.where(GiftHandsDao.Properties.IsDelete.eq("1"));
        queryBuilder.orderDesc(GiftHandsDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<GiftHands> getGiftHandsList(String userName, String companyName, String startTime, String endTime) {

        QueryBuilder<GiftHands> queryBuilder = giftHandsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(GiftHandsDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(GiftHandsDao.Properties.AddDate.le(endTime));
        }

        queryBuilder.where(GiftHandsDao.Properties.IsDelete.eq("1"));
        queryBuilder.orderDesc(GiftHandsDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<GiftHands> getGiftHandsListByName(String name) {

        QueryBuilder<GiftHands> queryBuilder = giftHandsDao.queryBuilder();
        if (!StringUtils.isEmpty(name)) {
            queryBuilder.where(GiftHandsDao.Properties.Name.like("%" + name + "%"));
        }

        queryBuilder.where(GiftHandsDao.Properties.IsDelete.eq("1"));
        queryBuilder.orderDesc(GiftHandsDao.Properties.UpdateDate);

        return queryBuilder.list();
    }

    public List<GiftHands> getGiftHandsListByAge(String  startAge, String endAge) {
        if (!StringUtils.isEmpty(startAge) && !StringUtils.isEmpty(endAge)) {
            return giftHandsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge +" and Age<="+ endAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(startAge)) {
            return giftHandsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age>="+ startAge + ")")).build().list();
        }

        if (!StringUtils.isEmpty(endAge)) {
            return giftHandsDao.queryBuilder().where(
                    new WhereCondition.StringCondition("UserID in " +
                            "(select ID from Users where Age<="+ endAge + ")")).build().list();
        }

        return giftHandsDao.loadAll();
    }
}
