package cs4347.hibernateProject.ecomm.services.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.*;

import cs4347.hibernateProject.ecomm.entity.Address;
import cs4347.hibernateProject.ecomm.entity.CreditCard;
import cs4347.hibernateProject.ecomm.entity.Customer;
import cs4347.hibernateProject.ecomm.entity.Product;
import cs4347.hibernateProject.ecomm.services.CustomerPersistenceService;
import cs4347.hibernateProject.ecomm.util.DAOException;

public class CustomerPersistenceServiceImpl implements CustomerPersistenceService
{
	private EntityManager em;

	public CustomerPersistenceServiceImpl(EntityManager em)
	{
		this.em = em;
	}
	
	/**
	 */
	@Override
	public Customer create(Customer customer) throws SQLException, DAOException
	{
		if (customer.getId() != null){
			throw new DAOException("Customer id must be null");
		}
		try{
			em=null;
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
					
			Customer c = new Customer();
			c.setAddress(customer.getAddress());
			c.setCreditCard(customer.getCreditCard());
			c.setDob(customer.getDob());
			c.setEmail(customer.getEmail());
			c.setFirstName(customer.getFirstName());
			c.setGender(customer.getGender());
			c.setLastName(customer.getLastName());
			
			em.getTransaction().begin();		
			em.persist(c);
			em.getTransaction().commit();

			return c;
		}
		catch(Exception ex){
			throw ex;
		}
		
	}

	@Override
	public Customer retrieve(Long id) throws SQLException, DAOException
	{
		if(id==null)
			throw new DAOException("Cannot retrieve a customer without an ID.");
		try{
			Customer customer = new Customer();
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			em.getTransaction().begin();
			customer = (Customer)em.find(Customer.class, id);
			em.getTransaction().commit();
			return customer;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Customer update(Customer c1) throws SQLException, DAOException
	{
		if(c1.getId()==null)
			throw new DAOException("Cannot update a customer without an ID.");
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			Long id = c1.getId();
			
			em.getTransaction().begin();
			
			c1=em.merge(c1);
			
			Customer cust = em.find(Customer.class, id);
			cust.setAddress(c1.getAddress());
			cust.setCreditCard(c1.getCreditCard());
			cust.setDob(c1.getDob());
			cust.setEmail(c1.getEmail());
			cust.setFirstName(c1.getFirstName());
			cust.setGender(c1.getGender());
			cust.setLastName(c1.getLastName());
			
			em.getTransaction().commit();
			
			return cust;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public void delete(Long id) throws SQLException, DAOException
	{
		if(id==null)
			throw new DAOException("Cannot delete a customer without an ID.");
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			em.getTransaction().begin();
			Customer c = em.find(Customer.class,  id);
			em.remove(c);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Customer> retrieveByZipCode(String zipCode) throws SQLException, DAOException
	{
		try{
			List<Customer> customers = em.createQuery("from Customer as c where c.address.zipcode = :zip").setParameter("zip", zipCode).getResultList();
			return customers;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Customer> retrieveByDOB(Date startDate, Date endDate) throws SQLException, DAOException
	{
		try{
		List<Customer> customers = em.createQuery("select c from Customer c where c.dob between :startDate and :endDate")
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate).getResultList();
		return customers;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
}
