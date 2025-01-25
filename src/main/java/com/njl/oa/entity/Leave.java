package com.njl.oa.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 请假实体类
 */
public class Leave implements Serializable {

    private Integer leaveId;        //请假ID
    private String leaveNumber;     //请假人工号
    private String leaveName;       //请假人名称
    private Integer leaveDay;       //请假天数
    private String leaveClassify;   //请假分类
    private String leaveReason;     //请假原因
    private String leaveTime;       //请假时间
    private Timestamp createTime;   //创建请假单时间
    private Integer leaveApproved;  //请假结果  0未审批，1审批通过，2审批不通过
    private String leaveReceipt;    //请假回执信息
    private String leaveDepartment; //请假人部门
    private String processInstanceId;   //流程实例ID

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveNumber() {
        return leaveNumber;
    }

    public void setLeaveNumber(String leaveNumber) {
        this.leaveNumber = leaveNumber;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public Integer getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Integer leaveDay) {
        this.leaveDay = leaveDay;
    }

    public String getLeaveClassify() {
        return leaveClassify;
    }

    public void setLeaveClassify(String leaveClassify) {
        this.leaveClassify = leaveClassify;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getLeaveApproved() {
        return leaveApproved;
    }

    public void setLeaveApproved(Integer leaveApproved) {
        this.leaveApproved = leaveApproved;
    }

    public String getLeaveReceipt() {
        return leaveReceipt;
    }

    public void setLeaveReceipt(String leaveReceipt) {
        this.leaveReceipt = leaveReceipt;
    }

    public String getLeaveDepartment() {
        return leaveDepartment;
    }

    public void setLeaveDepartment(String leaveDepartment) {
        this.leaveDepartment = leaveDepartment;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "leaveId=" + leaveId +
                ", leaveNumber='" + leaveNumber + '\'' +
                ", leaveName='" + leaveName + '\'' +
                ", leaveDay=" + leaveDay +
                ", leaveClassify='" + leaveClassify + '\'' +
                ", leaveReason='" + leaveReason + '\'' +
                ", leaveTime='" + leaveTime + '\'' +
                ", createTime=" + createTime +
                ", leaveApproved=" + leaveApproved +
                ", leaveReceipt='" + leaveReceipt + '\'' +
                ", leaveDepartment='" + leaveDepartment + '\'' +
                ", processInstanceId='" + processInstanceId + '\'' +
                '}';
    }
}
