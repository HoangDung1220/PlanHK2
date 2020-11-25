package Bean;

public class DetailService {
	private String iDDetail;
	private String iDService;
	private String iDBillService;
	private int amount;
	private double sum;
	
	public DetailService(String iDDetail, String iDService, String iDBillService, int amount, double sum) {
		super();
		this.iDDetail = iDDetail;
		this.iDService = iDService;
		this.iDBillService = iDBillService;
		this.amount = amount;
		this.sum = sum;
	}

	public DetailService() {
		super();
	}

	public String getiDDetail() {
		return iDDetail;
	}

	public void setiDDetail(String iDDetail) {
		this.iDDetail = iDDetail;
	}

	public String getiDService() {
		return iDService;
	}

	public void setiDService(String iDService) {
		this.iDService = iDService;
	}

	public String getiDBillService() {
		return iDBillService;
	}

	public void setiDBillService(String iDBillService) {
		this.iDBillService = iDBillService;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "DetailService [iDDetail=" + iDDetail + ", iDService=" + iDService + ", iDBillService=" + iDBillService
				+ ", amount=" + amount + ", sum=" + sum + "]";
	}
	
	/*public void show() {
		System.out.println(this.iDDetail,);
	}*/
	
	
	
	
}
