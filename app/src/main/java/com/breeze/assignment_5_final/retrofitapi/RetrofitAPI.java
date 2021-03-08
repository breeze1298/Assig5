package com.breeze.assignment_5_final.retrofitapi;



import com.breeze.assignment_5_final.GsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("contacts")
    Call<GsonResponse> getDetails();
}
