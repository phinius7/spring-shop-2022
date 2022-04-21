package ru.senchenko.dao;

import ru.senchenko.entities.Role;

import java.util.Date;

public class RoleDao {

    private Integer id;
    private Date createDate;
    private Date modifyDate;
    private String title;

    public RoleDao() {}

    public RoleDao(Role role) {
        this.id = role.getId();
        this.createDate = role.getCreateDate();
        this.modifyDate = role.getModifyDate();
        this.title = role.getTitle();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
