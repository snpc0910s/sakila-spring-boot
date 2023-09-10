package com.example.demo.dto;
import java.util.Date;

public class StoreDTO {
    private Integer storeId;

    private Integer managerStaffId;

    private Integer addressId;

    private Date lastUpdate;

	// getter and setter
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    public Integer getManagerStaffId() {
        return managerStaffId;
    }
    public void setManagerStaffId(Integer managerStaffId) {
        this.managerStaffId = managerStaffId;
    }
    public Integer getAddressId() {
        return addressId;
    }
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
}
