package playplane.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tproduct {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "pic")
	private String pic;
	
	@Column(name = "number")
	private int number;
	
	@ManyToOne
	@JoinColumn(name = "market_id")
	private Tmarket tmarket;
	
	@OneToMany(mappedBy = "torder")
	private Set<TorderBranch> torders = new HashSet<TorderBranch>(0);

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Tmarket getTmarket() {
		return tmarket;
	}

	public void setTmarket(Tmarket tmarket) {
		this.tmarket = tmarket;
	}


	public Set<TorderBranch> getTorders() {
		return torders;
	}

	public void setTorders(Set<TorderBranch> torders) {
		this.torders = torders;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}