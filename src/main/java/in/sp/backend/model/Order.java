package in.sp.backend.model;

 
public class Order extends Product {
	private int oderId;
	private int uid;
	private String quantity;
	private String date ;
	
	
	
	public Order() {
		super();
	}



	public Order(int oderId, int udi, String quantity, String date) {
		super();
		this.oderId = oderId;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}



	public int getOderId() {
		return oderId;
	}



	public void setOderId(int oderId) {
		this.oderId = oderId;
	}



	public int getUdi() {
		return uid;
	}



	public void setUdi(int udi) {
		this.uid = udi;
	}



	public String getQuantity() {
		return quantity;
	}



	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "Order [oderId=" + oderId + ", udi=" + uid + ", quantity=" + quantity + ", date=" + date + "]";
	}
	
	

}
