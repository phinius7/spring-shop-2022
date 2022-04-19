package ru.senchenko.dao;

import ru.senchenko.entities.Category;

import java.util.Date;

public class CategoryDao {

    private Integer id;
    private Date createDate;
    private Date modifyDate;
    private String title;
    private String article;

    public CategoryDao() {}

    public CategoryDao(Category category) {
        this.id = category.getId();
        this.createDate = category.getCreateDate();
        this.modifyDate = category.getModifyDate();
        this.title = category.getTitle();
        this.article = category.getArticle();
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return title;
    }
}
