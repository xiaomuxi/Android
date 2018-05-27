package com.project.archives.common.userdao.manager;

import com.project.archives.common.userdao.GreenDaoUserHelper;
import com.project.archives.common.userdao.User;
import com.project.archives.common.userdao.UserDao;

/**
 * Created by inrokei on 2018/5/27.
 */

public class UserManager {

    private static UserManager mInstance;
    private UserDao userDao;

    private UserManager() {
        userDao = GreenDaoUserHelper.getInstance().getDaoSession().getUserDao();
    }

    public static UserManager getInstance() {
        if (mInstance == null) {
            mInstance = new UserManager();
        }

        return mInstance;
    }

    public long getCount() {
        return userDao.count();
    }

    public long insertUserInfo(User user) {
        return userDao.insertOrReplace(user);
    }

    public User getUserByNameAndPassword(String name, String password) {
        return userDao.queryBuilder().where(UserDao.Properties.UserName.eq(name), UserDao.Properties.Password.eq(password)).build().unique();
    }

    public void updateUserPassword(User user) {
        userDao.update(user);
    }
}
