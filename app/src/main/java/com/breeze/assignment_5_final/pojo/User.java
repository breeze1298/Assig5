package com.breeze.assignment_5_final.pojo;

import androidx.databinding.BaseObservable;

public class User extends BaseObservable {

    private String emailID,password;


    public User(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmailID() {
        return emailID;
    }

    public String getPassword() {
        return password;
    }


}
