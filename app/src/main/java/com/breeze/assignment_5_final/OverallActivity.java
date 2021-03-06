package com.breeze.assignment_5_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class OverallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall);

        findViewById(R.id.btnVolley).setOnClickListener(view->{
            startActivity(new Intent(OverallActivity.this,Volley.class));
        });
    }
}