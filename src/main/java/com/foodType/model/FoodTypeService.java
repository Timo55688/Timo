package com.foodType.model;

import java.util.List;

public class FoodTypeService {

    private FoodTypeDAO dao;

    public FoodTypeService() {
        dao = new FoodTypeDAO();
    }

    public FoodTypeVO addFoodType(Integer storeId, String type) {
        FoodTypeVO vo = new FoodTypeVO();
        vo.setStoreId(storeId);
        vo.setType(type);
        dao.insert(vo);
        return vo;
    }

    public FoodTypeVO updateFoodType(Integer foodTypeId, String type) {
        FoodTypeVO vo = dao.findByPrimaryKey(foodTypeId);
        if (vo != null) {
            vo.setType(type);
            dao.update(vo);
        }
        return vo;
    }

    public void deleteFoodType(Integer foodTypeId) {
        dao.delete(foodTypeId);
    }

    public FoodTypeVO getOneFoodType(Integer foodTypeId) {
        return dao.findByPrimaryKey(foodTypeId);
    }

    public List<FoodTypeVO> getAll() {
        return dao.getAll();
    }
}
