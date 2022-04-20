package ru.senchenko.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pictures_md")
public class Picture extends CommonColumn {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "picture_data_id")
    private PictureData pictureData;

    @ManyToOne
    private Product product;

    public Picture() {}

    public Picture(String title, String contentType, PictureData pictureData, Product product) {
        this.title = title;
        this.contentType = contentType;
        this.pictureData = pictureData;
        this.product = product;
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

    public PictureData getPictureData() {
        return pictureData;
    }

    public void setPictureData(PictureData pictureData) {
        this.pictureData = pictureData;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
