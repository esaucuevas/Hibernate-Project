package cs4347.hibernateProject.ecomm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "product")
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable=true, updatable=true)
	private Long id;
	
	@Column(name = "prodName", unique = false, nullable = true, insertable=true, updatable=true)
	private String prodName;
	
	@Column(name = "prodDescription", unique = false, nullable = true, insertable=true, updatable=true)
	private String prodDescription;
	
	@Column(name = "prodCategory", unique = false, nullable = true, insertable=true, updatable=true)
	private int prodCategory;
	
	@Column(name = "prodUPC", unique = false, nullable = true, insertable=true, updatable=true)
	private String prodUPC;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getProdName()
	{
		return prodName;
	}

	public void setProdName(String prodName)
	{
		this.prodName = prodName;
	}

	public String getProdDescription()
	{
		return prodDescription;
	}

	public void setProdDescription(String prodDescription)
	{
		this.prodDescription = prodDescription;
	}

	public int getProdCategory()
	{
		return prodCategory;
	}

	public void setProdCategory(int prodCategory)
	{
		this.prodCategory = prodCategory;
	}

	public String getProdUPC()
	{
		return prodUPC;
	}

	public void setProdUPC(String prodUPC)
	{
		this.prodUPC = prodUPC;
	}

}
