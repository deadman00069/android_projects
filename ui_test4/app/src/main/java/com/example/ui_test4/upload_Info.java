package com.example.ui_test4;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

public class upload_Info {
   public String name;
    public String address;
    public String phone_no;
    public String image;
    public String status;

    public upload_Info() {
    }

    public upload_Info(String name, String address, String phone_no,String status,String image) {
        this.name = name;
        this.address = address;
        this.phone_no = phone_no;
        this.image = image;
        this.status=status;
    }
}
