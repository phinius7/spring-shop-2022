package ru.senchenko.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends CommonColumn {

    @Column(name = "title")
    private String title;

    @Column(name = "article")
    private String article;

    @OneToMany(mappedBy = "category")
    List<Product> products = new ArrayList<>();

    public Category() {}

    public Category(String title, String article) {
        this.title = title;
        this.article = article;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "(" + article + ") " + title;
    }
}
