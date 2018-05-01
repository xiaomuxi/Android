package com.project.archives.function.main.manager;

import com.project.archives.common.dao.CaseInves;
import com.project.archives.common.dao.CaseInvesDao;
import com.project.archives.common.dao.GreenDaoHelper;
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

    public long getCountByQuery(Date startTime, Date endTime) {
        QueryBuilder<CaseInves> queryBuilder = caseInvesDao.queryBuilder();
        queryBuilder.where(CaseInvesDao.Properties.AddDate.ge(startTime), CaseInvesDao.Properties.AddDate.le(endTime));

        return queryBuilder.buildCount().count();
    }

    public List<CaseInves> getCaseInvesList(String userName, String companyName, String startTime, String endTime) {
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

        QueryBuilder<CaseInves> queryBuilder = caseInvesDao.queryBuilder();
        if (!StringUtils.isEmpty(userName)) {
            queryBuilder.where(CaseInvesDao.Properties.Name.eq(userName));
        }

        if (!StringUtils.isEmpty(companyName)) {
            queryBuilder.where(CaseInvesDao.Properties.Init.like("%" + companyName + "%"));
        }

        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            queryBuilder.where(CaseInvesDao.Properties.AddDate.ge(start), CaseInvesDao.Properties.AddDate.le(end));
        }
        else if(!StringUtils.isEmpty(startTime)) {
            queryBuilder.where(CaseInvesDao.Properties.AddDate.ge(start));
        }
        else if(!StringUtils.isEmpty(endTime)) {
            queryBuilder.where(CaseInvesDao.Properties.AddDate.le(end));
        }

        queryBuilder.orderDesc(CaseInvesDao.Properties.UpdateDate);

        return queryBuilder.list();
    }
}
