package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Bean.RegistrationRentRoom;
import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;


public class GuiRegistrationRentRoomTest extends JFrame {

	private JPanel contentPane;
	private JTable table;

	Connection conn = null;
	private JTable table_1;
	private JTextField iDRegistration;
	private JTextField iDClient;
	private JTextField arrivalDate;
	private JTextField price;
	private JTextField iDRoom;
	private JTextField iDEmployee;
	List<RegistrationRentRoom> list = new ArrayList<RegistrationRentRoom>();
	private JTable table_3;
	private JTextField typeroom;
	private JTextField fullname;
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiRegistrationRentRoomTest frame = new GuiRegistrationRentRoomTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 void refreshClient() {
	    	try {
				String query ="select *from client";
				PreparedStatement ppst = conn.prepareStatement(query);
				ResultSet rs = ppst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				ppst.close();
				rs.close();
			    } catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    }
	 
	 void refreshRegistration() {

			String queryy = "select *from registrationrentroom";
			try {
				PreparedStatement ppst1 = conn.prepareStatement(queryy);
				ResultSet rs1 = ppst1.executeQuery();
				table_3.setModel(DbUtils.resultSetToTableModel(rs1));	
				ppst1.close();
				rs1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }
	 
	 void refreshRoom() {
		 String st = "Empty";
		 String query1 ="select IDRoom,room.TypeRoom,Price,Status from room join priceroom on room.TypeRoom=priceroom.TypeRoom and Status='"+st+"'";
			try {
				PreparedStatement ppst = conn.prepareStatement(query1);
				ResultSet rs = ppst.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				
				ppst.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	 }
	 
	 
	 void updateRoom() {
		 String st ="Full";
		 String query = " update room set Status ='"+st+"' where IDRoom ='"+iDRoom.getText()+"'";
		 try {
			PreparedStatement ppst = conn.prepareStatement(query);	
			ppst.execute();
			ppst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	 }
	 
	 void updateRoomDelete() {
		 String st="Empty";
		 String query ="update room set Status ='"+st+"' where IDRoom = '"+iDRoom.getText()+"'";
		 try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ppst.execute();
			ppst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	 }
	 
	 void createDate() {
		 Date date = new Date();
		 
		 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		  String time= format1.format(date);
		 arrivalDate.setText(time);
		// arrivalDate.setEnabled(false);
		 
	 }

	
	
	public GuiRegistrationRentRoomTest() {
		
		conn= DBConnection.createConnection();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 899, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 34, 609, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.LIGHT_GRAY);
		scrollPane.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		scrollPane.setBounds(0, 10, 609, 117);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				
				String id = ttable.getValueAt(row, 0).toString();
				
				iDClient.setText( id);
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		String query ="select *from client";
		try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			ppst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(636, 10, 229, 117);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("UPDATE CLIENT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Guiclienttest g = new Guiclienttest();
				g.setVisible(true);
				refreshClient();
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(35, 86, 172, 21);
		panel_1.add(btnNewButton_2);
		
		JLabel lblNewLabel_7 = new JLabel("Fullname");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(10, 29, 76, 13);
		panel_1.add(lblNewLabel_7);
		
		fullname = new JTextField();
		fullname.setBounds(96, 26, 123, 19);
		panel_1.add(fullname);
		fullname.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("SEARCH");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select *from client where Name like '%"+fullname.getText()+"%'";
				
				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					ResultSet rs = ppst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					ppst.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_6.setBounds(35, 55, 172, 21);
		panel_1.add(btnNewButton_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 159, 609, 107);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		scrollPane_1.setBorder(new LineBorder(new Color(130, 135, 144), 2));
		scrollPane_1.setBounds(0, 0, 609, 102);
		panel_2.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel ttable = (DefaultTableModel) table_1.getModel();
				int row = table_1.getSelectedRow();
				
				String id = ttable.getValueAt(row, 0).toString();
				String price1 = ttable.getValueAt(row, 2).toString();
				
				iDRoom.setText(id);
				price.setText(price1);
				
				
			}
		});
		scrollPane_1.setViewportView(table_1);
		String st ="Empty";
		
		String query1 ="select IDRoom,room.TypeRoom,Price,Status from room join priceroom on room.TypeRoom=priceroom.TypeRoom and Status='"+st+"'";
		try {
			PreparedStatement ppst = conn.prepareStatement(query1);
			ResultSet rs = ppst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
			panel_3.setBackground(Color.LIGHT_GRAY);
			panel_3.setForeground(Color.LIGHT_GRAY);
			panel_3.setBounds(0, 276, 865, 68);
			panel.add(panel_3);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("IDRegisterAuto");
			
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 10, 102, 13);
			panel_3.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("IDClient");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.setBounds(10, 45, 102, 13);
			panel_3.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("ArrivalDate");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_2.setBounds(318, 10, 93, 13);
			panel_3.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Price/Day");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_3.setBounds(318, 45, 78, 13);
			panel_3.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("IDRoom");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_4.setBounds(608, 10, 70, 13);
			panel_3.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("IDEmployee");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_5.setBounds(608, 45, 86, 13);
			panel_3.add(lblNewLabel_5);
			
			iDRegistration = new JTextField();
			iDRegistration.setBounds(122, 8, 96, 19);
			panel_3.add(iDRegistration);
			iDRegistration.setColumns(10);
			
			iDClient = new JTextField();
			iDClient.setBounds(122, 43, 96, 19);
			panel_3.add(iDClient);
			iDClient.setColumns(10);
			
			arrivalDate = new JTextField();
			arrivalDate.setBounds(440, 8, 96, 19);
			panel_3.add(arrivalDate);
			arrivalDate.setColumns(10);
			
			price = new JTextField();
			price.setBounds(440, 43, 96, 19);
			panel_3.add(price);
			price.setColumns(10);
			
			iDRoom = new JTextField();
			iDRoom.setBounds(721, 8, 96, 19);
			panel_3.add(iDRoom);
			iDRoom.setColumns(10);
			
			iDEmployee = new JTextField();
			iDEmployee.setBounds(721, 43, 96, 19);
			panel_3.add(iDEmployee);
			iDEmployee.setColumns(10);
			
		
			
			JPanel panel_5 = new JPanel();
			panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
			panel_5.setBackground(Color.LIGHT_GRAY);
			panel_5.setBounds(636, 352, 229, 124);
			panel.add(panel_5);
			panel_5.setLayout(null);
			
			JButton btnNewButton = new JButton("DELETE");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int res=JOptionPane.showConfirmDialog(null, "you are sure delete databases","confirm", JOptionPane.YES_NO_OPTION);
					if (res!= JOptionPane.YES_OPTION) {
						return ;
					} else 
					{
					        String query = "delete from registrationrentroom where IDRegistrationRent ='"+iDRegistration.getText()+"'";
						try {
							PreparedStatement ppst = conn.prepareStatement(query);
							ppst.execute();
							JOptionPane.showMessageDialog(null, "databases are deleted");
							ppst.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					
					refreshRegistration();
					updateRoomDelete();
					refreshRoom();
			     	iDRegistration.setText("");
			     	iDClient.setText("");
			     	iDRoom.setText("");
			     	iDEmployee.setText("");
			     	price.setText("");
			     	arrivalDate.setText("");
					
					}
					}			
					
					
					
				
			});
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton.setBounds(42, 52, 147, 21);
			panel_5.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("RENT ROOM");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					createDate();
					ListE.createListRestrationRentRoom(conn, list);
					int count = ListE.iDDMaxRegisterRentRoom(list);
					count++;
					String idP = Random.createStringRandom("PT", count);
					String idR = iDRoom.getText();
					String idC =iDClient.getText();
					String idE = iDEmployee.getText();
			     	String date = arrivalDate.getText();
			     	
			     	RegistrationRentRoom r = new RegistrationRentRoom();
			     	r.setArrivalDate(date);
			     	r.setiDClient(idC);
			     	r.setiDEmployee(idE);
			     	r.setiDRegistration(idP);
			     	r.setiDRoom(idR);
			     	
			     	list.add(r);
			     	
			     	String query = "insert into registrationrentroom(IDRegistrationRent,IDEmployee,IDClient,IDRoom,ArrivalDate) values (?,?,?,?,?)";
			     	
			     	try {
						PreparedStatement ppst = conn.prepareStatement(query);
						ppst.setString(1, idP);
						ppst.setString(2, idE);
						ppst.setString(3, idC);
						ppst.setString(4, idR);
						ppst.setString(5, date);
						ppst.execute();
						iDRegistration.setText(idP);
						refreshRegistration();
						JOptionPane.showMessageDialog(null, "Successful");
						updateRoom();
						refreshRoom();
						ppst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
			     	iDRegistration.setText("");
			     	iDClient.setText("");
			     	iDRoom.setText("");
			     	iDEmployee.setText("");
			     	price.setText("");
			     	arrivalDate.setText("");
					
					
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_1.setBounds(42, 10, 147, 21);
			panel_5.add(btnNewButton_1);
			
			JButton btnNewButton_3 = new JButton("EXIT");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					GUII g = new GUII();
					g.setVisible(true);
				}
			});
			btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_3.setBounds(42, 93, 147, 21);
			panel_5.add(btnNewButton_3);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(10, 352, 599, 124);
			panel.add(scrollPane_2);
			
			table_3 = new JTable();
			table_3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel ttable = (DefaultTableModel) table_3.getModel();
					int row =table_3.getSelectedRow();
					
					String idR = ttable.getValueAt(row, 0).toString();
					String iDE = ttable.getValueAt(row, 1).toString();
					String iDC =ttable.getValueAt(row, 2).toString();
					String iDR =ttable.getValueAt(row, 3).toString();
					String time =ttable.getValueAt(row, 4).toString();
					
					iDRegistration.setText(idR);
					iDClient.setText(iDC);
					iDRoom.setText(iDR);
					iDEmployee.setText(iDE);
					arrivalDate.setText(time);
					
					
					
				}
			});
			scrollPane_2.setViewportView(table_3);

			String queryy = "select *from registrationrentroom";
			try {
				PreparedStatement ppst1 = conn.prepareStatement(queryy);
				ResultSet rs1 = ppst1.executeQuery();
				table_3.setModel(DbUtils.resultSetToTableModel(rs1));
				
				JPanel panel_4 = new JPanel();
				panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
				panel_4.setBackground(Color.LIGHT_GRAY);
				panel_4.setBounds(636, 159, 229, 107);
				panel.add(panel_4);
				panel_4.setLayout(null);
				
				JButton btnNewButton_4 = new JButton("UPDATE ROOM");
				btnNewButton_4.setBounds(50, 72, 157, 25);
				panel_4.add(btnNewButton_4);
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						GuiRoomTest g = new GuiRoomTest();
						g.setVisible(true);
						
						
					}
				});
				btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				typeroom = new JTextField();
				typeroom.setBounds(85, 11, 134, 19);
				panel_4.add(typeroom);
				typeroom.setColumns(10);
				
				JLabel lblNewLabel_6 = new JLabel("TypeRoom");
				lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_6.setBounds(14, 13, 83, 13);
				panel_4.add(lblNewLabel_6);
				
				JButton btnNewButton_5 = new JButton("SEARCH");
				btnNewButton_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String st = "Empty";
						String query ="select IDRoom,room.TypeRoom,Price,Status from room join priceroom on room.TypeRoom=priceroom.TypeRoom and Status='"+st+"' where room.TypeRoom like '"+typeroom.getText()+"%'";
						
						try {
							PreparedStatement ppst = conn.prepareStatement(query);
							ResultSet rs = ppst.executeQuery();
							table_1.setModel(DbUtils.resultSetToTableModel(rs));
							ppst.close();
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					}
				});
				btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnNewButton_5.setBounds(50, 41, 157, 21);
				panel_4.add(btnNewButton_5);
				
				ppst1.close();
				rs1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			ppst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
			}
	}

