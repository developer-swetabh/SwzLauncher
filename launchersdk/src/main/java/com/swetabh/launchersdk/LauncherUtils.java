package com.swetabh.launchersdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LauncherUtils {

    public static final Integer SUCCESS = 1;
    public static final Integer FAILURE = 0;

    /* To get the icon of a particular package */
    public static Drawable getActivityIcon(Context context, String packageName, String activityName) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityName));
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);

        return resolveInfo.loadIcon(pm);
    }

    public static List<AppInfo> getAppsList(Context context) {
        List<AppInfo> appsList = new ArrayList<AppInfo>();
        //This is where we build our list of app details, using the app
        //object we created to store the label, package name and icon

        PackageManager pm = context.getPackageManager();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
        for (ResolveInfo ri : allApps) {
            AppInfo app = new AppInfo();
            app.setLabel((String) ri.loadLabel(pm));
            app.setPackageName(ri.activityInfo.packageName);
            PackageInfo pkgInfo = null;
            try {
                pkgInfo = pm.getPackageInfo(app.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            app.setIcon(ri.activityInfo.loadIcon(pm));
            app.setMainActivityName(ri.activityInfo.name);
            if (pkgInfo != null) {
                app.setVersionName(pkgInfo.versionName);
                app.setVersionCode(pkgInfo.versionCode);
            }
            appsList.add(app);
        }
        Collections.sort(appsList);
        return appsList;
    }
}
