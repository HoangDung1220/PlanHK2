package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Bean.PriceRoom;
import Bean.Room;
import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;


public class GuiRoomTestEnglish extends JFrame {

	private JPanel contentPane;
	private JTextField normal1;
	private JTextField normal2;
	private JTextField vip1;
	private JTextField vip2;
    List<PriceRoom> listPriceRoom1 = new ArrayList<PriceRoom>();
    
    Connection conn = null;
	private JTextField iDr;
	private JTextField status;
	private JTextField price;
	private JTable table;
	
	public void refresh() {
    	try {
			String query = "SELECT IDRoom,room.TypeRoom,priceroom.Price,Status from employee.room join employee.priceroom on room.TypeRoom=priceroom.TypeRoom";
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			ppst.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiRoomTestEnglish frame = new GuiRoomTestEnglish();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GuiRoomTestEnglish() {
		conn = DBConnection.createConnection();
		ListE.createListPriceRoom(conn, listPriceRoom1);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 22, 733, 180);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IDRoom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(32, 52, 78, 13);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Status");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(32, 106, 45, 13);
		panel_1.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_2 = new JLabel("TypeRoom");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(340, 52, 70, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(340, 106, 45, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("MANAGEMENT ROOM");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(324, 10, 173, 13);
		panel_1.add(lblNewLabel_4);
		
		iDr = new JTextField();
		iDr.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		iDr.setBounds(115, 50, 96, 19);
		panel_1.add(iDr);
		iDr.setColumns(10);
		
		status = new JTextField();
		status.setBounds(115, 104, 96, 19);
		panel_1.add(status);
		status.setColumns(10);
		status.setText("Empty");
		
		price = new JTextField();
		price.setBounds(473, 104, 127, 19);
		panel_1.add(price);
		price.setColumns(10);
		
		final JComboBox typeRoom = new JComboBox();
		typeRoom.setModel(new DefaultComboBoxModel(new String[] {"Single Room", "Double Room", "Twin Room", "Triple Room"}));
		typeRoom.setBounds(473, 49, 127, 21);
		panel_1.add(typeRoom);
		
		JButton btnNewButton_2 = new JButton("SAVE");
		btnNewButton_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "insert into room(IDRoom,TypeRoom,Status) values (?,?,?) ";
			    List<Room> list= new ArrayList<Room>();
				ListE.createListRoom(conn, list);
				int count = ListE.iDDMaxRoom(list);
				count++;
				
				Room e = new Room();
				String idr = Random.createStringRandom("R", count);
				String type =typeRoom.getSelectedItem().toString();
				String sta = status.getText();
				
				e.setiDRoom(idr);
				e.setStatus(sta);
				e.setTypeRoom(type);
				
				list.add(e);
				
				
				
				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					ppst.setString(1, idr);
					ppst.setString(2, type);
					ppst.setString(3, sta);
					ppst.execute();
					iDr.setText(idr);
					JOptionPane.showMessageDialog(null, "Insert data successfully");
					ppst.close();
										
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				refresh();
				iDr.setText("");
				price.setText("");
				
			}	
				
			
		});
		btnNewButton_2.setBounds(54, 149, 85, 21);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("DELETE");
		btnNewButton_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int res=JOptionPane.showConfirmDialog(null, "you are sure delete databases","confirm", JOptionPane.YES_NO_OPTION);
				if (res!= JOptionPane.YES_OPTION) {
					return ;
				} else 
				{
				        String query = "delete from room where IDRoom ='"+iDr.getText()+"'";
					try {
						PreparedStatement ppst = conn.prepareStatement(query);
						ppst.execute();
						JOptionPane.showMessageDialog(null, "databases are deleted");
						ppst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
				refresh();
				iDr.setText("");
				price.setText("");
				}
				}			
		});
		btnNewButton_3.setBounds(222, 149, 96, 21);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("UPDATE");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "update room set IDRoom ='"+iDr.getText()+"',Status ='"+status.getText()+"',TypeRoom='"+typeRoom.getSelectedItem().toString()+"' where IDRoom ='"+iDr.getText()+"'";
				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					ppst.execute();
					String id = iDr.getText();
					String type = typeRoom.getSelectedItem().toString();
					String sta = status.getText();
					
					iDr.setText(id);
					typeRoom.setSelectedItem(type);
					
					status.setText(sta);
					for (PriceRoom i:listPriceRoom1) {
						if (i.getTypeRoom().equalsIgnoreCase(type))
							price.setText(String.valueOf(i.getPrice()));
					}
					JOptionPane.showMessageDialog(null, "Updates data successful");
					
					ppst.close();
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				refresh();
				iDr.setText("");
				price.setText("");
				
			}
		});
		
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_4.setBounds(412, 149, 96, 21);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("EXIT ");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			/*	GuiRegistrationRentRoomTest g = new GuiRegistrationRentRoomTest();
				g.setVisible(true);*/
			
				dispose();
				GUII g = new GUII();
				g.setVisible(true);
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_5.setBounds(573, 149, 85, 21);
		panel_1.add(btnNewButton_5);
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				String id = table.getValueAt(row, 0).toString();
				String type = table.getValueAt(row, 1).toString();
				String prices = table.getValueAt(row, 2).toString();
				String sta = table.getValueAt(row, 3).toString();
				
				iDr.setText(id);
				typeRoom.setSelectedItem(type);
				price.setText(prices);
				status.setText(sta);
				
			}
		});
		scrollPane.setBounds(20, 219, 647, 195);
		panel_1.add(scrollPane);
		
		
		scrollPane.setViewportView(table);

		String query = "SELECT IDRoom,room.TypeRoom,priceroom.Price,Status from employee.room join employee.priceroom on room.TypeRoom=priceroom.TypeRoom";
		try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
					JPanel panel = new JPanel();
					panel_1.add(panel);
					panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panel.setBackground(Color.LIGHT_GRAY);
					panel.setBounds(716, 64, 290, 350);
					panel.setLayout(null);
					
					JLabel name1 = new JLabel("Single Room");
					name1.setFont(new Font("Tahoma", Font.BOLD, 13));
					name1.setBounds(22, 67, 100, 13);
					panel.add(name1);
					
					JLabel name2 = new JLabel("Double Room");
					name2.setFont(new Font("Tahoma", Font.BOLD, 13));
					name2.setBounds(22, 121, 88, 13);
					panel.add(name2);
					
					JLabel name3 = new JLabel("Twin Room");
					name3.setFont(new Font("Tahoma", Font.BOLD, 13));
					name3.setBounds(22, 175, 88, 13);
					panel.add(name3);
					
					JLabel name4 = new JLabel("Triple Room");
					name4.setFont(new Font("Tahoma", Font.BOLD, 13));
					name4.setBounds(20, 226, 88, 16);
					panel.add(name4);
					
					
					normal1 = new JTextField();
					normal1.setBounds(132, 65, 96, 19);
					panel.add(normal1);
					normal1.setColumns(10);
					
					normal2 = new JTextField();
					normal2.setBounds(132, 119, 96, 19);
					panel.add(normal2);
					normal2.setColumns(10);
					
					vip1 = new JTextField();
					vip1.setBounds(132, 173, 96, 19);
					panel.add(vip1);
					vip1.setColumns(10);
					
					vip2 = new JTextField();
					vip2.setBounds(132, 226, 96, 19);
					panel.add(vip2);
					vip2.setColumns(10);
					
					
					
					JButton btnNewButton = new JButton("Load");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String st1= "Single Room";

						String query = "select Price from priceroom where TypeRoom='"+st1+"'";
						try {
							PreparedStatement ppst = conn.prepareStatement(query);
							ResultSet rs = ppst.executeQuery();
						   
						while (rs.next()) {
						  String value = rs.getString(1).toString();
						  normal1.setText(value);
						  
						  }
						  ppst.close();
						  rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						String st2= "Double Room";

						String query2 = "select Price from priceroom where TypeRoom='"+st2+"'";
						try {
							PreparedStatement ppst = conn.prepareStatement(query2);
							ResultSet rs = ppst.executeQuery();
						   
						while (rs.next()) {
						  String value = rs.getString(1).toString();
						  normal2.setText(value);
						  
						  }
						  ppst.close();
						  rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						String st3= "Twin Room";

						String query3 = "select Price from priceroom where TypeRoom='"+st3+"'";
						try {
							PreparedStatement ppst = conn.prepareStatement(query3);
							ResultSet rs = ppst.executeQuery();
						   
						while (rs.next()) {
						  String value = rs.getString(1).toString();
						  vip1.setText(value);
						  
						  }
						  ppst.close();
						  rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						String st4= "Triple Room";

						String query4 = "select Price from priceroom where TypeRoom='"+st4+"'";
						try {
							PreparedStatement ppst = conn.prepareStatement(query4);
							ResultSet rs = ppst.executeQuery();
						   
						while (rs.next()) {
						  String value = rs.getString(1).toString();
						  vip2.setText(value);
						  
						  }
						  ppst.close();
						  rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						}
					});
					btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
					btnNewButton.setBounds(60, 307, 88, 21);
					panel.add(btnNewButton);
					
					
			/*		JButton btnNewButton = new JButton("Load");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							
							String a1 = String.valueOf(listPriceRoom1.get(1).getPrice());
							String a2 = String.valueOf(listPriceRoom1.get(0).getPrice());

							String a3 = String.valueOf(listPriceRoom1.get(3).getPrice());
							String a4 = String.valueOf(listPriceRoom1.get(2).getPrice());
							
							normal1.setText(a1);
							normal2.setText(a2);
							vip1.setText(a3);
							vip2.setText(a4);
							
						}
					});
					btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
					btnNewButton.setBounds(60, 307, 88, 21);
					panel.add(btnNewButton);*/
					
					JButton btnNewButton_1 = new JButton("Update");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						
						  List<PriceRoom> listPriceRoom = new ArrayList<PriceRoom>();
							
						    PriceRoom r1 = new PriceRoom();
						    PriceRoom r2 = new PriceRoom();
						    PriceRoom r3 = new PriceRoom();
						    PriceRoom r4 = new PriceRoom();
						    
						    String m1 = normal1.getText();
						    String m2 = normal2.getText();
						    String m3 = vip1.getText();
						    String m4 = vip2.getText();
						    
							r1.setTypeRoom("Single Room");
							r1.setPrice(Float.parseFloat(m1));	
							
							r2.setTypeRoom("Double Room");
							r2.setPrice(Float.parseFloat(m2));
							
							r3.setTypeRoom("Twin Room");
							r3.setPrice(Float.parseFloat(m3));
							
							r4.setTypeRoom("Triple Room");
							r4.setPrice(Float.parseFloat(m4));
							
							listPriceRoom.add(r2);
							listPriceRoom.add(r1);
							listPriceRoom.add(r4);
							listPriceRoom.add(r3);		
							
						
							for (PriceRoom l :listPriceRoom)
							
							try {
								String query ="update priceroom set Price ='"+l.getPrice()+"' where TypeRoom ='"+l.getTypeRoom()+"'";
								PreparedStatement ppst = conn.prepareStatement(query);
								
						
								ppst.execute();
								ppst.close();

								
							} catch (SQLException e) {
								e.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Updated Successful");

							refresh();
							
						}
					});
					
					btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					btnNewButton_1.setBounds(174, 307, 85, 21);
					panel.add(btnNewButton_1);
					
				/*	JButton btnNewButton_1 = new JButton("Update");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						
						for (PriceRoom i :listPriceRoom1) {
								String query1 = "delete from priceroom";
								
								try {
									PreparedStatement ppst1 = conn.prepareStatement(query1);
									ppst1.execute();
									ppst1.close();
									} catch (SQLException e) {
									JOptionPane.showMessageDialog(null, "Failed");
									e.printStackTrace();
								}
							}
						
						
						listPriceRoom1.removeAll(listPriceRoom1);
						
						  List<PriceRoom> listPriceRoom = new ArrayList<PriceRoom>();

							
							String query ="insert into priceRoom(TypeRoom,Price) values (?,?)";
						    PriceRoom r1 = new PriceRoom();
						    PriceRoom r2 = new PriceRoom();
						    PriceRoom r3 = new PriceRoom();
						    PriceRoom r4 = new PriceRoom();
						    
						    String m1 = normal1.getText();
						    String m2 = normal2.getText();
						    String m3 = vip1.getText();
						    String m4 = vip2.getText();
						    
							r1.setTypeRoom("Single Room");
							r1.setPrice(Float.parseFloat(m1));	
							
							r2.setTypeRoom("Double Room");
							r2.setPrice(Float.parseFloat(m2));
							
							r3.setTypeRoom("Twin Room");
							r3.setPrice(Float.parseFloat(m3));
							
							r4.setTypeRoom("Triple Room");
							r4.setPrice(Float.parseFloat(m4));
							
							listPriceRoom.add(r2);
							listPriceRoom.add(r1);
							listPriceRoom.add(r4);
							listPriceRoom.add(r3);		
							
							listPriceRoom1.add(r2);
							listPriceRoom1.add(r1);
							listPriceRoom1.add(r4);
							listPriceRoom1.add(r3);		
							
							
							try {
								PreparedStatement ppst = conn.prepareStatement(query);
								
								for (PriceRoom i:listPriceRoom) {
									ppst.setString(1, i.getTypeRoom());
									ppst.setFloat(2, i.getPrice());
									ppst.execute();
								}
								
								JOptionPane.showMessageDialog(null, "Updated Successful");
								ppst.close();
								
							} catch (SQLException e) {
								e.printStackTrace();
							}
							
							
						}
					});
					
					btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					btnNewButton_1.setBounds(174, 307, 85, 21);
					panel.add(btnNewButton_1);*/
					
					JLabel lblNewLabel_5 = new JLabel("Unit: Day");
					lblNewLabel_5.setBorder(new LineBorder(new Color(0, 0, 0), 0));
					lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
					lblNewLabel_5.setBounds(159, 265, 121, 19);
					panel.add(lblNewLabel_5);
					
					JLabel lblNewLabel_6 = new JLabel("ROOM RATE");
					lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblNewLabel_6.setBounds(93, 25, 135, 13);
					panel.add(lblNewLabel_6);
			ppst.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
