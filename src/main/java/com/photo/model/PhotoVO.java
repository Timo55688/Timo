package com.photo.model;

import java.sql.Timestamp;

public class PhotoVO {
    private Integer photoId;
    private Integer storeId;
    private String photoSrc;
    private Timestamp updateTime;
    
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
    public String getPhotoSrc() {
        return photoSrc;
    }
    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }
    public Timestamp getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
