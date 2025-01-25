package com.njl.oa.entity;

import java.sql.Timestamp;

/**
 * 办公用品实体类
 */
public class Stationery {
    private Integer stationeryId;       //办公用品ID
    private String stationeryName;      //办公用品名称
    private String stationeryExplain;   //办公用品说明
    private Integer stationeryCount;    //办公用品数量
    private String stationeryClassify;  //办公用品分类
    private Timestamp creationTime;     //办公用品创建时间

    public Integer getStationeryId() {
        return stationeryId;
    }

    public void setStationeryId(Integer stationeryId) {
        this.stationeryId = stationeryId;
    }

    public String getStationeryName() {
        return stationeryName;
    }

    public void setStationeryName(String stationeryName) {
        this.stationeryName = stationeryName;
    }

    public String getStationeryExplain() {
        return stationeryExplain;
    }

    public void setStationeryExplain(String stationeryExplain) {
        this.stationeryExplain = stationeryExplain;
    }

    public Integer getStationeryCount() {
        return stationeryCount;
    }

    public void setStationeryCount(Integer stationeryCount) {
        this.stationeryCount = stationeryCount;
    }

    public String getStationeryClassify() {
        return stationeryClassify;
    }

    public void setStationeryClassify(String stationeryClassify) {
        this.stationeryClassify = stationeryClassify;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Stationery{" +
                "stationeryId=" + stationeryId +
                ", stationeryName='" + stationeryName + '\'' +
                ", stationeryExplain='" + stationeryExplain + '\'' +
                ", stationeryCount=" + stationeryCount +
                ", stationeryClassify='" + stationeryClassify + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
