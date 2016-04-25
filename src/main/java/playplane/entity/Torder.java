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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "ordered")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Torder {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "total")
	private double total;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "method")
	private String method;
	
	@Column(name = "expect")
	private String expect;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "review")
	private String review;
	
	@Column(name = "reply_review")
	private String replyReview;
	
	@Column(name = "orderRead")
	private boolean orderRead;
	
	@Column(name = "reviewRead")
	private boolean reviewRead;
	
	@ManyToOne
	@JoinColumn(name = "market_id")
	private Tmarket tmarket;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Tuser tuser;
	
	@OneToMany(mappedBy = "torder")
	private Set<TorderBranch> torderBranchs = new HashSet<TorderBranch>(0);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public String getReplyReview() {
		return replyReview;
	}

	public void setReplyReview(String replyReview) {
		this.replyReview = replyReview;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Tmarket getTmarket() {
		return tmarket;
	}

	public void setTmarket(Tmarket tmarket) {
		this.tmarket = tmarket;
	}


	public Tuser getTuser() {
		return tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}


	public boolean isOrderRead() {
		return orderRead;
	}

	public void setOrderRead(boolean orderRead) {
		this.orderRead = orderRead;
	}

	public boolean isReviewRead() {
		return reviewRead;
	}

	public void setReviewRead(boolean reviewRead) {
		this.reviewRead = reviewRead;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<TorderBranch> getTorderBranchs() {
		return torderBranchs;
	}

	public void setTorderBranchs(Set<TorderBranch> torderBranchs) {
		this.torderBranchs = torderBranchs;
	}


	
	
}