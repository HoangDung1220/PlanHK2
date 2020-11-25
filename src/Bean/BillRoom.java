package Bean;

public class BillRoom {

	private String iDBillRoom;
	private String iDRegistration;
    private String checkOutDate;
    private double priceRoom;
    private String status;
	
    public BillRoom() {
		super();
	}

	public BillRoom(String iDBillRoom, String iDRegistration, String checkOutDate, double priceRoom, String status) {
		super();
		this.iDBillRoom = iDBillRoom;
		this.iDRegistration = iDRegistration;
		this.checkOutDate = checkOutDate;
		this.priceRoom = priceRoom;
		this.status = status;
	}

	public String getiDBillRoom() {
		return iDBillRoom;
	}

	public void setiDBillRoom(String iDBillRoom) {
		this.iDBillRoom = iDBillRoom;
	}

	public String getiDRegistration() {
		return iDRegistration;
	}

	public void setiDRegistration(String iDRegistration) {
		this.iDRegistration = iDRegistration;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public double getPriceRoom() {
		return priceRoom;
	}

	public void setPriceRoom(double priceRoom) {
		this.priceRoom = priceRoom;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
