package com.project.archives.common.utils;

import android.content.SharedPreferences;

/**
 * Created by Riky luwei on 2016/9/22.
 */

public class SpUtils {
    public static final String TAG = SpUtils.class.getSimpleName();

    private static SpUtils mInstance;
    private static SharedPreferences mSp;

    private SpUtils() {
    }

    public static SpUtils getInstance() {
        if (mInstance == null) {
            mInstance = new SpUtils();
        }
        return mInstance;
    }

    public void setSP(SharedPreferences sp) {
        SpUtils.mSp = sp;
    }

    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }

    public long getLong(String key, int defValue) {
        return mSp.getLong(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    public void putString(String key, String value) {
        mSp.edit().putString(key, value).apply();
    }

    public void putInt(String key, int value) {
        mSp.edit().putInt(key, value).apply();
    }

    public void putLong(String key, long value) {
        mSp.edit().putLong(key, value).apply();
    }

    public void putBoolean(String key, boolean value) {
        mSp.edit().putBoolean(key, value).apply();
    }

    public void removeByKey(String key) {
        mSp.edit().remove(key).apply();
    }

    public static class id {
        /**
         * 是否第一次登录
         */
        public static String KEY_IS_FIRST_LOGIN = "is_first_login";
        public static String KEY_INTERCEPT_FLAG = "intercept_flag";
        public static String KEY_INTERCEPT_LASTTIME = "intercept_lasttime";

        public static final String USER_TOKEN = "user_token";
        public static final String USER_PUBLICKEY = "user_publickey";
        public static final String USER_COMPANY = "user_company";
        public static final String USER_EMPID = "user_empid";
        public static final String USER_PASS = "user_pass";
        public static final String USER_DEV1 = "user_dev1";
        public static final String USER_DEV2 = "user_dev2";
        public static final String USER_MOBILE = "user_mobile";
        public static final String USER_NAME = "user_name";
        public static final String USER_PASSET = "user_passst";
        public static final String USER_DEVST = "user_devst";

        public static String PREVIOUS_VERSION_CODE =  "previous_version_code";

        public static String APK_DOWNLOAD_ID = "apkDownloadId";
        public static String SHARE_QR_APK_URL = "apk_url";
        public static String SHARE_QR_APK_VERSION = "apk_version";
    }
}
