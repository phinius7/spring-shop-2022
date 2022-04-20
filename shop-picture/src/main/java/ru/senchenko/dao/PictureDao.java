package ru.senchenko.dao;

import ru.senchenko.entities.Picture;

import java.io.Serializable;
import java.util.Date;

public class PictureDao implements Serializable {

    private Integer id;
    private Date createDate;
    private Date modifyDate;
    private String title;
    private String contentType;

    public PictureDao(Picture picture) {
        this.id = picture.getId();
        this.createDate = picture.getCreateDate();
        this.modifyDate = picture.getModifyDate();
        this.title = picture.getTitle();
        this.contentType = picture.getContentType();
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
