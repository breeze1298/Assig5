package com.breeze.assignment_5_final.RetrofitAPI;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.breeze.assignment_5_final.Model.ContactsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class GetDetailsViewModel extends ViewModel {

    public MutableLiveData<ContactsItem> contactsLiveData;
    public MutableLiveData<Boolean> progress;

    public RetrofitAPI retrofitAPI;

    public void init()
    {
        retrofitAPI=RetrofitClient.getClient().create(RetrofitAPI.class);
        contactsLiveData=new MutableLiveData();
        progress=new MutableLiveData<>();

    }

    public void getDetails()
    {
        progress.postValue(true);
        retrofitAPI.getDetails().enqueue(new Callback<List<ContactsItem>>() {
            @Override
            public void onResponse(Call<List<ContactsItem>> call, retrofit2.Response<List<ContactsItem>> response) {

                contactsLiveData.postValue(response.body().get(0));
                progress.postValue(false);

            }

            @Override
            public void onFailure(Call<List<ContactsItem>> call, Throwable t) {

                contactsLiveData.postValue(null);
                progress.postValue(false);

            }
        });
    }
}
