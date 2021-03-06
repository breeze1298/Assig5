package com.breeze.assignment_5_final.RetrofitAPI;



import com.breeze.assignment_5_final.Model.ContactsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("/contacts")
    Call<List<ContactsItem>> getDetails();
}
