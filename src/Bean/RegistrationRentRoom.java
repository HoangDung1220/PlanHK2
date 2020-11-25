package Bean;

public class RegistrationRentRoom {

	private String iDRegistration;
	private String iDClient;
	private String iDEmployee;
	private String iDRoom;
	private String arrivalDate;
	private String departureDate;
	
	public RegistrationRentRoom() {
		super();
	}

	public RegistrationRentRoom(String iDRegistration, String iDClient, String iDEmployee, String iDRoom,
			String arrivalDate, String departureDate) {
		super();
		this.iDRegistration = iDRegistration;
		this.iDClient = iDClient;
		this.iDEmployee = iDEmployee;
		this.iDRoom = iDRoom;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	public String getiDRegistration() {
		return iDRegistration;
	}

	public void setiDRegistration(String iDRegistration) {
		this.iDRegistration = iDRegistration;
	}

	public String getiDClient() {
		return iDClient;
	}

	public void setiDClient(String iDClient) {
		this.iDClient = iDClient;
	}

	public String getiDEmployee() {
		return iDEmployee;
	}

	public void setiDEmployee(String iDEmployee) {
		this.iDEmployee = iDEmployee;
	}

	public String getiDRoom() {
		return iDRoom;
	}

	public void setiDRoom(String iDRoom) {
		this.iDRoom = iDRoom;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	
	
	
	
	
}
