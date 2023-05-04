package com.example.travelexpertsandroid;

import java.io.Serializable;

public class Customer implements Serializable {
    private int customerId;
    private String custFirstName;
    private String custLastName;
    private String etCustAddress;
    private String etCustCity;
    private String etCustProv;
    private String etCustPostal;
    private String etCustCountry;
    private String etCustHomePhone;
    private String etCustBusPhone;
    private String etCustEmail;
    private int etAgentId;

    public Customer(int customerId, String custFirstName, String custLastName,
                    String etCustAddress, String etCustCity, String etCustProv, String etCustPostal, String etCustCountry,
                    String etCustHomePhone, String etCustBusPhone, String etCustEmail, int etAgentId) {
        this.customerId = customerId;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.etCustAddress = etCustAddress;
        this.etCustCity = etCustCity;
        this.etCustProv = etCustProv;
        this.etCustPostal = etCustPostal;
        this.etCustCountry = etCustCountry;
        this.etCustHomePhone = etCustHomePhone;
        this.etCustBusPhone = etCustBusPhone;
        this.etCustEmail = etCustEmail;
        this.etAgentId = etAgentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getEtCustAddress() {
        return etCustAddress;
    }

    public void setEtCustAddress(String etCustAddress) {
        this.etCustAddress = etCustAddress;
    }

    public String getEtCustCity() {
        return etCustCity;
    }

    public void setEtCustCity(String etCustCity) {
        this.etCustCity = etCustCity;
    }

    public String getEtCustProv() {
        return etCustProv;
    }

    public void setEtCustProv(String etCustProv) {
        this.etCustProv = etCustProv;
    }

    public String getEtCustPostal() {
        return etCustPostal;
    }

    public void setEtCustPostal(String etCustPostal) {
        this.etCustPostal = etCustPostal;
    }

    public String getEtCustCountry() {
        return etCustCountry;
    }

    public void setEtCustCountry(String etCustCountry) {
        this.etCustCountry = etCustCountry;
    }

    public String getEtCustHomePhone() {
        return etCustHomePhone;
    }

    public void setEtCustHomePhone(String etCustHomePhone) {
        this.etCustHomePhone = etCustHomePhone;
    }

    public String getEtCustBusPhone() {
        return etCustBusPhone;
    }

    public void setEtCustBusPhone(String etCustBusPhone) {
        this.etCustBusPhone = etCustBusPhone;
    }

    public String getEtCustEmail() {
        return etCustEmail;
    }

    public void setEtCustEmail(String etCustEmail) {
        this.etCustEmail = etCustEmail;
    }

    public int getEtAgentId() {
        return etAgentId;
    }

    public void setEtAgentId(int etAgentId) {
        this.etAgentId = etAgentId;
    }
}
