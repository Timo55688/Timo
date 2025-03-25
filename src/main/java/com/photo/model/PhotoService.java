package com.photo.model;

import java.util.List;

public class PhotoService {

    private PhotoDAOInterface dao;

    public PhotoService() {
        dao = new PhotoDAO(); // Hibernate 版本
    }

    public PhotoVO addPhoto(Integer storeId, byte[] photoSrc) {
        PhotoVO photoVO = new PhotoVO();
        photoVO.setStoreId(storeId);
        photoVO.setPhotoSrc(photoSrc);
        dao.insert(photoVO);
        return photoVO;
    }

    public PhotoVO updatePhoto(Integer photoId, Integer storeId, byte[] photoSrc, java.sql.Timestamp updateTime) {
        PhotoVO photoVO = new PhotoVO();
        photoVO.setPhotoId(photoId);
        photoVO.setStoreId(storeId);
        photoVO.setPhotoSrc(photoSrc);
        photoVO.setUpdateTime(updateTime);
        dao.update(photoVO);
        return photoVO;
    }

    public void deletePhoto(Integer photoId) {
        dao.delete(photoId);
    }

    public PhotoVO getOnePhoto(Integer photoId) {
        return dao.findByPrimaryKey(photoId);
    }

    public List<PhotoVO> getAll() {
        return dao.getAll();
    }
} 