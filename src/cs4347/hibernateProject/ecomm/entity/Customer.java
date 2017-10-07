package cs4347.hibernateProject.ecomm.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "customer")
public class Customer 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable=true, updatable=true)
	private Long id;
	
	@Column(name = "firstName", unique = false, nullable = true, insertable=true, updatable=true)
	private String firstName;
	
	@Column(name = "lastName", unique = false, nullable = true, insertable=true, updatable=true)
	private String lastName;
	
	@Column(name = "gender", unique = false, nullable = true, insertable=true, updatable=true)
	private Character gender;
	
	@Column(name = "dob", unique = false, nullable = true, insertable=true, updatable=true)
	private Date dob;
	
	@Column(name = "email", unique = false, nullable = true, insertable=true, updatable=true)
	private String email;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Address address;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private CreditCard creditCard;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Character getGender()
	{
		return gender;
	}

	public void setGender(Character gender)
	{
		this.gender = gender;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public CreditCard getCreditCard()
	{
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard)
	{
		this.creditCard = creditCard;
	}
}
