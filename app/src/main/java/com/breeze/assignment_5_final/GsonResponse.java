package com.breeze.assignment_5_final;

import com.breeze.assignment_5_final.pojo.ContactsItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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