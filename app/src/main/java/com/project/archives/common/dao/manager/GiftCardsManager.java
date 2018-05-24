package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GiftCards;
import com.project.archives.common.dao.GiftCardsDao;
import com.project.archives.common.dao.GreenDaoHelper;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by inrokei on 2018/4/30.
 */

public class GiftCardsManager {
    private static GiftCardsManager mInstance;
    private GiftCardsDao giftCardsDao;

    private GiftCardsManager() {
        giftCardsDao = GreenDaoHelper.getInstance().getDaoSession().getGiftCardsDao();
    }

    public static GiftCardsManager getInstance() {
        if (mInstance == null) {
            mInstance = new GiftCardsManager();
        }

        return mInstance;
    }

    public long getCount() {
        return giftCardsDao.count();
    }


    public GiftCards getGiftCardByGiftHandID(byte[] giftHandId) {

        QueryBuilder<GiftCards> queryBuilder = giftCardsDao.queryBuilder();
        if (giftHandId == null) {
            return null;
        }
        queryBuilder.where(GiftCardsDao.Properties.GiftHandID.eq(giftHandId));

        return queryBuilder.build().unique();
    }
}
