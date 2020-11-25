package Bean;

public class Client {
	private String iDClient;
	private String nameClient;
	private String phonenumber;
	private String gender;
	private String address;
	private String iCard ;
	private String email;
	
	public Client() {
		
	}

	public Client(String iDClient, String nameClient, String phonenumber, String gender, String address, String iCard,
			String email) {
		
		this.iDClient = iDClient;
		this.nameClient = nameClient;
		this.phonenumber = phonenumber;
		this.gender = gender;
		this.address = address;
		this.iCard = iCard;
		this.email = email;
	}

	public String getiDClient() {
		return iDClient;
	}

	public void setiDClient(String iDClient) {
		this.iDClient = iDClient;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getiCard() {
		return iCard;
	}

	public void setiCard(String iCard) {
		this.iCard = iCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
