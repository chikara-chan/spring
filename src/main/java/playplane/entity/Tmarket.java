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
@Table(name = "market")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tmarket {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "broadcast")
	private String broadcast;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private Tuser tuser ;
	
	@OneToOne
	@JoinColumn(name = "unit_id")
	private Tunit tunit;
	
	@OneToMany(mappedBy = "tmarket")
	private Set<Tproduct> tproducts = new HashSet<Tproduct>(0);
	
	@OneToMany(mappedBy = "tmarket")
	private Set<Torder> Torders = new HashSet<Torder>(0);
	
	@OneToMany(mappedBy = "tmarket")
	private Set<Treport> treports = new HashSet<Treport>(0);
	
	public Set<Tproduct> getTproducts() {
		return tproducts;
	}

	public void setTproducts(Set<Tproduct> tproducts) {
		this.tproducts = tproducts;
	}

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

	public String getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}

	public Tuser getTuser() {
		return tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	public Tunit getTunit() {
		return tunit;
	}

	public void setTunit(Tunit tunit) {
		this.tunit = tunit;
	}

	public Set<Torder> getTorders() {
		return Torders;
	}

	public void setTorders(Set<Torder> torders) {
		Torders = torders;
	}

	public Set<Treport> getTreports() {
		return treports;
	}

	public void setTreports(Set<Treport> treports) {
		this.treports = treports;
	}


	

}
