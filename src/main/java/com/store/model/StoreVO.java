package com.store.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "store")
public class StoreVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId")
    private Integer storeId;

    @Column(name = "name")
    private String name;

    @Column(name = "managerName")
    private String managerName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phoneNum")
    private String phoneNum;

    @Column(name = "guiNum")
    private String guiNum;

    @Column(name = "businessRegNum")
    private String businessRegNum;

    @Column(name = "reviewed")
    private Integer reviewed;
    
    @CreationTimestamp
    @Column(name = "regTime")
    private Timestamp regTime;

    @Column(name = "points")
    private Integer points;

    @Column(name = "accStat")
    private Integer accStat;

    @Column(name = "opStat")
    private Integer opStat;

    @Column(name = "opTime")
    private String opTime;

    @Column(name = "pickTime")
    private String pickTime;

    @Column(name = "lastOrder")
    private String lastOrder;

    @Column(name = "closeTime")
    private String closeTime;

    @Column(name = "address")
    private String address;

    @Column(name = "county")
    private String county;

    @Column(name = "district")
    private String district;

    @Column(name = "postalCode")
    private Integer postalCode;

    @Column(name = "starNum")
    private Integer starNum;

    @Column(name = "visitorsNum")
    private Integer visitorsNum;

    // --- Getter / Setter ---

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
}
