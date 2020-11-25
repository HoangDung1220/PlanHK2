package Bean;

public class Room  {
	private String iDRoom;
	private String status;
	private String typeRoom;
	
	
	
	
	public Room() {
		super();
	}


	public Room(String iDRoom, String status, String typeRoom) {
		super();
		this.iDRoom = iDRoom;
		this.status = status;
		this.typeRoom = typeRoom;
	}


	public String getiDRoom() {
		return iDRoom;
	}


	public void setiDRoom(String iDRoom) {
		this.iDRoom = iDRoom;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTypeRoom() {
		return typeRoom;
	}


	public void setTypeRoom(String typeRoom) {
		this.typeRoom = typeRoom;
	}
	
	
	
	

}
