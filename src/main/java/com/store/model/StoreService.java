package com.store.model;

import java.util.List;

public class StoreService {

    private StoreDAOInterface dao;

    public StoreService() {
        dao = new StoreJDBCDAO();
    }

    // 新增店家
    public void addStore(StoreVO storeVO) {
        dao.insert(storeVO);
    }

    // 修改店家
//    public StoreVO updateStore(StoreVO storeVO) {
//        dao.update(storeVO);
//        return storeVO;
//    }

    // 修改店家（可選用另一版：傳參數）
    public StoreVO updateStore(Integer storeId, String name, String managerName, String email, String password,
                               String phoneNum, String guiNum, String businessRegNum,
                               Integer opStat, String opTime, String pickTime,
                               String lastOrder, String closeTime, String address, String county,
                               String district, Integer postalCode) {

        StoreVO storeVO = new StoreVO();
        storeVO.setStoreId(storeId);
        storeVO.setName(name);
        storeVO.setManagerName(managerName);
        storeVO.setEmail(email);
        storeVO.setPassword(password);
        storeVO.setPhoneNum(phoneNum);
        storeVO.setGuiNum(guiNum);
        storeVO.setBusinessRegNum(businessRegNum);
//        storeVO.setReviewed(reviewed);
//        storeVO.setPoints(points);
//        storeVO.setAccStat(accStat);
        storeVO.setOpStat(opStat);
        storeVO.setOpTime(opTime);
        storeVO.setPickTime(pickTime);
        storeVO.setLastOrder(lastOrder);
        storeVO.setCloseTime(closeTime);
        storeVO.setAddress(address);
        storeVO.setCounty(county);
        storeVO.setDistrict(district);
        storeVO.setPostalCode(postalCode);
//        storeVO.setStarNum(starNum);
//        storeVO.setVisitorsNum(visitorsNum);

        dao.update(storeVO);
        return storeVO;
    }

    // 查一筆
    public StoreVO getOneStore(Integer storeId) {
        return dao.findByPrimaryKey(storeId);
    }

    // 查全部
    public List<StoreVO> getAll() {
        return dao.getAll();
    }

    // 刪除
    public void deleteStore(Integer storeId) {
        dao.delete(storeId);
    }
}
