package com.soho.ssc.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Lihong on 2016/10/16.
 * description:获取系统版本号
 */

public class versionUtil {

    /**
     * 得到版本名 manifest.vml version Name
     * @param context
     * @return
     */
    public static String getVersion(Context context){
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            // 得包名
            String packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            versionName = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
    /**
     * 获取本地apk版本号
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = 0;
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            verCode = packageInfo.versionCode;
            L.e(verCode+"code");
        } catch (PackageManager.NameNotFoundException e) {
            L.e(e.getMessage());
        }
        return verCode;
    }
}
