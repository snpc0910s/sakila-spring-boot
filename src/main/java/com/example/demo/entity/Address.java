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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district")
    private String district;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "location")
    private String location;

    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "address")
    private List<Staff> lstStaff = new ArrayList<>();

    @OneToMany(mappedBy = "address")
    private List<Store> lstStore = new ArrayList<>();

    @OneToMany(mappedBy = "address")
    private List<Customer> lstCustomer = new ArrayList<>();

    // getter and setter for @OneToMany
    public List<Staff> getLstStaff() {
        return lstStaff;
    }
    public void setLstStaff(List<Staff> lstStaff) {
        this.lstStaff = lstStaff;
    }
    public List<Store> getLstStore() {
        return lstStore;
    }
    public void setLstStore(List<Store> lstStore) {
        this.lstStore = lstStore;
    }
    public List<Customer> getLstCustomer() {
        return lstCustomer;
    }
    public void setLstCustomer(List<Customer> lstCustomer) {
        this.lstCustomer = lstCustomer;
    }

    // getter and setter
    public Integer getAddressId() {
        return addressId;
    }
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}