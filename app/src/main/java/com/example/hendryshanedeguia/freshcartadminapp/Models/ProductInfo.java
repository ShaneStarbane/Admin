package com.example.hendryshanedeguia.freshcartadminapp.Models;

/**
 * Created by HendryShanedeGuia on 10/10/2017.
 */

public class ProductInfo {
    public String prodName;
    public String prodPrice;
    public String prodDes;
    public String imageURL;
    public String prodID;
    public String prodCategory;
    public String prodStock;




    public ProductInfo(){

    }

    public ProductInfo(String prodName, String prodPrice, String prodDes, String imageURL, String prodID, String prodCategory, String prodStock) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodDes = prodDes;
        this.imageURL = imageURL;
        this.prodID =  prodID;
        this.prodCategory = prodCategory;
        this.prodStock = prodStock;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public String getProdDes() {
        return prodDes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getProdID() {
        return prodID;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public String getProdStock() {
        return prodStock;
    }
}
