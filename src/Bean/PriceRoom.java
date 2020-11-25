package Bean;

public class PriceRoom {
	private String typeRoom;
	private float price;
	
	
	
	public PriceRoom() {
		
	}



	public PriceRoom(String typeRoom, float price) {
		this.typeRoom = typeRoom;
		this.price = price;
	}



	public String getTypeRoom() {
		return typeRoom;
	}



	public void setTypeRoom(String typeRoom) {
		this.typeRoom = typeRoom;
	}



	public float getPrice() {
		return price;
	}



	public void setPrice(float t2) {
		this.price = t2;
	}
	
	
	

}
