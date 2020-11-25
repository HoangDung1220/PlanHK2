package Bean;

public class Service {
	private String iDService;
	private String nameService;
	private float price;
	private String unit;
	
	public Service() {
		super();
	}

	public Service(String iDService, String nameService, float price, String unit) {
		super();
		this.iDService = iDService;
		this.nameService = nameService;
		this.price = price;
		this.unit = unit;
	}

	public String getiDService() {
		return iDService;
	}

	public void setiDService(String iDService) {
		this.iDService = iDService;
	}

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	

}
