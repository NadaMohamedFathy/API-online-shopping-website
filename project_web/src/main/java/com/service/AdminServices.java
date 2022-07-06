package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ejbs.Admin;
import com.ejbs.Customer;
import com.ejbs.Item;
import com.ejbs.MyBean;
import com.ejbs.Order;


@Stateless
@Path("/AdminService")//the class with its own specific @Path value
@Produces(MediaType.APPLICATION_JSON)//defines the type of the response’s content that is returned by the service class or method
@Consumes(MediaType.APPLICATION_JSON)//defines the type of the request's content that is accepted by the service class or method.
public class AdminServices {//implements MyServiceInterface
	//the individual methods  with their own specific @Path value
	//@GET -> used to retrieve data.
		//@POST -> used to save or create data.
	@PersistenceContext
    private EntityManager entityManager;
	@EJB//is injected into this bean
	ArrayList<Admin> admins = new ArrayList<Admin>();
	ArrayList<Item> items = new ArrayList<Item>();
	//MyBean bean;
	
	@POST
	@Path("/registerAsAdmin")
	public String register(Admin admin)//done
	{
		entityManager.persist(admin);
		admins.add(admin);
		return "Admin Added Successfully. ";
	}
	@POST
	@Path("/CreateAnItem")
	public String CreateAnItem(Item item)//done//w2ft
	{
		entityManager.persist(item);
		items.add(item);
		return "Item Added Successfully. ";
	}
	@GET
	@Path("/getRoleofCurrentAdmin")
	public String getRoleofCurrentAdmin(Admin admin)
	{
		admin = entityManager.find(Admin.class, admin.getId());
		if (admin != null ){
			return admin.getRole();
          } else 
              return "Not Found";
	}
	@GET
	@Path("/getRoleofCurrentCustomer")
	public String getRoleofCurrentCustomer(Customer customer)
	{
		customer = entityManager.find(Customer.class, customer.getId());
		if (customer != null){
			return customer.getRole();
          } else 
              return "Not Found";
	}
	@POST
	@Path("/loginAsAdmin")
	public boolean loginAsAdmin(Admin admin)//done
	{
		//h3mlha username w pass
		Admin admin1;
		//admin = entityManager.find(Admin.class, admin.getId());
		admin = entityManager.find(Admin.class, admin.getUsername());
		admin1 = entityManager.find(Admin.class, admin.getPassword());
		if (admin != null && admin1 != null){
			return true;
          } else 
              return false;
          }
	@POST
	@Path("/ViewOrdersByCustomerId/{id}")
	public ArrayList<Order> ViewOrdersByCustomerId(@PathParam("id")int id)//used to retrieve a parameter passed in through the URI
	{
		Customer customer;
		customer = entityManager.find(Customer.class, id);
		if (customer != null){
			return customer.getList();
	      } else 
	          return null;
	      }
	@GET
	@Path("/viewAllOrders")
	public List<Order> viewAllOrders()
	{
		TypedQuery<Order> query = entityManager.createQuery("SELECT * FROM Order o", Order.class);//createQuery method is used to create dynamic queries.
	  	List<Order> orders = query.getResultList();

		  return orders;
	      }
	//@DELETE
	@POST
	@Path("/blockCustomer")
	public String blockCustomer(Customer customer)//done
	{
		customer = entityManager.find(Customer.class,customer.getId());
		if (customer != null){
		entityManager.remove(customer);
        return "Done";
		}
		else
			return "Not Found";

     }
}
