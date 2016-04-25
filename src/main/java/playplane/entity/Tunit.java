package playplane.entity;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "unit")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tunit {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private Tschool tschool;
	
	@OneToOne(mappedBy="tunit")
	private Tmarket tmarket;
	
	@OneToMany(mappedBy = "tunit")
	private Set<Tapply> tapplies = new HashSet<Tapply>(0);
	
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

	public Tschool getTschool() {
		return tschool;
	}

	public void setTschool(Tschool tschool) {
		this.tschool = tschool;
	}

	public Tmarket getTmarket() {
		return tmarket;
	}

	public void setTmarket(Tmarket tmarket) {
		this.tmarket = tmarket;
	}

	public Set<Tapply> getTapplies() {
		return tapplies;
	}

	public void setTapplies(Set<Tapply> tapplies) {
		this.tapplies = tapplies;
	}


	
}