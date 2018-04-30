package com.project.archives.function.main.manager;

import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.dao.Zancuns;
import com.project.archives.common.dao.ZancunsDao;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        return zancunsDao.count();
    }

    public List<Zancuns> getZancunList(String userName, String companyName, String startTime, String endTime) {
        Date start = null;
        Date end = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            if (!StringUtils.isEmpty(startTime)) {
                start = format.parse(startTime);
            }
            if (!StringUtils.isEmpty(endTime)) {
                end = format.parse(startTime);
            }
        }
        catch (Exception e) {
            LogUtils.e(CaseInvesManager.class.getName(), e);
        }

        QueryBuilder<Zancuns> queryBuilder = zancunsDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(ZancunsDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(ZancunsDao.Properties.Init.like("%" + companyName + "%"));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.between(start, end));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.ge(start));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(ZancunsDao.Properties.AddDate.le(end));
        }

        queryBuilder.orderDesc(ZancunsDao.Properties.UpdateDate);

        return queryBuilder.list();
    }
}
