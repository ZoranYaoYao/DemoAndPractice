package com.example.zoran.zqs_databinding_study.custombinding;

/**
 * Created by zqs on 2018/2/5.
 */

public class Contact {

    private String tel;
    private String address;

    public Contact(String tel, String address) {
        this.tel = tel;
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
