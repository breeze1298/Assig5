package com.breeze.assignment_5_final.Model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class GsonResponse {

	@SerializedName("contacts")
	private ArrayList<ContactsItem> contacts;

	public void setContacts(ArrayList<ContactsItem> contacts){
		this.contacts = contacts;
	}

	public ArrayList<ContactsItem> getContacts(){
		return contacts;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"contacts = '" + contacts + '\'' + 
			"}";
		}
}