package com.example.demo.entity;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_staff_id")
    private Staff managerStaff;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "store")
    private List<Staff> lstStaff = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Inventory> lstInventory = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Customer> lstCustomer = new ArrayList<>();

    // getter and setter for @OneToMany
    public List<Staff> getLstStaff() {
        return lstStaff;
    }
    public void setLstStaff(List<Staff> lstStaff) {
        this.lstStaff = lstStaff;
    }
    public List<Inventory> getLstInventory() {
        return lstInventory;
    }
    public void setLstInventory(List<Inventory> lstInventory) {
        this.lstInventory = lstInventory;
    }
    public List<Customer> getLstCustomer() {
        return lstCustomer;
    }
    public void setLstCustomer(List<Customer> lstCustomer) {
        this.lstCustomer = lstCustomer;
    }

    // getter and setter
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    public Staff getManagerStaff() {
        return managerStaff;
    }
    public void setManagerStaff(Staff managerStaff) {
        this.managerStaff = managerStaff;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}