package com.ejbs;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Stateless
@Entity//class is an entity.(creating table for this class in database)
public class Order implements Serializable{// Serializable interface is required for entity classes that are accessed through a remote interface.
	
	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//JPA provider uses the database identity column to generate the primary key.
	int id;
	@OneToMany(mappedBy="list")  //multiple orders instances can belong to a single customer instance.
	//The mappedBy option is used because the order table actually doesn't contain a reference to customer
	Customer customer;
	@OneToMany(mappedBy="list")  //multiple orders instances can belong to a single customer instance.
	//The mappedBy option is used because the order table actually doesn't contain a reference to admin
	Admin admin;
	@ManyToOne
	@JoinColumn(name="id")//tells JPA which column to use when joining to the list of items
	//table, in this example id of item.//Defines the column that JPA uses as the foreign key.
	ArrayList<Item> items = new ArrayList<Item>();
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	

}
