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

@Stateless
@Entity//class is an entity.(creating table for this class in database)
public class Customer implements Serializable{// Serializable interface is required for entity classes that are accessed through a remote interface.
	
	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//JPA provider uses the database identity column to generate the primary key.
	int id;
	String username;
	String password;
	String role;
	@ManyToOne
	@JoinColumn(name="id")//tells JPA which column to use when joining to the list of orders
	//table, in this example id of order.//Defines the column that JPA uses as the foreign key.
	ArrayList<Order> list = new ArrayList<Order>();
	
	 @Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public ArrayList<Order> getList() {
		return list;
	}
	public void setList(ArrayList<Order> list) {
		this.list = list;
	} 
	
	
	

}
