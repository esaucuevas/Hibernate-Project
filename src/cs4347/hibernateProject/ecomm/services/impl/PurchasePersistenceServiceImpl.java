package cs4347.hibernateProject.ecomm.services.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cs4347.hibernateProject.ecomm.entity.Customer;
import cs4347.hibernateProject.ecomm.entity.Product;
import cs4347.hibernateProject.ecomm.entity.Purchase;
import cs4347.hibernateProject.ecomm.services.PurchasePersistenceService;
import cs4347.hibernateProject.ecomm.services.PurchaseSummary;
import cs4347.hibernateProject.ecomm.util.DAOException;

public class PurchasePersistenceServiceImpl implements PurchasePersistenceService
{
	private EntityManager em;

	public PurchasePersistenceServiceImpl(EntityManager em)
	{
		this.em = em;
	}
	
	@Override
	public Purchase create(Purchase purchase) throws SQLException, DAOException
	{
		if(purchase.getId()!=null)
			throw new DAOException("The purchase needs a null ID");
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			
			purchase.getCustomer().getId();
			Customer c = (Customer)em.find(Customer.class, purchase.getCustomer().getId());
			Product pr = (Product)em.find(Product.class, purchase.getProduct().getId());
			
			Purchase p = new Purchase();
			p.setCustomer(c);
			p.setProduct(pr);
			p.setPurchaseAmount(purchase.getPurchaseAmount());
			p.setPurchaseDate(purchase.getPurchaseDate());
			
			em.getTransaction().begin();		
			em.persist(p);
			em.getTransaction().commit();
			return p;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Purchase retrieve(Long id) throws SQLException, DAOException
	{
		if(id==null)
			throw new DAOException("Cannot retrieve purchase with a null ID");
		try{
			Purchase p = new Purchase();
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			em.getTransaction().begin();
			p = (Purchase)em.find(Purchase.class, id);
			em.getTransaction().commit();
			return p;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Purchase update(Purchase purchase) throws SQLException, DAOException
	{
		if(purchase.getId()==null)
			throw new DAOException("Cannot update purchase with a null ID");
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			Long id = purchase.getId();
			
			em.detach(purchase);
			em.getTransaction().begin();
			
			purchase=em.merge(purchase);
			
			Purchase p = em.find(Purchase.class, id);
			p.setCustomer(purchase.getCustomer());
			p.setProduct(purchase.getProduct());
			p.setPurchaseAmount(purchase.getPurchaseAmount());
			p.setPurchaseDate(purchase.getPurchaseDate());
			
			em.getTransaction().commit();
			
			return p;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public void delete(Long id) throws SQLException, DAOException
	{
		if(id==null)
			throw new DAOException("Cannot delete purchase with a null ID");
		
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			em.getTransaction().begin();
			Purchase p = em.find(Purchase.class, id);
			em.remove(p);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Purchase> retrieveForCustomerID(Long customerID) throws SQLException, DAOException
	{
		try{
			List<Purchase> p = em.createQuery("from Purchase as p where p.customer.id = :cat").setParameter("cat", customerID).getResultList();
			return p;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public PurchaseSummary retrievePurchaseSummary(Long customerID) throws SQLException, DAOException
	{
		try{
			List<Purchase> list = em.createQuery("from Purchase as p where p.customer.id = :cat").setParameter("cat", customerID).getResultList();
			
			PurchaseSummary summary = new PurchaseSummary();
			float min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0, count = 0;
			
			for(Purchase p : list)
			{
				if(p.getPurchaseAmount() < min)
					min = (float)p.getPurchaseAmount();
				if(p.getPurchaseAmount() > max)
					max = (float)p.getPurchaseAmount();
				sum = (float)(sum + p.getPurchaseAmount());
				count++;	
			}
			summary.maxPurchase = max;
			summary.minPurchase = min;
			float avg = sum / count;
			summary.avgPurchase = avg;
			return summary;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Purchase> retrieveForProductID(Long productID) throws SQLException, DAOException
	{
		try{
			List<Purchase> p = em.createQuery("from Purchase as p where p.id = :cat").setParameter("cat", productID).getResultList();
			return p;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
}
