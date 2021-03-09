package com.breeze.assignment_5_final.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FormDao {
    @Insert
    void insert(FormEntity form);

    @Query("SELECT * FROM FormEntity")
    List<FormEntity> getFormDetails();

    @Update
    void update(FormEntity formEntity);

    @Delete
    void delete(FormEntity formEntity);



}
