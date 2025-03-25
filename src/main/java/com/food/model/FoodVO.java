package com.food.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food")  // 對應資料表名稱
public class FoodVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動遞增主鍵
    @Column(name = "foodId")
    private Integer foodId;

    @Column(name = "storeId", nullable = false)
    private Integer storeId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    
    @CreationTimestamp
    @Column(name = "createdTime")
    private Timestamp createdTime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "photo", length = 255)
    private String photo;

    @Column(name = "cost")
    private Integer cost;

    // 必須要有無參數建構子給 Hibernate 用
    public FoodVO() {}

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

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

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
