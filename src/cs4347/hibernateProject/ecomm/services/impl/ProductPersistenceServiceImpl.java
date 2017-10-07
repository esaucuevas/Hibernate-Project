package cs4347.hibernateProject.ecomm.services.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cs4347.hibernateProject.ecomm.entity.Customer;
import cs4347.hibernateProject.ecomm.entity.Product;
import cs4347.hibernateProject.ecomm.services.ProductPersistenceService;
import cs4347.hibernateProject.ecomm.util.DAOException;

public class ProductPersistenceServiceImpl implements ProductPersistenceService
{
	private EntityManager em;

	public ProductPersistenceServiceImpl(EntityManager em)
	{
		this.em = em;
	}
	
	@Override
	public Product create(Product product) throws SQLException, DAOException
	{
		if(product.getId()!=null) throw new DAOException("The Product needs a null id");
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			if (product.getId() != null){
				throw new DAOException("Customer id must be null");
			}
			
			Product p = new Product();
			p.setProdCategory(product.getProdCategory());
			p.setProdDescription(product.getProdDescription());
			p.setProdName(product.getProdName());
			p.setProdUPC(product.getProdUPC());
			
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
	public Product retrieve(Long id) throws SQLException, DAOException
	{
		if(id==null)
			throw new DAOException("Cannot retrieve product with a null ID");
		try{
			Product p = new Product();
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			em.getTransaction().begin();
			p = (Product)em.find(Product.class, id);
			em.getTransaction().commit();
			return p;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Product update(Product product) throws SQLException, DAOException
	{
		if(product.getId()==null)
			throw new DAOException("Cannot update product with a null ID");
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			Long id = product.getId();
			
			em.getTransaction().begin();
			
			product=em.merge(product);
			
			Product p = em.find(Product.class, id);
			p.setProdCategory(product.getProdCategory());
			p.setProdDescription(product.getProdDescription());
			p.setProdName(product.getProdName());
			p.setProdUPC(product.getProdUPC());
			
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
			throw new DAOException("Cannot retrieve product with a null ID");
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-simple_company");
			em = factory.createEntityManager();
			em.getTransaction().begin();
			Product p = em.find(Product.class,  id);
			em.remove(p);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Product retrieveByUPC(String upc) throws SQLException, DAOException
	{
		try{
			List<Product> p = em.createQuery("from Product as p where p.prodUPC = :cat").setParameter("cat", upc).getResultList();
			return p.get(0);
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Product> retrieveByCategory(int category) throws SQLException, DAOException
	{
		try{
			List<Product> p = em.createQuery("from Product as p where p.prodCategory = :cat").setParameter("cat", category).getResultList();
			return p;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

}
