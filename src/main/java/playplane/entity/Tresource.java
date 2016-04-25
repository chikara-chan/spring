package playplane.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "resource")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tresource {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "url")
	private String url;
	
	@ManyToMany
	@JoinTable(name = "role_resource", joinColumns = { @JoinColumn(name = "resource_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Trole> troles = new HashSet<Trole>(0);

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Trole> getTroles() {
		return troles;
	}

	public void setTroles(Set<Trole> troles) {
		this.troles = troles;
	}

}
