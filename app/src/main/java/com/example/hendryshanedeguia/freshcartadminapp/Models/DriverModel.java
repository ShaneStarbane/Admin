package com.example.hendryshanedeguia.freshcartadminapp.Models;

/**
 * Created by HendryShanedeGuia on 10/10/2017.
 */

public class DriverModel {
    public String driverEmail;
    public String driverContact;
    public String driverAddress;
    public String driverImageUrl;
    public String driverUsername;
    public String driverID;
    public String timesCancelled;
    public String hasHistory;

    public DriverModel(){

    }
    public DriverModel(String driverEmail, String driverContact, String driverAddress, String driverImageUrl, String driverUsername, String driverID, String timesCancelled, String hasHistory) {
        this.driverEmail = driverEmail;
        this.driverContact = driverContact;
        this.driverAddress = driverAddress;
        this.driverImageUrl = driverImageUrl;
        this.driverUsername = driverUsername;
        this.driverID = driverID;
        this.timesCancelled = timesCancelled;
        this.hasHistory = hasHistory;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public String getDriverImageUrl() {
        return driverImageUrl;
    }

    public String getDriverUsername() {
        return driverUsername;
    }

    public String getDriverID() {
        return driverID;
    }

    public String getTimesCancelled() {
        return timesCancelled;
    }

    public String getHasHistory() {
        return hasHistory;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public void setDriverImageUrl(String driverImageUrl) {
        this.driverImageUrl = driverImageUrl;
    }

    public void setDriverUsername(String driverUsername) {
        this.driverUsername = driverUsername;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public void setTimesCancelled(String timesCancelled) {
        this.timesCancelled = timesCancelled;
    }

    public void setHasHistory(String hasHistory) {
        this.hasHistory = hasHistory;
    }
}
