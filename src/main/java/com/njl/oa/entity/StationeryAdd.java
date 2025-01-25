package com.njl.oa.entity;

import java.sql.Timestamp;

public class StationeryAdd {
    private Integer stationeryAddId;        //办公用品添加ID
    private Integer stationeryId;           //办公用品ID
    private Integer stationeryCount;        //办公用品数量
    private String stationeryExplain;       //办公用品说明
    private Timestamp creationTime;         //添加时间

    public Integer getStationeryAddId() {
        return stationeryAddId;
    }

    public void setStationeryAddId(Integer stationeryAddId) {
        this.stationeryAddId = stationeryAddId;
    }

    public Integer getStationeryId() {
        return stationeryId;
    }

    public void setStationeryId(Integer stationeryId) {
        this.stationeryId = stationeryId;
    }

    public Integer getStationeryCount() {
        return stationeryCount;
    }

    public void setStationeryCount(Integer stationeryCount) {
        this.stationeryCount = stationeryCount;
    }

    public String getStationeryExplain() {
        return stationeryExplain;
    }

    public void setStationeryExplain(String stationeryExplain) {
        this.stationeryExplain = stationeryExplain;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "StationeryAdd{" +
                "stationeryAddId=" + stationeryAddId +
                ", stationeryId=" + stationeryId +
                ", stationeryCount=" + stationeryCount +
                ", stationeryExplain='" + stationeryExplain + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
