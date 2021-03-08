package com.breeze.assignment_5_final;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.breeze.assignment_5_final.retrofitapi.RetrofitAPI;
import com.breeze.assignment_5_final.retrofitapi.RetrofitClient;
import com.breeze.assignment_5_final.databinding.ActivityRetrofitBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit extends AppCompatActivity {

    private ProgressDialog dialog;

    ActivityRetrofitBinding binding;
    public RetrofitAPI retrofitAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_retrofit);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Retrofit.this);
        binding.retrofitRecyclerView.setLayoutManager(linearLayoutManager);


        retrofitAPI= RetrofitClient.getClient().create(RetrofitAPI.class);
        retrofitAPI.getDetails().enqueue(new Callback<GsonResponse>() {
            @Override
            public void onResponse(Call<GsonResponse> call, Response<GsonResponse> response) {
                RecyclerViewAdapter adapter=new RecyclerViewAdapter(new ArrayList<>(response.body().getContacts()));
                binding.retrofitRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GsonResponse> call, Throwable t) {

            }
        });

    }

}