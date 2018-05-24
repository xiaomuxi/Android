package com.project.archives.common.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.project.archives.common.config.GlobalConfig;
import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.utils.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by inrokei on 2018/4/24.
 */

public class App extends Application{
    /**
     * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
     */
    private static App mInstance;
    /**
     * 主线程ID
     */
    private static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;

    public static boolean isFirst;
    public static String appId;

    private GreenDaoHelper dbHelper;
    private static String DB_PATH = "";

    @Override
    public void onCreate() {
        super.onCreate();
        //android.os.Process.myTid()  获取调用进程的id
        //android.os.Process.myUid() 获取 该进程的用户id
        //android.os.Process.myPid() 获取进程的id
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;

        String processName = getProcessName(this, android.os.Process.myPid());
        if (null == processName || processName.equals(getApplication().getPackageName())) {
            GlobalConfig.init(this);
        }


//        DB_PATH = this.getDatabasePath("jw.db").getParent() + "/jw";
//        String dbPath = GlobalConfig.getInstance().dataPath + "jw.db";
//        if (new File(dbPath).canRead()) {
//           Boolean result = FileUtils.copyFile(dbPath, DB_PATH, true);
//        }
        DB_PATH = this.getDatabasePath("jw.db").getParent() + "/";

        try {
            InputStream dbInputStream = this.getAssets().open("jw.db");
            Boolean result = FileUtils.copyFile(dbInputStream, DB_PATH, "jw");
        } catch (IOException e) {
            e.printStackTrace();
        }

        dbHelper = GreenDaoHelper.getInstance();
    }

    public static App getApplication() {
        return mInstance;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    /**
     * 获取进程名称
     *
     * @param
     * @return
     * @author Ahwind
     * modify at 2016/10/25 14:54
     */
    public String getProcessName(Context cxt, int pid) {
        try {
            ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
            if (null != runningApps) {
                for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                    if (procInfo.pid == pid) {
                        return procInfo.processName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
