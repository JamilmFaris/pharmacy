package com.example.drugstry3.Model;

import com.example.drugstry3.Pages.Repositories;

public class Company {
    public String CoID, CoName;
    public Repository Store;
    String StoreName;
    public String address , phone, notes, state, img, dateCreated, fk_store;

    public Company(String coID, String coName, Repository store
            , String address, String phone, String notes, String state
            , String img, String dateCreated, String fk_store) {
        CoID = coID;
        CoName = coName;
        Store = store;
        this.address = address;
        this.phone = phone;
        this.notes = notes;
        this.state = state;
        this.img = img;
        this.dateCreated = dateCreated;
        this.fk_store = fk_store;
        StoreName = store.SName;
    }
    public Company(String CoName){
        this.CoName = CoName;
    }
}
