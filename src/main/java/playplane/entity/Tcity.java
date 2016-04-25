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
@Table(name = "city")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tcity {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "province_id")
	private Tprovince tprovince;

	@OneToMany(mappedBy = "tcity")
	private Set<Tschool> tschools = new HashSet<Tschool>(0);

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

	public Tprovince getTprovince() {
		return tprovince;
	}

	public void setTprovince(Tprovince tprovince) {
		this.tprovince = tprovince;
	}

	public Set<Tschool> getTschools() {
		return tschools;
	}

	public void setTschools(Set<Tschool> tschools) {
		this.tschools = tschools;
	}

}
