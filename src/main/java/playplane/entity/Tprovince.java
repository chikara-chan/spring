package playplane.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "province")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tprovince {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "tprovince")
	private Set<Tcity> tcitys = new HashSet<Tcity>(0);

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

	public Set<Tcity> getTcitys() {
		return tcitys;
	}

	public void setTcitys(Set<Tcity> tcitys) {
		this.tcitys = tcitys;
	}

}
