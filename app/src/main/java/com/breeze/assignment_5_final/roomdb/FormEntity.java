package com.breeze.assignment_5_final.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FormEntity {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "Name")
    public String name;
    @ColumnInfo(name = "Email")
    public String email;
    @ColumnInfo(name = "Mobile")
    public String mobile;
    @ColumnInfo(name = "Address")
    public String address;

    public FormEntity(String name, String email, String mobile, String address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

}
