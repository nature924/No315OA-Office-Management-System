package com.njl.oa.entity;

import java.sql.Timestamp;

public class Permissions {
    private Integer permissionsId;      //ID
    private Integer permissionsPid;     //PID
    private String permissionsTitle;    //名称
    private String permissionsDesc;     //说明
    private Integer permissionsStart;   //状态
    private Timestamp createTime;       //创建时间
    private Timestamp updateTime;       //修改时间

    public Integer getPermissionsPid() {
        return permissionsPid;
    }

    public void setPermissionsPid(Integer permissionsPid) {
        this.permissionsPid = permissionsPid;
    }

    public Integer getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Integer permissionsId) {
        this.permissionsId = permissionsId;
    }

    public String getPermissionsTitle() {
        return permissionsTitle;
    }

    public void setPermissionsTitle(String permissionsTitle) {
        this.permissionsTitle = permissionsTitle;
    }

    public String getPermissionsDesc() {
        return permissionsDesc;
    }

    public void setPermissionsDesc(String permissionsDesc) {
        this.permissionsDesc = permissionsDesc;
    }

    public Integer getPermissionsStart() {
        return permissionsStart;
    }

    public void setPermissionsStart(Integer permissionsStart) {
        this.permissionsStart = permissionsStart;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "permissionsId=" + permissionsId +
                ", permissionsTitle='" + permissionsTitle + '\'' +
                ", permissionsDesc='" + permissionsDesc + '\'' +
                ", permissionsStart=" + permissionsStart +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
