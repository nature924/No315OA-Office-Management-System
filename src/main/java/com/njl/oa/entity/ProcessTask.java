package com.njl.oa.entity;

import java.util.Date;

public class ProcessTask {
    private String processTaskId;               //任务节点ID
    private String processTaskExecutionId;      //任务流程ID
    private String processTaskName;             //任务节点名称
    private String processTaskAssignee;         //任务节点执行人
    private Date createTime;                    //任务节点创建时间
    private boolean processTaskIsSuspended;     //任务节点是否停止
    private String processTaskTenantId;         //任务节点租户ID
    private Leave leave = new Leave();          //请假实例

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    public String getProcessTaskTenantId() {
        return processTaskTenantId;
    }

    public void setProcessTaskTenantId(String processTaskTenantId) {
        this.processTaskTenantId = processTaskTenantId;
    }

    public String getProcessTaskId() {
        return processTaskId;
    }

    public void setProcessTaskId(String processTaskId) {
        this.processTaskId = processTaskId;
    }

    public String getProcessTaskExecutionId() {
        return processTaskExecutionId;
    }

    public void setProcessTaskExecutionId(String processTaskExecutionId) {
        this.processTaskExecutionId = processTaskExecutionId;
    }

    public String getProcessTaskName() {
        return processTaskName;
    }

    public void setProcessTaskName(String processTaskName) {
        this.processTaskName = processTaskName;
    }

    public String getProcessTaskAssignee() {
        return processTaskAssignee;
    }

    public void setProcessTaskAssignee(String processTaskAssignee) {
        this.processTaskAssignee = processTaskAssignee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isProcessTaskIsSuspended() {
        return processTaskIsSuspended;
    }

    public void setProcessTaskIsSuspended(boolean processTaskIsSuspended) {
        this.processTaskIsSuspended = processTaskIsSuspended;
    }

    @Override
    public String toString() {
        return "ProcessTask{" +
                "processTaskId='" + processTaskId + '\'' +
                ", processTaskExecutionId='" + processTaskExecutionId + '\'' +
                ", processTaskName='" + processTaskName + '\'' +
                ", processTaskAssignee='" + processTaskAssignee + '\'' +
                ", createTime=" + createTime +
                ", processTaskIsSuspended=" + processTaskIsSuspended +
                ", processTaskTenantId='" + processTaskTenantId + '\'' +
                ", leave=" + leave +
                '}';
    }
}
