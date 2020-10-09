package com.swetabh.launchersdk;

import android.graphics.drawable.Drawable;

/* AppInfo is used to hold all the information of the applications installed
* on the device. It is using {Comparable} to sort the list in ascending order based on app name
* */
public class AppInfo implements Comparable<AppInfo>{

    private String label;
    private String packageName;
    private int versionCode;
    private String versionName;
    private String mainActivityName;
    private Drawable icon;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getMainActivityName() {
        return mainActivityName;
    }

    public void setMainActivityName(String mainActivityName) {
        this.mainActivityName = mainActivityName;
    }

    @Override
    public int compareTo(AppInfo info) {
        return this.label.compareTo(info.label);
    }
}
