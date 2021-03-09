package com.breeze.assignment_5_final;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.breeze.assignment_5_final.databinding.CustomLayoutFormBinding;
import com.breeze.assignment_5_final.roomdb.DatabaseClient;
import com.breeze.assignment_5_final.roomdb.FormEntity;

import java.util.ArrayList;

public class FormRecyclerViewAdapter extends RecyclerView.Adapter<FormRecyclerViewAdapter.FormRecyclerViewHolder> {

    ArrayList<FormEntity> formEntities;
    private final Context context;
    private final String ePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String s1,s2,s3,s4;


    public FormRecyclerViewAdapter(Context context,ArrayList<FormEntity> formEntities) {
        this.formEntities = formEntities;
        this.context=context;
    }

    @NonNull
    @Override
    public FormRecyclerViewAdapter.FormRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        CustomLayoutFormBinding binding=CustomLayoutFormBinding.inflate(layoutInflater, parent, false);

        return new FormRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FormRecyclerViewAdapter.FormRecyclerViewHolder holder, int position) {

        final FormEntity formEntity=formEntities.get(position);
        holder.binding.setForm(formEntity);
        holder.binding.editDetails.setOnClickListener(v -> {
                editDialog(formEntity);
        });

        holder.binding.deleteDetails.setOnClickListener(v->{
            DatabaseClient.getInstance(context).getUserDao().delete(formEntity);
            Toast.makeText(context, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
            context.startActivity(((Activity) context).getIntent());

        });

    }

    @Override
    public int getItemCount() {
        return formEntities.size();
    }

    public static class FormRecyclerViewHolder extends RecyclerView.ViewHolder {

        CustomLayoutFormBinding binding;
        public FormRecyclerViewHolder(CustomLayoutFormBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }


    public void editDialog(final FormEntity formEntity)
    {

        LayoutInflater inflater = LayoutInflater.from(context);

        View subView = inflater.inflate(R.layout.add_from, null);
        EditText name = subView.findViewById(R.id.et_name);
        EditText email = subView.findViewById(R.id.et_email);
        EditText mobile = subView.findViewById(R.id.et_mobile);
        EditText address = subView.findViewById(R.id.et_address);

        if (formEntity!=null)
        {

            name.setText(formEntity.name);
            email.setText(formEntity.email);
            mobile.setText(formEntity.mobile);
            address.setText(formEntity.address);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Details");
        builder.setView(subView);
        builder.setPositiveButton("Edit Details", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });


        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Boolean error=false;

                s1 = name.getText().toString();
                s2 = email.getText().toString();
                s3 = mobile.getText().toString();
                s4 = address.getText().toString();

                error=true;

                //Validating all the inputs
                if (s1.isEmpty())
                {
                    error=true;
                    name.setError("Name Field Cannot be Empty");
                }
                else if (!s2.matches(ePattern))
                {
                    error=true;
                    email.setError("Invalid Email Id");
                }
                else if (s4.isEmpty())
                {
                    error=true;
                    address.setError("Address Field Cannot be Empty ");
                }else if (s3.length()==10)
                {
                    error=false;
                    new editTask().execute();
                    ((Activity)context).finish();
                    context.startActivity(((Activity) context).getIntent());
                }
                else {
                    error=true;
                    mobile.setError("Invalid Mobile Number");
                }
                if (!error)
                {
                    alertDialog.dismiss();
                }


            }
        });


    }


    class editTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //creating a task
            FormEntity edit = new FormEntity(s1,s2,s3,s4);
            //Updating values to database
            DatabaseClient.getInstance(context).getUserDao().update(edit);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, "Data Edited Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
