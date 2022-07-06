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
@Path("/CustomerService")//the class with its own specific @Path value
@Produces(MediaType.APPLICATION_JSON)//defines the type of the response’s content that is returned by the service class or method
@Consumes(MediaType.APPLICATION_JSON)//defines the type of the request's content that is accepted by the service class or method.
public class CustomerService {//implements MyServiceInterface
//the individual methods  with their own specific @Path value
	//@GET -> used to retrieve data.
	//@POST -> used to save or create data.
	@PersistenceContext
    private EntityManager entityManager;
	@EJB//is injected into this bean
	ArrayList<Customer> customers = new ArrayList<Customer>();
	//MyBean bean;
	
	
	
	@POST
	@Path("/registerAsCustomer")
	public String register(Customer customer)//done
	{
		entityManager.persist(customer);
		customers.add(customer);
		return "Customer Added Successfully. ";
	}
	@GET
	@Path("/getCutomerRoles")
	public String getRoleofCurrentCustomer(Customer customer)//done
	{
		customer = entityManager.find(Customer.class, customer.getId());
		if (customer != null){
			return customer.getRole();
          } else 
              return "Not Found";
          }
	
	@POST
	@Path("/loginAsCustomer")
	public boolean loginAsCustomer(Customer customer)//done
	{
		//h3mlha username w pass
		Customer customer1;
		//customer = entityManager.find(Customer.class, customer.getId());
		customer = entityManager.find(Customer.class, customer.getUsername());
		customer1 = entityManager.find(Customer.class, customer.getPassword());
				if (customer != null && customer1 != null){
			return true;
          } else 
              return false;
          }

@POST
@Path("/createOrder")
public String createOrder(Order order)
{
	entityManager.persist(order);
		return "Done";
      
      }
@GET
@Path("/viewOrderByID/{id}")
public Order viewOrderByID(@PathParam("id")int id)//used to retrieve a parameter passed in through the URI
{
	/*TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o where o.id ="+ id, Order.class);//createQuery method is used to create dynamic queries.
  	List<Order> orders = query.getResultList();

	  return orders;*/
	Order order;
	order = entityManager.find(Order.class, id);
	if (order != null){
		return order;
      } else 
          return null;
      }

@GET
@Path("/ViewAllCustomerOrders")//quary htt3dl
public List<Order> ViewAllCustomerOrders(Customer customer)
{
	customer = entityManager.find(Customer.class, customer.getId());
	if (customer != null){
		return customer.getList();
      } else 
          return null;
      }

}
	

	
	
	
	
	
	//ArrayList<Customer> customers = new ArrayList<Customer>();
	
	/*@GET
	//@Produces(MediaType.TEXT_PLAIN)//return as string
	@Produces(MediaType.APPLICATION_JSON)//return json object
	@Path("/getMsg")
	public String getMsg() {
		
		return bean.getMsg();
	}*/
	/*@GET
	//@Produces(MediaType.TEXT_PLAIN)//return as string
	@Produces(MediaType.APPLICATION_JSON)//return json object
	@Path("/getMsg/{id}")
	public String getMsg(@PathParam("id")int id) {
		
		return bean.getMsg() + "  "+id;
	}*/
//msh m7taga a7ot pathparam l2ny hbeto f el body
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addCustomer")
	public String addCustomer(Customer customer)
	{
		customers.add(customer);
		return "Customer Added Successfully. ";
	}*/
	


