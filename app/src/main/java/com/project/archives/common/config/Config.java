package com.project.archives.common.config;

import android.content.pm.ApplicationInfo;

import com.project.archives.common.app.App;

/**
 * Created by inrokei on 2018/4/24.
 */

public class Config {
    public static boolean isDebug() {
        try {
            ApplicationInfo info = App.getApplication().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
