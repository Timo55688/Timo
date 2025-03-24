package com.photo.model;

import java.util.List;

public class PhotoService {

    private PhotoDAOInterface dao;

    public PhotoService() {
        dao = new PhotoJDBCDAO();
    }

    // 新增
    public PhotoVO addPhoto(Integer storeId, byte[] photoSrc) {
        PhotoVO photoVO = new PhotoVO();
        photoVO.setStoreId(storeId);
        photoVO.setPhotoSrc(photoSrc);
        dao.insert(photoVO);
        return photoVO;
    }

    // 修改
    public PhotoVO updatePhoto(Integer photoId, Integer storeId, byte[] photoSrc, java.sql.Timestamp updateTime) {
        PhotoVO photoVO = new PhotoVO();
        photoVO.setPhotoId(photoId);
        photoVO.setStoreId(storeId);
        photoVO.setPhotoSrc(photoSrc);
        photoVO.setUpdateTime(updateTime);
        dao.update(photoVO);
        return photoVO;
    }

    // 刪除
    public void deletePhoto(Integer photoId) {
        dao.delete(photoId);
    }

    // 查一筆
    public PhotoVO getOnePhoto(Integer photoId) {
        return dao.findByPrimaryKey(photoId);
    }

    // 查全部
    public List<PhotoVO> getAll() {
        return dao.getAll();
    }
}
