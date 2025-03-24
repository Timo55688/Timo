package com.foodType.model;

import java.util.List;

public class FoodTypeService {

    private FoodTypeDAOinterface dao;

    public FoodTypeService() {
        dao = new FoodTypeJDBCDAO();
    }

    // 新增
    public FoodTypeVO addFoodType(Integer storeId, String type) {
        FoodTypeVO foodTypeVO = new FoodTypeVO();
        foodTypeVO.setStoreId(storeId);
        foodTypeVO.setType(type);
        dao.insert(foodTypeVO);
        return foodTypeVO;
    }

    // 修改
    public FoodTypeVO updateFoodType(Integer foodTypeId, String type) {
        FoodTypeVO foodTypeVO = new FoodTypeVO();
        foodTypeVO.setFoodTypeId(foodTypeId);
        foodTypeVO.setType(type);
        dao.update(foodTypeVO);
        return foodTypeVO;
    }

    // 刪除
    public void deleteFoodType(Integer foodTypeId) {
        dao.delete(foodTypeId);
    }

    // 查一筆
    public FoodTypeVO getOneFoodType(Integer foodTypeId) {
        return dao.findByPrimaryKey(foodTypeId);
    }

    // 查全部
    public List<FoodTypeVO> getAll() {
        return dao.getAll();
    }
}
