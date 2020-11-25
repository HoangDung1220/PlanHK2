package Bean;

public class Employee {
	private String iDe;
	private String fullname;
	private String sex;
	private String address;
	private String email;
	private String phonenumber;
	private String iCard;
	private String iDRole;
	
	public Employee(String iDe, String fullname, String sex, String address, String email, String phonenumber,
			String iCard,String role) {
		this.iDe = iDe;
		this.fullname = fullname;
		this.sex = sex;
		this.address = address;
		this.email = email;
		this.phonenumber = phonenumber;
		this.iCard = iCard;
		this.iDRole=role;
	}

	public String getRole() {
		return iDRole;
	}

	public void setRole(String role) {
		this.iDRole = role;
	}

	public Employee() {
		super();
	}

	public String getiDe() {
		return iDe;
	}

	public void setiDe(String iDe) {
		this.iDe = iDe;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getiCard() {
		return iCard;
	}

	public void setiCard(String iCard) {
		this.iCard = iCard;
	}

	public void show() {
		System.out.println("ID= "+iDe+" Fullname= "+fullname+" Sex= "+sex+" Phonenumber=  "+phonenumber+" ICard = "+iCard+" Email= "+email+
				" Address= "+address+" Role= "+iDRole+" ");
	}
	
	
	
	
	

}
