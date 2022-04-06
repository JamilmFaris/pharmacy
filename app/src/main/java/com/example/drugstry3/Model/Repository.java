package com.example.drugstry3.Model;

public class Repository {
    public String SID, SName, address, phone, phone2, catCount, prodCount
            , notes, state, image, password, cityName, dateCreated, fk_city, rate;

    public Repository(String SID, String SName, String address, String phone
            , String phone2, String catCount, String productCount, String notes
            , String state, String image, String password, String cityName, String dateCreated
            , String fk_city, String rate) {
        this.SID = SID;
        this.SName = SName;
        this.address = address;
        this.phone = phone;
        this.phone2 = phone2;
        this.catCount = catCount;
        this.prodCount = productCount;
        this.notes = notes;
        this.state = state;
        this.image = image;
        this.password = password;
        this.cityName = cityName;
        this.dateCreated = dateCreated;
        this.fk_city = fk_city;
        this.rate = rate;
    }

    public Repository(String SName) {
        this.SName = SName;
    }
}
