package com.foodType.model;

import jakarta.persistence.*;

@Entity
@Table(name = "foodType")
public class FoodTypeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodTypeId")
    private Integer foodTypeId;

    @Column(name = "storeId")
    private Integer storeId;

    @Column(name = "type")
    private String type;

    // --- Getter and Setter ---
    public Integer getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
