package com.project.archives.common.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.project.archives.common.dao.GreenDaoHelper;
import com.project.archives.common.utils.FileUtils;
import com.project.archives.common.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

        DB_PATH = this.getDatabasePath("jw.db").getParent() + "/";
//        String[] list = this.getFilesDir(DB_PATH).list();
        File file = new File(DB_PATH);
        String[] list = file.list();
        for (int i = 0; i < list.length; i++) {
            LogUtils.i("TEST---", list[i]);
        }


//        File file = new File(DB_PATH);
//        file.list();
        LogUtils.i("TEST--", DB_PATH);

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

}
