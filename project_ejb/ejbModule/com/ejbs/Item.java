package com.ejbs;

import java.io.Serializable;

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
public class Item implements Serializable{// Serializable interface is required for entity classes that are accessed through a remote interface.
	
	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//JPA provider uses the database identity column to generate the primary key.
	int id;
	String name;
	@OneToMany(mappedBy="items")// single instance of order is potentially related to multiple item instances
	Order order;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
