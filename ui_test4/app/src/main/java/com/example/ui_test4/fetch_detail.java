package com.example.ui_test4;

import android.net.Uri;

public class fetch_detail {
    public String name;
    public String address;
    public String phone_no;
    public String image;
    public String status;

    public fetch_detail() {
    }

    public fetch_detail(String name, String address, String phone_no, String image,String status) {
        this.name = name;
        this.address = address;
        this.phone_no = phone_no;
        this.image = image;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}