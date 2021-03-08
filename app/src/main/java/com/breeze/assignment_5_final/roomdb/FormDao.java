package com.breeze.assignment_5_final.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FormDao {
    @Insert
    void insert(FormEntity form);

    @Insert
    void insertAll(FormEntity... form);

    @Query("SELECT * FROM FormEntity")
    List<FormEntity> getFormDetails();

//    @Update
//    List<FormEntity> updateFormDetails();

//    @Query("select * from form_details where id in (:)" )

}
