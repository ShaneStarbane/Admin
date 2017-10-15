package com.example.hendryshanedeguia.freshcartadminapp.Models;

/**
 * Created by HendryShanedeGuia on 11/10/2017.
 */

public class OrderMainDetailsModel {
    public String orderID;
    public String orderGross;
    public String custContact;
    public String custAddress;
    public String custImageUrl;
    public String custUsername;
    public String orderStatus;
    public String cashOnHand;
    public String noteForDriver;
    public String promo;
    public String discount;
    public String orderVAT;
    public String deliveredBy;
    public String orderBill;



    public OrderMainDetailsModel() {
    }
    public OrderMainDetailsModel(String orderID, String orderGross, String custContact, String custAddress, String custImageUrl, String custUsername,String orderStatus,String cashOnHand, String noteForDriver, String promo, String discount,String orderVAT, String deliveredBy,String orderBill) {
        this.orderID = orderID;
        this.orderGross = orderGross;
        this.custContact = custContact;
        this.custAddress = custAddress;
        this.custImageUrl = custImageUrl;
        this.custUsername = custUsername;
        this.orderStatus = orderStatus;
        this.cashOnHand = cashOnHand;
        this.noteForDriver = noteForDriver;
        this.promo = promo;
        this.discount = discount;
        this.orderVAT = orderVAT;
        this.deliveredBy = deliveredBy;
        this.orderBill =  orderBill;
    }

    public String getOrderGross() {
        return orderGross;
    }

    public String getPromo() {
        return promo;
    }

    public String getDiscount() {
        return discount;
    }

    public String getOrderVAT() {
        return orderVAT;
    }

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public void setOrderGross(String orderGross) {
        this.orderGross = orderGross;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setOrderVAT(String orderVAT) {
        this.orderVAT = orderVAT;
    }

    public void setDeliveredBy(String deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    public String getCashOnHand() {
        return cashOnHand;
    }

    public String getNoteForDriver() {
        return noteForDriver;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getOrderBill() {
        return orderBill;
    }

    public String getCustContact() {
        return custContact;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public String getCustImageUrl() {
        return custImageUrl;
    }

    public String getCustUsername() {
        return custUsername;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderBill(String orderBill) {
        this.orderBill = orderBill;
    }

    public void setCustContact(String custContact) {
        this.custContact = custContact;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public void setCustImageUrl(String custImageUrl) {
        this.custImageUrl = custImageUrl;
    }

    public void setCustUsername(String custUsername) {
        this.custUsername = custUsername;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCashOnHand(String cashOnHand) {
        this.cashOnHand = cashOnHand;
    }

    public void setNoteForDriver(String noteForDriver) {
        this.noteForDriver = noteForDriver;
    }
}
