package com.project.archives.common.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.project.archives.common.utils.SpUtils;
import com.project.archives.common.utils.UIUtils;

/**
 * 名称:
 * 功能:
 * 创建人: luWei
 * 创建日期: 2016/11/11
 */
public class SPManager {

    public static final String TAG = SPManager.class.getSimpleName();
    private static SPManager mInstance;
    private static SharedPreferences mPublicSP;


    public static final String PUBLIC_OPTION = "public_option";
    public static final String PUBLIC_FIRST_INSTALL = "public_first_install"; // 首次安装
    public static final String PUBLIC_AUTO_LOGIN = "public_auto_login"; // 自动登录
    public static final String PUBLIC_USER_LIST = "public_user_list"; // 登录过的用户列表
    public static final String PUBLIC_PROVINCE_LIST = "public_province_list"; // 省列表
    public static final String PUBLIC_CITY_LIST = "public_city_list"; // 城市列表
    public static final String PUBLIC_COUNTY_LIST = "public_county_list"; // 区列表

    public SPManager() {
    }

    public static SPManager getInstance() {
        if (null == mInstance) {
            return new SPManager();
        }
        return mInstance;
    }

    public static SpUtils getPublicSP() {
        if (null == mPublicSP) {
            mPublicSP = UIUtils.getContext().getSharedPreferences(UIUtils.getContext().getPackageName(), Context.MODE_PRIVATE);
            mPublicSP.edit().apply();
        }
        SpUtils.getInstance().setSP(mPublicSP);
        return SpUtils.getInstance();

    }


}
