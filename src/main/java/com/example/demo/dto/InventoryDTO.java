package com.example.demo.dto;
import java.util.Date;

public class InventoryDTO {
    private Integer inventoryId;

    private Integer filmId;

    private Integer storeId;

    private Date lastUpdate;

	// getter and setter
    public Integer getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
    public Integer getFilmId() {
        return filmId;
    }
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
}
