package com.project.archives.common.dao.manager;

import com.project.archives.common.dao.GiftHandDetails;
import com.project.archives.common.dao.GiftHandDetailsDao;
import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

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


    public List<GiftHandDetails> getGiftHandDetailByGiftHandID(byte[] giftHandId) {

        QueryBuilder<GiftHandDetails> queryBuilder = giftHandDetailsDao.queryBuilder();
        if (giftHandId == null) {
            return null;
        }
        String handId = StringUtils.byteArrayToHexStr(giftHandId);
        queryBuilder.where(new WhereCondition.StringCondition("GiftHandID=" +"X'" + handId+"'"));

        return queryBuilder.build().list();
    }
}
