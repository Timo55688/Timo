package com.photo.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "photo")
public class PhotoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photoId")
    private Integer photoId;

    @Column(name = "storeId", nullable = false)
    private Integer storeId;

    @Lob
    @Column(name = "photoSrc")
    private byte[] photoSrc;
    
    @CreationTimestamp
    @Column(name = "updateTime")
    private Timestamp updateTime;

    // Getter & Setter
    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public byte[] getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(byte[] photoSrc) {
        this.photoSrc = photoSrc;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
