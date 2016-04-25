package playplane.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "delivery")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tdelivery {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Tcustomer tcustomer;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Tcustomer getTcustomer() {
		return tcustomer;
	}

	public void setTcustomer(Tcustomer tcustomer) {
		this.tcustomer = tcustomer;
	}

	
	
}
