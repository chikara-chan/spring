package playplane.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "school")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tschool {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private Tcity tcity;
	
	@OneToMany(mappedBy="tschool")
	private Set<Tunit> tunits = new HashSet<Tunit>(0);
	
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

	public Tcity getTcity() {
		return tcity;
	}

	public void setTcity(Tcity tcity) {
		this.tcity = tcity;
	}

	public Set<Tunit> getTunits() {
		return tunits;
	}

	public void setTunits(Set<Tunit> tunits) {
		this.tunits = tunits;
	}

}
