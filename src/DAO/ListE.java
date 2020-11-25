package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JList;

import Bean.BillRoom;
import Bean.BillService;
import Bean.Client;
import Bean.DetailService;
import Bean.Employee;
import Bean.PriceRoom;
import Bean.RegistrationRentRoom;
import Bean.Room;
import Bean.Service;

public class ListE {
	public static void createList(Connection conn, List<Employee> list) {
		String query = "select *from employee";
		
		try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				
				String id1 = rs.getString("IDEmployee");
				String fullname1 = rs.getString("Fullname");
				String icard1 = rs.getString("ICard");
				String sex1 = rs.getString("Sex");
				String phonenumber1 = rs.getString("Phonenumber");
				String address1 = rs.getString("Address");
				String email1 = rs.getString("Email");
				String idrole1 = rs.getString("Role");
				
				e.setAddress(address1);
				e.setEmail(email1);
				e.setFullname(fullname1);
				e.setiCard(icard1);
				e.setiDe(id1);
				e.setPhonenumber(phonenumber1);
				e.setRole(idrole1);
				e.setSex(sex1);
				
				list.add(e);
				
			}
			ppst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void createListPriceRoom(Connection conn, List<PriceRoom> list) {
        String query = "select *from priceroom";
		
		try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			while (rs.next()) {
				PriceRoom r = new PriceRoom();
				String t1 = rs.getString("TypeRoom");
				String t2 = rs.getString("Price");
				
				
				r.setTypeRoom(t1);
				r.setPrice(Float.parseFloat(t2));
				
				list.add(r);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

	public static void dell(List<PriceRoom> list) {
		for (PriceRoom i:list) {
		  {
				list.remove(i);
			}
	}
	}
	
	
		public static void createListClient(Connection conn, List<Client> list) {
			
			String query = "select *from client";
			
			try {
				PreparedStatement ppst = conn.prepareStatement(query);
				ResultSet rs = ppst.executeQuery();
				while (rs.next()) {
					Client e = new Client();
					String id1 = rs.getString("IDClient");
					String fullname1 = rs.getString("Name");
					String icard1 = rs.getString("ICard");
					String gender = rs.getString("Gender");
					String phonenumber1 = rs.getString("Phonenumber");
					String address1 = rs.getString("Address");
					String email1 = rs.getString("Email");
					
					e.setAddress(address1);
					e.setEmail(email1);
					e.setNameClient(fullname1);
					e.setiCard(icard1);
					e.setiDClient(id1);
					e.setGender(gender);
					e.setPhonenumber(phonenumber1);
					
					
					list.add(e);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		 public static int iDMax(List<Client> list) {
				int max=0;
				int num=max;
					for (Client i:list) {
						String st =i.getiDClient();
						String st1[] = st.split("H");
					    num = Integer.parseInt(st1[1]);
						if (num>max) max = num;
				}
					return num;
				}	    


		
		public static void delClient(List<Client> list,String a) {
			for (Client i:list) {
				if (i.getiDClient().equalsIgnoreCase(a)) {
					list.remove(i);
				}
		}
		}
		    
		public static void createListBillService(Connection conn, List<BillService> list) {
			String query = "select *from billservice";
			
			try {
				PreparedStatement ppst = conn.prepareStatement(query);
				ResultSet rs = ppst.executeQuery();
				while (rs.next()) {
					BillService e = new BillService();
					String id1 = rs.getString("IDBillService");
					String id2 = rs.getString("IDClient");
					String date = rs.getString("DateCreated");
					double sum = Double.parseDouble(rs.getString("Summary"));
					
					e.setiDBillService(id1);
					e.setiDClient(id2);
					e.setDateCreated(date);
					e.setSummary(sum);
					
					list.add(e);
					
				}
				ppst.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
			 public static int iDDMax(List<BillService> list) {
					int max=0;
					int num=max;
						for (BillService i:list) {
							String st =i.getiDBillService();
							String st1[] = st.split("V");
						    num = Integer.parseInt(st1[1]);
							if (num>max) max = num;
					}
						return num;
					}	 
			 
			 public static void createListDetailService(Connection conn, List<DetailService> list) {
					String query = "select *from detailservice";
					
					try {
						PreparedStatement ppst = conn.prepareStatement(query);
						ResultSet rs = ppst.executeQuery();
						while (rs.next()) {
							DetailService e = new DetailService();
							String id1 = rs.getString("IDBillDetailService");
							String id2 = rs.getString("IDBillService");
							String id3 = rs.getString("IDService");
							int amount = Integer.valueOf(rs.getString("Amount"));
							double sum = Double.parseDouble(rs.getString("Sum"));
							
							e.setiDDetail(id1);
							e.setiDBillService(id2);
							e.setiDService(id3);
							e.setAmount(amount);
							e.setSum(sum);
							
							list.add(e);
							
						}
						ppst.close();
						rs.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
					 public static int iDDMaxDetailService(List<DetailService> list) {
							int max=0;
							int num=max;
							//DS001
								for (DetailService i:list) {
									String st =i.getiDDetail();
									String st1[] = st.split("S");
								    num = Integer.parseInt(st1[1]);
									if (num>max) max = num;
							}
								return num;
							}
					 
					 public static void createListRestrationRentRoom(Connection conn, List<RegistrationRentRoom> list) {
						 
						 String query = "select *from registrationrentroom";
							
							try {
								PreparedStatement ppst = conn.prepareStatement(query);
								ResultSet rs = ppst.executeQuery();
								while (rs.next()) {

									RegistrationRentRoom e = new RegistrationRentRoom();
									String id1 = rs.getString("IDRegistrationRent");
									String id2 = rs.getString("IDEmployee");
									String id3 = rs.getString("IDClient");
									String id4 = rs.getString("IDRoom");
									String date = rs.getString("ArrivalDate");
									
									e.setArrivalDate(date);
									e.setiDClient(id3);
									e.setiDEmployee(id2);
									e.setiDRegistration(id1);
									e.setiDRoom(id4);
									
									list.add(e);
									
									
								
									
								}
								ppst.close();
								rs.close();
								
							} catch (SQLException e) {
								e.printStackTrace();
							} 
					 }
					 
					 
					 public static int iDDMaxRegisterRentRoom(List<RegistrationRentRoom> list) {
							int max=0;
							int num=max;
							//DS001
								for (RegistrationRentRoom i:list) {
									String st =i.getiDRegistration();
									String st1[] = st.split("T");
								    num = Integer.parseInt(st1[1]);
									if (num>max) max = num;
							}
								return num;
							}
					
					 public static void createListRoom(Connection conn, List<Room> list) {
					        String query = "select *from room";
							
							try {
								PreparedStatement ppst = conn.prepareStatement(query);
								ResultSet rs = ppst.executeQuery();
								while (rs.next()) {
									Room r = new Room();
									String t1 = rs.getString("IDRoom");
									String t2 = rs.getString("TypeRoom");
									String t3 = rs.getString("Status");
									
									
									r.setiDRoom(t1);
									r.setStatus(t3);
									r.setTypeRoom(t2);
									
									list.add(r);				
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
							
						}
					 
					 public static int iDDMaxRoom(List<Room> list) {
							int max=0;
							int num=max;
							//DS001
								for (Room i:list) {
									String st =i.getiDRoom();
									String st1[] = st.split("R");
								    num = Integer.parseInt(st1[1]);
									if (num>max) max = num;
							}
								return num;
							}
			 
					 public static void createListBillRoom(Connection conn, List<BillRoom> list) {
					        String query = "select *from billroom";
							
							try {
								PreparedStatement ppst = conn.prepareStatement(query);
								ResultSet rs = ppst.executeQuery();
								while (rs.next()) {
									BillRoom r = new BillRoom();
									String t1 = rs.getString("IDBillRoom");
									String t2 = rs.getString("IDRegistration");
									String t3 = rs.getString("PayDate");
									String t4 = rs.getString("PriceRoom");
									String t5 = rs.getString("Status");
									
									
									r.setCheckOutDate(t3);
									r.setiDBillRoom(t1);
									r.setiDRegistration(t2);
									r.setPriceRoom(Double.parseDouble(t4));
									r.setStatus(t5);
									
									list.add(r);				
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
							
						}
					 
					 public static int iDDMaxBillRoom(List<BillRoom> list) {
							int max=0;
							int num=max;
							//DS001
								for (BillRoom i:list) {
									String st =i.getiDBillRoom();
									String st1[] = st.split("P");
								    num = Integer.parseInt(st1[1]);
									if (num>max) max = num;
							}
								return num;
							}
					 
					 public static int iDMaxEmployee(List<Employee> list) {
							int max=0;
							int num=max;
								for (Employee i:list) {
									String st =i.getiDe();
									String st1[] = st.split("V");
								    num = Integer.parseInt(st1[1]);
									if (num>max) max = num;
							}
								return num;
							}	
					 
						public static void createService(Connection conn, List<Service> list) {
							String query = "select *from service";
							
							try {
								PreparedStatement ppst = conn.prepareStatement(query);
								ResultSet rs = ppst.executeQuery();
								while (rs.next()) {
									Service e = new Service();
									
									String id1 = rs.getString("IDService");
									String id2 = rs.getString("NameService");
									String t3 = rs.getString("Price");
									String t4 = rs.getString("Unit");
									
								    e.setiDService(id1);
								    e.setNameService(id2);
								    e.setPrice(Float.parseFloat(t3));
								    e.setUnit(t4);
									
									list.add(e);
									
								}
								ppst.close();
								
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
							 public static int iDDMaxService(List<Service> list) {
									int max=0;
									int num=max;
										for (Service i:list) {
											String st =i.getiDService();
											String st1[] = st.split("V");
										    num = Integer.parseInt(st1[1]);
											if (num>max) max = num;
									}
										return num;
									}	 
							 
			 
			 
			
	}
