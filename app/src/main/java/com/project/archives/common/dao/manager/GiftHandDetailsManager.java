package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GiftHandDetails;
import com.project.archives.common.dao.GiftHandDetailsDao;
import com.project.archives.common.dao.GreenDaoHelper;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by inrokei on 2018/4/30.
 */

public class GiftHandDetailsManager {
    private static GiftHandDetailsManager mInstance;
    private GiftHandDetailsDao giftHandDetailsDao;

    private GiftHandDetailsManager() {
        giftHandDetailsDao = GreenDaoHelper.getInstance().getDaoSession().getGiftHandDetailsDao();
    }

    public static GiftHandDetailsManager getInstance() {
        if (mInstance == null) {
            mInstance = new GiftHandDetailsManager();
        }

        return mInstance;
    }

    public long getCount() {
        return giftHandDetailsDao.count();
    }


    public GiftHandDetails getGiftHandDetailByGiftHandID(byte[] giftHandId) {

        QueryBuilder<GiftHandDetails> queryBuilder = giftHandDetailsDao.queryBuilder();
        if (giftHandId == null) {
            return null;
        }
        queryBuilder.where(GiftHandDetailsDao.Properties.GiftHandID.eq( giftHandId));

        return queryBuilder.build().unique();
    }
}
