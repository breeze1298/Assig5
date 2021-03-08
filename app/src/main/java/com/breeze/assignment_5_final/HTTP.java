package com.breeze.assignment_5_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;

public class HTTP extends AppCompatActivity {

    RecyclerView recyclerView;
    private String url = "https://api.androidhive.info/contacts/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        recyclerView=findViewById(R.id.httpRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HTTP.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        new getData().execute();


    }

    class getData extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            String response=HTTPHandler.serviceCall(url);

            Gson gson=new Gson();
            GsonResponse res=gson.fromJson(response, GsonResponse.class);
            RecyclerViewAdapter adapter=new RecyclerViewAdapter(res.getContacts());


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setAdapter(adapter);
                }
            });

            return null;
        }

    }
}