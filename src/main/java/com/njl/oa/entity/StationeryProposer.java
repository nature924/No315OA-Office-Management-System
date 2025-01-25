package com.njl.oa.entity;

import java.sql.Timestamp;

public class StationeryProposer {
    private Integer stationeryEmployeeRelId;    //申请办公用品ID
    private Integer stationeryId;               //办公用品ID
    private Integer employeeId;                 //雇员ID
    private Integer stationeryCount;            //办公用品数量
    private Timestamp creationTime;             //申请时间
    private Integer approve;                    //审批状态：0未审批，1同意，2不同意
    private String receipt;                     //审批回执
    private String explain;                     //说明
    private Integer approveEmployeeId;          //审批人ID

    private String approveEmployeeName;         //审批人名称
    private String stationeryName;              //办公用品名称
    private String proposer;                    //办公用品名称
    private String employeeName;                //申请人名称

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getStationeryName() {
        return stationeryName;
    }

    public void setStationeryName(String stationeryName) {
        this.stationeryName = stationeryName;
    }

    public String getApproveEmployeeName() {
        return approveEmployeeName;
    }

    public void setApproveEmployeeName(String approveEmployeeName) {
        this.approveEmployeeName = approveEmployeeName;
    }

    public Integer getApproveEmployeeId() {
        return approveEmployeeId;
    }

    public void setApproveEmployeeId(Integer approveEmployeeId) {
        this.approveEmployeeId = approveEmployeeId;
    }

    public Integer getStationeryEmployeeRelId() {
        return stationeryEmployeeRelId;
    }

    public void setStationeryEmployeeRelId(Integer stationeryEmployeeRelId) {
        this.stationeryEmployeeRelId = stationeryEmployeeRelId;
    }

    public Integer getStationeryId() {
        return stationeryId;
    }

    public void setStationeryId(Integer stationeryId) {
        this.stationeryId = stationeryId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getStationeryCount() {
        return stationeryCount;
    }

    public void setStationeryCount(Integer stationeryCount) {
        this.stationeryCount = stationeryCount;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getApprove() {
        return approve;
    }

    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public String toString() {
        return "StationeryProposer{" +
                "stationeryEmployeeRelId=" + stationeryEmployeeRelId +
                ", stationeryId=" + stationeryId +
                ", employeeId=" + employeeId +
                ", stationeryCount=" + stationeryCount +
                ", creationTime=" + creationTime +
                ", approve=" + approve +
                ", receipt='" + receipt + '\'' +
                ", explain='" + explain + '\'' +
                ", approveEmployeeId=" + approveEmployeeId +
                ", approveEmployeeName='" + approveEmployeeName + '\'' +
                ", stationeryName='" + stationeryName + '\'' +
                ", proposer='" + proposer + '\'' +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }
}
