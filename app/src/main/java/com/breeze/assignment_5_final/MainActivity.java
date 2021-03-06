package com.breeze.assignment_5_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.breeze.assignment_5_final.Model.User;
import com.breeze.assignment_5_final.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);


        SharedPreferences sharedPreferences=getSharedPreferences("Login_Details",MODE_PRIVATE);
        String email=sharedPreferences.getString("EmailID","");
        String password=sharedPreferences.getString("Password","");

        //Object to User POJO Class and setting the data if available
        User user=new User(email,password);

        if (email !=null && password !=null)
        {
            //Here the Values would be set to the xml using activityMainBinding
            activityMainBinding.setUser(user);

        }

        activityMainBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sEmail = activityMainBinding.etEmail.getText().toString();
                String sPassword = activityMainBinding.etPassword.getText().toString();

                if (!sEmail.matches(emailPattern)) {
                    activityMainBinding.etEmail.setError("Invalid Email ID !");

                } else if (sPassword.length() < 6) {
                    activityMainBinding.etPassword.setError("Password too short !");
                } else if (activityMainBinding.checkRemember.isChecked()) {
                    sharedLogin(1, sEmail, sPassword);
                } else {
                    sharedLogin(0, sEmail, sPassword);
                }


            }
        });

    }


    private void sharedLogin(int flag,String email,String pass)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("Login_Details",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        if (flag==0)
        {
            //Deleting the value's from SharedPreference As user Didn't click Remember Me
            editor.clear();
            editor.apply();
            Toast.makeText(MainActivity.this, "Logged In Successfully ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, OverallActivity.class));
        }
        else
        {
            //Here flag=1 and Data is Saved in Shared Preference as User Clicked on Remember Me
            editor.putString("EmailID",email);
            editor.putString("Password",pass);
            editor.apply();
            Toast.makeText(MainActivity.this, "Logged In Successfully ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, OverallActivity.class));
        }
    }

}