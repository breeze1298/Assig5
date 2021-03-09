package com.breeze.assignment_5_final;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.breeze.assignment_5_final.roomdb.DatabaseClient;
import com.breeze.assignment_5_final.roomdb.FormEntity;

import java.util.ArrayList;

public class Form extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView addForm;
    String s1, s2, s3, s4;
    private String ePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        recyclerView=findViewById(R.id.recyclerDetails);
        addForm=findViewById(R.id.formAdd);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<FormEntity> allDetails=new ArrayList<>(DatabaseClient.getInstance(Form.this).getUserDao().getFormDetails());

       FormRecyclerViewAdapter adapter=new FormRecyclerViewAdapter(Form.this,allDetails);
        recyclerView.setAdapter(adapter);

        addForm.setOnClickListener(doThis -> {

            addForm();

        });

    }

    private void addForm() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_from, null);
        EditText name = subView.findViewById(R.id.et_name);
        EditText email = subView.findViewById(R.id.et_email);
        EditText mobile = subView.findViewById(R.id.et_mobile);
        EditText address = subView.findViewById(R.id.et_address);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Details");
        builder.setView(subView);
        builder.setPositiveButton("Add Details", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Form.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });


        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean error = false;

                s1 = name.getText().toString();
                s2 = email.getText().toString();
                s3 = mobile.getText().toString();
                s4 = address.getText().toString();

                error = true;

                //Validating all the inputs
                if (s1.isEmpty()) {
                    error = true;
                    name.setError("Name Field Cannot be Empty");
                } else if (!s2.matches(ePattern)) {
                    error = true;
                    email.setError("Invalid Email Id");
                } else if (s4.isEmpty()) {
                    error = true;
                    address.setError("Address Field Cannot be Empty ");
                } else if (s3.length() == 10) {
                    error = false;

                    new SaveTask().execute();
                    finish();
                    startActivity(getIntent());

                } else {
                    error = true;
                    mobile.setError("Invalid Mobile Number");
                }
                if (!error) {
                    alertDialog.dismiss();
                }


            }
        });
    }

    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //creating a task
            FormEntity task = new FormEntity(s1,s2,s3,s4);
            //adding to database
            DatabaseClient.getInstance(Form.this).getUserDao().insert(task);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
            Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}