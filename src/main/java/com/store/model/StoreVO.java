package com.store.model;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;

public class StoreVO implements Serializable {
    private Integer storeId;
    private String name;
    private String managerName;
    private String email;
    private String password;
    private String phoneNum;
    private String guiNum;
    private String businessRegNum;
    private Integer reviewed;
    private Timestamp regTime;  
    private Integer points;
    private Integer accStat;
    private Integer opStat;
    private String opTime;
    private String pickTime;
    private String lastOrder;
    private String closeTime;
    private String address;
    private String county;
    private String district;
    private Integer postalCode;
    private Integer starNum;
    
    public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getGuiNum() {
		return guiNum;
	}
	public void setGuiNum(String guiNum) {
		this.guiNum = guiNum;
	}
	public String getBusinessRegNum() {
		return businessRegNum;
	}
	public void setBusinessRegNum(String businessRegNum) {
		this.businessRegNum = businessRegNum;
	}
	public Integer getReviewed() {
		return reviewed;
	}
	public void setReviewed(Integer reviewed) {
		this.reviewed = reviewed;
	}
	public Timestamp getRegTime() {
		return regTime;
	}
	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getAccStat() {
		return accStat;
	}
	public void setAccStat(Integer accStat) {
		this.accStat = accStat;
	}
	public Integer getOpStat() {
		return opStat;
	}
	public void setOpStat(Integer opStat) {
		this.opStat = opStat;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getPickTime() {
		return pickTime;
	}
	public void setPickTime(String pickTime) {
		this.pickTime = pickTime;
	}
	public String getLastOrder() {
		return lastOrder;
	}
	public void setLastOrder(String lastOrder) {
		this.lastOrder = lastOrder;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Integer getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
	public Integer getStarNum() {
		return starNum;
	}
	public void setStarNum(Integer starNum) {
		this.starNum = starNum;
	}
	public Integer getVisitorsNum() {
		return visitorsNum;
	}
	public void setVisitorsNum(Integer visitorsNum) {
		this.visitorsNum = visitorsNum;
	}
	private Integer visitorsNum;
    
	
    
	

    
}
