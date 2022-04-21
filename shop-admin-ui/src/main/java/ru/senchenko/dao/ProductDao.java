package ru.senchenko.dao;

import org.springframework.web.multipart.MultipartFile;
import ru.senchenko.entities.Category;
import ru.senchenko.entities.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductDao {

    private Integer id;
    private Date createDate;
    private Date modifyDate;
    private String title;
    private BigDecimal price;
    private String description;
    private Category category;

    private List<PictureDao> pictures;

    private MultipartFile[] newPicture;

    public ProductDao() {}

    public ProductDao(Product product) {
        this.id = product.getId();
        this.createDate = product.getCreateDate();
        this.modifyDate = product.getModifyDate();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.pictures = product.getPictures().stream()
                .map(PictureDao::new)
                .collect(Collectors.toList());
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<PictureDao> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureDao> pictures) {
        this.pictures = pictures;
    }

    public MultipartFile[] getNewPicture() {
        return newPicture;
    }

    public void setNewPicture(MultipartFile[] newPicture) {
        this.newPicture = newPicture;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductDao that = (ProductDao) obj;
        return Objects.equals(id, that.id);
    }
}
