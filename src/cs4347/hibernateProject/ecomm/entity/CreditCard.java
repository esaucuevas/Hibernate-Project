package cs4347.hibernateProject.ecomm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "creditcard")
public class CreditCard 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable=true, updatable=true)
	private Long id;
	
	@Column(name = "name", unique = false, nullable = true, insertable=true, updatable=true)
	private String name;
	
	@Column(name = "ccNumber", unique = false, nullable = true, insertable=true, updatable=true)
	private String ccNumber;
	
	@Column(name = "expDate", unique = false, nullable = true, insertable=true, updatable=true)
	private String expDate;
	
	@Column(name = "securityCode", unique = false, nullable = true, insertable=true, updatable=true)
	private String securityCode;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCcNumber()
	{
		return ccNumber;
	}

	public void setCcNumber(String ccNumber)
	{
		this.ccNumber = ccNumber;
	}

	public String getExpDate()
	{
		return expDate;
	}

	public void setExpDate(String expDate)
	{
		this.expDate = expDate;
	}

	public String getSecurityCode()
	{
		return securityCode;
	}

	public void setSecurityCode(String securityCode)
	{
		this.securityCode = securityCode;
	}

}
