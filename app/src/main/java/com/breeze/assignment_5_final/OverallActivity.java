package com.breeze.assignment_5_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class OverallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall);

        findViewById(R.id.btnVolley).setOnClickListener(view->{
            startActivity(new Intent(OverallActivity.this,Volley.class));
        });

        findViewById(R.id.btnHttp).setOnClickListener(view->{
            startActivity(new Intent(OverallActivity.this,HTTP.class));
        });

        findViewById(R.id.btnRetrofit).setOnClickListener(view->{
            startActivity(new Intent(OverallActivity.this,Retrofit.class));
        });

        findViewById(R.id.btnRecyclerView).setOnClickListener(view->{
            startActivity(new Intent(OverallActivity.this,Form.class));
        });
    }
}