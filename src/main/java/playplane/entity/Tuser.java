package playplane.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tuser {

	@Id
	@Column(name = "id")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_time")
	private Date lastTime;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Trole trole ;
	
	@OneToMany(mappedBy = "tuser")
	private Set<Torder> torders= new HashSet<Torder>(0); ;
	
	@OneToOne(mappedBy = "tuser")
	private Tmarket tmarket ;
	
	@OneToOne(mappedBy = "tuser")
	private Tcustomer tcustomer ;

	public Tmarket getTmarket() {
		return tmarket;
	}

	public void setTmarket(Tmarket tmarket) {
		this.tmarket = tmarket;
	}

	public Tcustomer getTcustomer() {
		return tcustomer;
	}

	public void setTcustomer(Tcustomer tcustomer) {
		this.tcustomer = tcustomer;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Trole getTrole() {
		return trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	public Set<Torder> getTorders() {
		return torders;
	}

	public void setTorders(Set<Torder> torders) {
		this.torders = torders;
	}


}
