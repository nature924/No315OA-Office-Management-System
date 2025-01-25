package com.njl.oa.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Employee implements Serializable {
    private Integer employeeId;         //ID
    private String employeeName;        //姓名
    private String employeeNumber;      //工号
    private String employeePassword;    //密码
    private Integer employeeSex;        //性别
    private Timestamp employeeBirthDate;//出生日期
    private String employeePhone;       //手机号
    private String employeeEmail;       //邮件地址
    private String employeeAddress;     //家庭地址
    private Integer employeeStart;      //是否有效
    private Timestamp updateTime;       //修改时间
    private Timestamp createTime;       //创建时间
    private String employeeDesc;        //说明
    private Integer roleId;             //所在角色ID
    private Integer departmentId;       //所在部门ID
    private String employeePhoto;       //雇员照片


    private String roleTitle;           //所在角色名称
    private String departmentTitle;     //所在部门名称

    private String startTime;           //开始时间
    private String endTime;             //结束时间

    public String getEmployeePhoto() {
        return employeePhoto;
    }

    public void setEmployeePhoto(String employeePhoto) {
        this.employeePhoto = employeePhoto;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public Integer getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(Integer employeeSex) {
        this.employeeSex = employeeSex;
    }

    public Timestamp getEmployeeBirthDate() {
        return employeeBirthDate;
    }

    public void setEmployeeBirthDate(Timestamp employeeBirthDate) {
        this.employeeBirthDate = employeeBirthDate;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public Integer getEmployeeStart() {
        return employeeStart;
    }

    public void setEmployeeStart(Integer employeeStart) {
        this.employeeStart = employeeStart;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getEmployeeDesc() {
        return employeeDesc;
    }

    public void setEmployeeDesc(String employeeDesc) {
        this.employeeDesc = employeeDesc;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                ", employeeSex=" + employeeSex +
                ", employeeBirthDate=" + employeeBirthDate +
                ", employeePhone='" + employeePhone + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                ", employeeStart=" + employeeStart +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", employeeDesc='" + employeeDesc + '\'' +
                ", roleId=" + roleId +
                ", departmentId=" + departmentId +
                ", employeePhoto='" + employeePhoto + '\'' +
                ", roleTitle='" + roleTitle + '\'' +
                ", departmentTitle='" + departmentTitle + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
