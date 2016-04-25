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
@Table(name = "report")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Treport {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "type")
	private String type;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "pic")
	private String pic;
	
	@Column(name = "status")
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Tcustomer tcustomer;
	
	@ManyToOne
	@JoinColumn(name = "market_id")
	private Tmarket tmarket;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Tcustomer getTcustomer() {
		return tcustomer;
	}

	public void setTcustomer(Tcustomer tcustomer) {
		this.tcustomer = tcustomer;
	}

	public Tmarket getTmarket() {
		return tmarket;
	}

	public void setTmarket(Tmarket tmarket) {
		this.tmarket = tmarket;
	}
	

}