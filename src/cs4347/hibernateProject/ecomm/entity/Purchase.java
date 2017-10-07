package cs4347.hibernateProject.ecomm.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "purchase")
public class Purchase 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable=true, updatable=true)
	private Long id;
	
	@Column(name = "purchaseDate", unique = false, nullable = true, insertable=true, updatable=true)
	private Date purchaseDate;
	
	@Column(name = "purchaseAmount", unique = false, nullable = true, insertable=true, updatable=true)
	private double purchaseAmount;
	
	//@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "customer")
	private Customer customer;
	
	//@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getPurchaseDate()
	{
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate)
	{
		this.purchaseDate = purchaseDate;
	}

	public double getPurchaseAmount()
	{
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount)
	{
		this.purchaseAmount = purchaseAmount;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

}
