package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GiftCards;
import com.project.archives.common.dao.GiftCardsDao;
import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

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
        return giftCardsDao.queryBuilder().where(GiftCardsDao.Properties.IsDelete.eq("1")).buildCount().count();
    }


    public List<GiftCards> getGiftCardByGiftHandID(byte[] giftHandId) {

        QueryBuilder<GiftCards> queryBuilder = giftCardsDao.queryBuilder();
        if (giftHandId == null) {
            return null;
        }
        String handId = StringUtils.byteArrayToHexStr(giftHandId);
        queryBuilder.where(new WhereCondition.StringCondition("GiftHandID=" +"X'" + handId+"'"), GiftCardsDao.Properties.IsDelete.eq("1"));

        return queryBuilder.build().list();
    }
}
