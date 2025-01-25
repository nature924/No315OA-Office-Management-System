package com.njl.oa.entity;

import java.sql.Timestamp;

/**
 * 系统公告实体类
 */
public class Notice {

    private Integer noticeId;               //公告ID
    private String noticeTheme;             //公告主题
    private String noticeContent;           //公告内容
    private String noticeEmployeeNumber;    //发布公告人
    private Timestamp noticeTime;           //创建公告时间

    private Timestamp issueTime;            //发布公告时间
    private Integer nerId;                  //发布公告ID

    public Integer getNerId() {
        return nerId;
    }

    public void setNerId(Integer nerId) {
        this.nerId = nerId;
    }

    public Timestamp getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Timestamp issueTime) {
        this.issueTime = issueTime;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTheme() {
        return noticeTheme;
    }

    public void setNoticeTheme(String noticeTheme) {
        this.noticeTheme = noticeTheme;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeEmployeeNumber() {
        return noticeEmployeeNumber;
    }

    public void setNoticeEmployeeNumber(String noticeEmployeeNumber) {
        this.noticeEmployeeNumber = noticeEmployeeNumber;
    }

    public Timestamp getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Timestamp noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeTheme='" + noticeTheme + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeEmployeeNumber='" + noticeEmployeeNumber + '\'' +
                ", noticeTime=" + noticeTime +
                ", issueTime=" + issueTime +
                ", nerId=" + nerId +
                '}';
    }
}
