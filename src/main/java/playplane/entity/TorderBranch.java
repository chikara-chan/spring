package playplane.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "order_branch")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TorderBranch {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "added")
	private int added;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Torder torder;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Tproduct tproduct;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAdded() {
		return added;
	}

	public void setAdded(int added) {
		this.added = added;
	}

	public Torder getTorder() {
		return torder;
	}

	public void setTorder(Torder torder) {
		this.torder = torder;
	}

	public Tproduct getTproduct() {
		return tproduct;
	}

	public void setTproduct(Tproduct tproduct) {
		this.tproduct = tproduct;
	}
	
	

}
