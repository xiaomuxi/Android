package com.project.archives.common.config;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import com.project.archives.common.utils.FileUtils;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Lxuyang on 2016/1/14.
 *
 */
public class GlobalConfig {

    // 设备信息
    // 用于存放日志，缓存到外置 SD 卡上
    private String sdPath;
    // 外置存储根目录
    public String sdRootPath;
    // 用于存放数据等到私有目录下
    public String dataPath;
    // 图像目录
    private String imagePath;
    // 缓存目录
    public String sdcachePath;
    // 日志目录
    private String sdlogPath;
    // 数据库目录
    public String databasePath;
    //apk包
    public String sdapkPath;
    // 屏幕宽
    private int scrwid = 1080;
    // 屏幕高
    private int scrhei = 1920;

    // 唯一配置实例
    private static GlobalConfig globalInstance;

    public static GlobalConfig getInstance() {
        if (globalInstance == null) {
            globalInstance = new GlobalConfig();
        }

        return globalInstance;
    }

    public static void init(Context context) {
        getInstance().initThis(context);
    }

    /**
     * 加载配置信息
     *
     * @param context
     */
    private void initThis(Context context) {
        // 加载设备信息

        scrwid = getScreenRect().width();
        scrhei = getScreenRect().height();


        // 初始化文件目录
        String fileSeparator = System.getProperty("file.separator");
        // sd 卡目录
        sdPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        // app 根目录
        String projectName = "archives";
        sdRootPath = sdPath + fileSeparator + projectName + fileSeparator;
        // 普通数据
        dataPath = sdRootPath + "data" + fileSeparator;
        // 数据库
        databasePath = context.getFilesDir() + "db" + fileSeparator;
        // 图片
        imagePath = sdRootPath + ".image" + fileSeparator;
        // 缓存
        sdcachePath = sdRootPath + "cache" + fileSeparator;
        //apk包
        sdapkPath = sdRootPath + "apk" + fileSeparator;
        // 日志
        sdlogPath = sdRootPath + ".log" + fileSeparator;

        // 初始化数据库，数据，图像、缓存、日志文件夹
        checkAndCreatePrivateDirectory();

        // 初始化缓存文件夹
        checkAndCreateSdDirectory();
        LogUtils.i(this.getClass().getName(), "project dir is prepared");
    }

    /**
     * 检查并创建私有文件夹
     */
    private void checkAndCreatePrivateDirectory() {
        // 创建私有文件夹
        String[] path = {sdRootPath, databasePath, dataPath, imagePath, sdcachePath,sdapkPath , sdlogPath};
        // 这里创建目录首先创建 sd/xxx，再创建 /sd/xxx/image，否则创建失败
        for (String x : path) {
            File file = new File(x);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    /**
     * 每次启动删除旧的文件夹
     */
    private void checkAndCreateSdDirectory() {
        if ((!StringUtils.isEmpty(sdPath) && new File(sdPath).canRead())) {

            String[] path = {sdcachePath, imagePath};
            for (String x : path) {
                File file = new File(x);

                if (!file.exists()) {
                    file.mkdir();
                } else {
                    FileUtils.DeleteFile(x);
                    file.mkdir();
                }
            }

            // 禁止系统 Media 搜索程序目录
            String nomediapath = imagePath + ".nomedia";
            File nomedia = new File(nomediapath);

            try {
                if (!nomedia.exists()) {
                    nomedia.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static Rect getScreenRect() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        return rect;
    }
}
