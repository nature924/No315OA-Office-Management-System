package com.njl.oa.entity;

import java.sql.Timestamp;
import java.util.List;

public class Menu {
    private Integer id;             //菜单ID
    private Integer pid;            //菜单PID
    private String pidTitle;        //菜单PID名称
    private String title;           //菜单名称
    private String href;            //菜单URL
    private String icon;            //菜单图标
    private boolean start;          //菜单状态
    private Timestamp createTime;   //创建时间
    private Timestamp updateTime;   //修改时间
    private String comment;         //菜单描述
    private Integer sort;           //菜单排序：1最大

    private boolean spread;         //是否打开状态
    private List<Menu> children;    //菜单集合
    private boolean checked;        //是否选中
    private boolean disabled;       //是否禁用

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPidTitle() {
        return pidTitle;
    }

    public void setPidTitle(String pidTitle) {
        this.pidTitle = pidTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
