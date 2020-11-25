package Bean;

public class BillService {
	private String iDBillService;
	private String iDClient;
	private String dateCreated;
	private double summary;
	
	public BillService() {
		super();
	}

	public String getiDBillService() {
		return iDBillService;
	}

	public void setiDBillService(String iDBillService) {
		this.iDBillService = iDBillService;
	}

	public String getiDClient() {
		return iDClient;
	}

	public void setiDClient(String iDClient) {
		this.iDClient = iDClient;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public double getSummary() {
		return summary;
	}

	public void setSummary(double summary) {
		this.summary = summary;
	}

	public BillService(String iDBillService, String iDClient, String dateCreated, double summary) {
		super();
		this.iDBillService = iDBillService;
		this.iDClient = iDClient;
		this.dateCreated = dateCreated;
		this.summary = summary;
	}

	
	
	

}
