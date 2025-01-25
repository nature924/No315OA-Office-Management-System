package com.njl.oa.entity;

import java.sql.Timestamp;

public class Role {
    private Integer roleId;         //ID
    private String roleTitle;       //名称
    private String roleDesc;        //说明
    private Integer roleStart;      //状态
    private Timestamp createTime;   //创建时间
    private Timestamp updateTime;   //修改时间

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Integer getRoleStart() {
        return roleStart;
    }

    public void setRoleStart(Integer roleStart) {
        this.roleStart = roleStart;
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
}
