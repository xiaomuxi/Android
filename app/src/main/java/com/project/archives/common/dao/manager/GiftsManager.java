package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.Gifts;
import com.project.archives.common.dao.GiftsDao;
import com.project.archives.common.dao.GreenDaoHelper;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by inrokei on 2018/4/30.
 */

public class GiftsManager {
    private static GiftsManager mInstance;
    private GiftsDao giftsDao;

    private GiftsManager() {
        giftsDao = GreenDaoHelper.getInstance().getDaoSession().getGiftsDao();
    }

    public static GiftsManager getInstance() {
        if (mInstance == null) {
            mInstance = new GiftsManager();
        }

        return mInstance;
    }

    public long getCount() {
        return giftsDao.count();
    }


    public Gifts getGiftByGiftHandID(byte[] giftHandId) {

        QueryBuilder<Gifts> queryBuilder = giftsDao.queryBuilder();
        if (giftHandId == null) {
            return null;
        }
        queryBuilder.where(GiftsDao.Properties.GiftHandID.eq(giftHandId));

        return queryBuilder.build().unique();
    }
}
