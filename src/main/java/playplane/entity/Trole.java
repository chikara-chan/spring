package playplane.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "role")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Trole {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(name = "role_resource", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "resource_id") })
	private Set<Tresource> tresources = new HashSet<Tresource>(0);
	
	@OneToMany(mappedBy = "trole")
	private Set<Tuser> tusers = new HashSet<Tuser>(0);

	public Set<Tuser> getTusers() {
		return tusers;
	}

	public void setTusers(Set<Tuser> tusers) {
		this.tusers = tusers;
	}

	public Set<Tresource> getTresources() {
		return tresources;
	}

	public void setTresources(Set<Tresource> tresources) {
		this.tresources = tresources;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
