package playplane.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "customer")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tcustomer {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private Tuser tuser ;
	
	@OneToMany(mappedBy="tcustomer")
	private Set<Tdelivery> tdeliveries = new HashSet<Tdelivery>(0);
	
	@OneToMany(mappedBy="tcustomer")
	private Set<Treport> treports = new HashSet<Treport>(0);

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Tuser getTuser() {
		return tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	public Set<Tdelivery> getTdeliveries() {
		return tdeliveries;
	}

	public void setTdeliveries(Set<Tdelivery> tdeliveries) {
		this.tdeliveries = tdeliveries;
	}

	public Set<Treport> getTreports() {
		return treports;
	}

	public void setTreports(Set<Treport> treports) {
		this.treports = treports;
	}




}
