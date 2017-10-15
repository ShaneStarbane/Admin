package com.example.hendryshanedeguia.freshcartadminapp.Models;

/**
 * Created by HendryShanedeGuia on 06/10/2017.
 */


public class CustomerModel {
    public String custEmail;
    public String custContact;
    public String custAddress;
    public String custImageUrl;
    public String custUsername;
    public String custID;
    public String timesCancelled;

    public CustomerModel(){

    }

    public CustomerModel(String custEmail, String custContact, String custAddress, String custImageUrl, String custUsername, String custID, String timesCancelled) {
        this.custEmail = custEmail;
        this.custContact = custContact;
        this.custAddress = custAddress;
        this.custImageUrl = custImageUrl;
        this.custUsername = custUsername;
        this.custID = custID;
        this.timesCancelled = timesCancelled;
    }

    public String getCustEmail() {
        return custEmail;
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

    public String getCustID() {
        return custID;
    }

    public String getTimesCancelled() {
        return timesCancelled;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
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

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public void setTimesCancelled(String timesCancelled) {
        this.timesCancelled = timesCancelled;
    }

}
