package com.breeze.assignment_5_final;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.breeze.assignment_5_final.roomdb.FormEntity;
import com.breeze.assignment_5_final.databinding.CustomLayoutFormBinding;

import java.util.ArrayList;

public class FormRecyclerViewAdapter extends RecyclerView.Adapter<FormRecyclerViewAdapter.FormRecyclerViewHolder> {

    ArrayList<FormEntity> formEntities;


    public FormRecyclerViewAdapter(ArrayList<FormEntity> formEntities) {
        this.formEntities = formEntities;
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

        final FormEntity formModel=formEntities.get(position);
        holder.binding.setForm(formModel);

    }

    @Override
    public int getItemCount() {
        return formEntities.size();
    }

    public class FormRecyclerViewHolder extends RecyclerView.ViewHolder {

        CustomLayoutFormBinding binding;
        public FormRecyclerViewHolder(CustomLayoutFormBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }
}
