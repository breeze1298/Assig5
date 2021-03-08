package com.breeze.assignment_5_final;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.breeze.assignment_5_final.databinding.CustomLayoutBinding;
import com.breeze.assignment_5_final.pojo.ContactsItem;

import java.util.ArrayList;

public class RecyclerViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    ArrayList<ContactsItem> contactsItems;

    public RecyclerViewAdapter(ArrayList<ContactsItem> contactsItems) {
        this.contactsItems = contactsItems;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());

        CustomLayoutBinding customLayoutBinding=CustomLayoutBinding.inflate(layoutInflater,parent,false);

        /*View listItem=layoutInflater.inflate(R.layout.custom_layout,parent,false);
        RecyclerViewHolder viewHolder=new RecyclerViewHolder(listItem);*/
        return new RecyclerViewHolder(customLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        final ContactsItem contactsItem=contactsItems.get(position);
        holder.customLayoutBinding.setData(contactsItem);


    }

    @Override
    public int getItemCount() {
        return contactsItems.size();
    }

    public class RecyclerViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{

        CustomLayoutBinding customLayoutBinding;
        public RecyclerViewHolder(@NonNull CustomLayoutBinding customLayoutBinding) {
            super(customLayoutBinding.getRoot());
            this.customLayoutBinding=customLayoutBinding;
        }
    }
}
