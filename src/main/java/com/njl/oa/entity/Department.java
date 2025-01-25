package com.njl.oa.entity;

import java.sql.Timestamp;

public class Department {

    private Integer departmentId;       //部门ID
    private String departmentTitle;     //部门名称
    private String departmentDesc;      //部门说明
    private Timestamp createTime;       //创建时间
    private Timestamp updateTime;       //修改时间
    private Integer departmentStart;    //是否启用
    private Integer departmentHeadId;   //部门负责人ID
    private Integer departmentSize;     //部门人数


    private String departmentHeadName;  //部门负责人名称

    public String getDepartmentHeadName() {
        return departmentHeadName;
    }

    public void setDepartmentHeadName(String departmentHeadName) {
        this.departmentHeadName = departmentHeadName;
    }

    public Integer getDepartmentSize() {
        return departmentSize;
    }

    public void setDepartmentSize(Integer departmentSize) {
        this.departmentSize = departmentSize;
    }

    public Integer getDepartmentHeadId() {
        return departmentHeadId;
    }

    public void setDepartmentHeadId(Integer departmentHeadId) {
        this.departmentHeadId = departmentHeadId;
    }

    public Integer getDepartmentStart() {
        return departmentStart;
    }

    public void setDepartmentStart(Integer departmentStart) {
        this.departmentStart = departmentStart;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    public String getDepartmentDesc() {
        return departmentDesc;
    }

    public void setDepartmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc;
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
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentTitle='" + departmentTitle + '\'' +
                ", departmentDesc='" + departmentDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", departmentStart=" + departmentStart +
                ", departmentHeadId=" + departmentHeadId +
                ", departmentSize=" + departmentSize +
                ", departmentHeadName='" + departmentHeadName + '\'' +
                '}';
    }
}
