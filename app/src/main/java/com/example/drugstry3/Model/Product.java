package com.example.drugstry3.Model;

public class Product {
    public String prodID, prodName, notes, state, priceST, img, dateCreated, price
            , storeName, companyName, catName, phPrice, sPrice, limit
            , rate, validity, type, sort, fk_store, year, month, day;


    public Product(String prodID, String prodName, String notes, String state, String priceST, String img, String dateCreated, String price
            , String storeName, String companyName, String catName, String phPrice
            , String sPrice, String limit, String rate, String validity, String type, String sort
            , String fk_store, String year, String month, String day) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.notes = notes;
        this.state = state;
        this.priceST = priceST;
        this.img = img;
        this.dateCreated = dateCreated;
        this.price = price;
        this.storeName = storeName;
        this.companyName = companyName;
        this.catName = catName;
        this.phPrice = phPrice;
        this.sPrice = sPrice;
        this.limit = limit;
        this.rate = rate;
        this.validity = validity;
        this.type = type;
        this.sort = sort;
        this.fk_store = fk_store;
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public Product(String prodName){
        this.prodName = prodName;
        this.price = "100 sp";
    }

}
