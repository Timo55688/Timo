package com.store.model;

import java.util.List;

public class StoreService {

    private StoreDAO dao;

    public StoreService() {
        dao = new StoreDAO();
    }

    public StoreVO addStore(StoreVO storeVO) {
        dao.insert(storeVO);
        return storeVO;
    }

    public StoreVO updateStore(Integer storeId, String name, String managerName, String email,
            String password, String phoneNum, String guiNum, String businessRegNum,
            Integer opStat, String opTime, String pickTime, String lastOrder,
            String closeTime, String address, String county, String district,
            Integer postalCode) {

        // 從 DB 取得原始資料
        StoreVO original = dao.findByPrimaryKey(storeId);

        StoreVO storeVO = new StoreVO();
        storeVO.setStoreId(storeId);
        storeVO.setName(name);
        storeVO.setManagerName(managerName);
        storeVO.setEmail(email);
        storeVO.setPassword(password);
        storeVO.setPhoneNum(phoneNum);
        storeVO.setGuiNum(guiNum);
        storeVO.setBusinessRegNum(businessRegNum);
        storeVO.setOpStat(opStat);
        storeVO.setOpTime(opTime);
        storeVO.setPickTime(pickTime);
        storeVO.setLastOrder(lastOrder);
        storeVO.setCloseTime(closeTime);
        storeVO.setAddress(address);
        storeVO.setCounty(county);
        storeVO.setDistrict(district);
        storeVO.setPostalCode(postalCode);

        // 保留原本未修改的欄位值
        storeVO.setPoints(original.getPoints());
        storeVO.setAccStat(original.getAccStat());
        storeVO.setRegTime(original.getRegTime());
        storeVO.setReviewed(original.getReviewed());
        storeVO.setStarNum(original.getStarNum());
        storeVO.setVisitorsNum(original.getVisitorsNum());

        dao.update(storeVO);
        return storeVO;
    }

    public void deleteStore(Integer storeId) {
        dao.delete(storeId);
    }

    public StoreVO getOneStore(Integer storeId) {
        return dao.findByPrimaryKey(storeId);
    }

    public List<StoreVO> getAll() {
        return dao.getAll();
    }
}
