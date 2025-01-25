package com.njl.oa.entity;

import java.sql.Timestamp;

public class Punch {

    private Integer punchId;            //打卡ID
    private Integer punchClassify;      //打卡分类
    private Integer punchStatus;        //打卡状态
    private Integer employeeId;         //打卡人ID
    private Timestamp punchTime;        //打卡时间
    private String punchDay;            //打卡日期


    private String employeeNumber;
    private String employeeName;

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

    public String getPunchDay() {
        return punchDay;
    }

    public void setPunchDay(String punchDay) {
        this.punchDay = punchDay;
    }

    public Integer getPunchId() {
        return punchId;
    }

    public void setPunchId(Integer punchId) {
        this.punchId = punchId;
    }

    public Integer getPunchClassify() {
        return punchClassify;
    }

    public void setPunchClassify(Integer punchClassify) {
        this.punchClassify = punchClassify;
    }

    public Integer getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(Integer punchStatus) {
        this.punchStatus = punchStatus;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Timestamp getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Timestamp punchTime) {
        this.punchTime = punchTime;
    }

    @Override
    public String toString() {
        return "Punch{" +
                "punchId=" + punchId +
                ", punchClassify=" + punchClassify +
                ", punchStatus=" + punchStatus +
                ", employeeId=" + employeeId +
                ", punchTime=" + punchTime +
                ", punchDay='" + punchDay + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }
}
