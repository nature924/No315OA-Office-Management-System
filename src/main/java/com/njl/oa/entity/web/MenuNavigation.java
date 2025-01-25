package com.njl.oa.entity.web;

import java.util.List;

/**
 * 菜单导航实体类
 */
public class MenuNavigation {
    private Integer id;
    private String title;
    private String href;
    private String icon;
    private String target;
    private Integer pid;
    private List<MenuNavigation> child;

    public List<MenuNavigation> getChild() {
        return child;
    }

    public void setChild(List<MenuNavigation> child) {
        this.child = child;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "MenuNavigation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", icon='" + icon + '\'' +
                ", target='" + target + '\'' +
                ", pid=" + pid +
                ", child=" + child +
                '}';
    }
}
