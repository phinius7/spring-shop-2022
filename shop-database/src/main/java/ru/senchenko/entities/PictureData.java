package ru.senchenko.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "pictures_data")
public class PictureData extends CommonColumn {

    @Lob
    @Column(name = "data", length = 33554430)
    private byte[] data;

    public PictureData() {}

    public PictureData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
