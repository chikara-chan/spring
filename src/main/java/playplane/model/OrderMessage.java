package playplane.model;

public class OrderMessage {
	private int orderUnread;
	private int reviewUnread;
	public int getOrderUnread() {
		return orderUnread;
	}
	public void setOrderUnread(int orderUnread) {
		this.orderUnread = orderUnread;
	}
	public int getReviewUnread() {
		return reviewUnread;
	}
	public void setReviewUnread(int reviewUnread) {
		this.reviewUnread = reviewUnread;
	}

}
