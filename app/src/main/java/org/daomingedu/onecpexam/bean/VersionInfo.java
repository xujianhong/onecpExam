package org.daomingedu.onecpexam.bean;

public class VersionInfo {

    /**
     * path : https://tjmusician-1255518711.cos.ap-beijing.myqcloud.comnull
     * releaseTime : 2020-08-08 12:14:29
     * isMust : 1
     * systemType : 1
     * releaseUser :
     * remark : 测试
     * id : 1
     * versionName : 1.1.1
     * versionCode : 1
     */
    private String path;
    private String releaseTime;
    private int isMust;
    private int systemType;
    private String releaseUser;
    private String remark;
    private String id;
    private String versionName;
    private int versionCode;

    public void setPath(String path) {
        this.path = path;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setIsMust(int isMust) {
        this.isMust = isMust;
    }

    public void setSystemType(int systemType) {
        this.systemType = systemType;
    }

    public void setReleaseUser(String releaseUser) {
        this.releaseUser = releaseUser;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getPath() {
        return path;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public int getIsMust() {
        return isMust;
    }

    public int getSystemType() {
        return systemType;
    }

    public String getReleaseUser() {
        return releaseUser;
    }

    public String getRemark() {
        return remark;
    }

    public String getId() {
        return id;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }
}
