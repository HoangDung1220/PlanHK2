package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiCheckOut extends JFrame {
	
	
    Connection conn =null;
	private JPanel contentPane;
	private JTextField IDR;
	private JTextField iDRegistration;
	private JTextField iDClient;
	private JTextField iDEmployee;
	private JTextField iDRoom;
	private JTextField arrivalDate;
	private JTextField departureDate;
	private JTextField totalDay;
	private JTextField price;
	private JTextField totalPrice;
	private JTable table;
	static String valuee;
    	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiCheckOut frame = new GuiCheckOut();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 
	 void createDate() {
		 Date date = new Date();
		 
		 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		  String time= format1.format(date);
		 departureDate.setText(time);
		 departureDate.setEnabled(false);
		 
	 }
	 
	 void refresh() {

			String query ="select *from registrationrentroom where DepartureDate is null";
			
			try {
				PreparedStatement ppst = conn.prepareStatement(query);
				ResultSet rs = ppst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				ppst.close();
				rs.close();
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		 
	 
		 
	 }

	 void calculationDate() throws ParseException {
		 String arrival = arrivalDate.getText();
		 String departure = departureDate.getText();
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(arrival);
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(departure);
			
			long a = date2.getTime()-date1.getTime();
			a=a/(1000*60*24*60);
			if (a==0) {
				totalDay.setText(String.valueOf(1));

			} else
		totalDay.setText(String.valueOf(a));
			

		// Date d1 = 
		 
	 }
	
	 void refreshPrice() {
		 String query2 = "select *from priceroom,room where room.IDRoom='"+iDRoom.getText()+"' and room.TypeRoom=priceroom.TypeRoom";
			try {
				PreparedStatement ppst = conn.prepareStatement(query2);
				ResultSet rs = ppst.executeQuery();
				while (rs.next()) {
					valuee = rs.getString("Price");
					price.setText(rs.getString("Price"));
				}
				
				ppst.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }
	 
	public GuiCheckOut() {
		setFont(new Font("Tahoma", Font.BOLD, 17));
		setTitle("CHECK-OUT REGISTRATION");
		
		conn = DBConnection.createConnection();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(64, 26, 616, 58);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IDRegistration");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(40, 24, 96, 13);
		panel.add(lblNewLabel);
		
		IDR = new JTextField();
		IDR.setBounds(174, 22, 165, 19);
		panel.add(IDR);
		IDR.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String query ="select *from registrationrentroom where IDRegistrationRent like '%"+IDR.getText()+"%' and DepartureDate is null";
				
				
				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					ResultSet rs = ppst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					ppst.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(504, 21, 85, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("SEARCH");
		lblNewLabel_1.setBounds(64, 3, 66, 13);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(95, 108, 565, 148);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("IDRegistration");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 26, 104, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("IDClient");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 68, 85, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("IDEmployee\r\n");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 114, 85, 13);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("IDRoom");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(338, 26, 111, 13);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("ArrivalDate");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(338, 68, 111, 13);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("DepartureDate");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(338, 114, 111, 13);
		panel_1.add(lblNewLabel_7);
		
		iDRegistration = new JTextField();
		iDRegistration.setBounds(125, 24, 96, 19);
		panel_1.add(iDRegistration);
		iDRegistration.setColumns(10);
		
		iDClient = new JTextField();
		iDClient.setBounds(125, 65, 96, 19);
		panel_1.add(iDClient);
		iDClient.setColumns(10);
		
		iDEmployee = new JTextField();
		iDEmployee.setBounds(125, 111, 96, 19);
		panel_1.add(iDEmployee);
		iDEmployee.setColumns(10);
		
		iDRoom = new JTextField();
		iDRoom.setBounds(459, 23, 96, 19);
		panel_1.add(iDRoom);
		iDRoom.setColumns(10);
		
		arrivalDate = new JTextField();
		arrivalDate.setBounds(459, 65, 96, 19);
		panel_1.add(arrivalDate);
		arrivalDate.setColumns(10);
		
		departureDate = new JTextField();
		departureDate.setBounds(459, 111, 96, 19);
		panel_1.add(departureDate);
		departureDate.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 283, 120, 201);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Total days");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_8.setBounds(10, 10, 96, 13);
		panel_2.add(lblNewLabel_8);
		
		totalDay = new JTextField();
		totalDay.setBounds(10, 33, 96, 19);
		panel_2.add(totalDay);
		totalDay.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Price");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9.setBounds(10, 73, 45, 13);
		panel_2.add(lblNewLabel_9);
		
		price = new JTextField();
		price.setBounds(10, 103, 96, 19);
		panel_2.add(price);
		price.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Total Price");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_10.setBounds(10, 149, 96, 13);
		panel_2.add(lblNewLabel_10);
		
		totalPrice = new JTextField();
		totalPrice.setBounds(10, 172, 96, 19);
		panel_2.add(totalPrice);
		totalPrice.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(168, 283, 527, 141);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String time1="";
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row =table.getSelectedRow();
				

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
				
				refreshPrice();
				price.setText(valuee);

				
			}
		});
		scrollPane.setViewportView(table);
		
		String query ="select *from registrationrentroom where DepartureDate is null";
		
		try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(168, 446, 548, 41);
			contentPane.add(panel_3);
			panel_3.setLayout(null);
			
			JButton btnNewButton_1 = new JButton("CHECK-OUT");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					createDate();
					String query = "update registrationrentroom set DepartureDate = '"+departureDate.getText()+"' where IDRegistrationRent='"+iDRegistration.getText()+"' ";
					try {
						PreparedStatement ppst = conn.prepareStatement(query);
						ppst.execute();
						JOptionPane.showMessageDialog(null, "Check-out successful. Please calculation money ");
						ppst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					refresh();
					
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_1.setBounds(10, 10, 127, 21);
			panel_3.add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("CALCULATION");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				try {
						calculationDate();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				
						String date1 = totalDay.getText();
						int date = Integer.parseInt(date1);
				    if (date==0) {
				    	JOptionPane.showMessageDialog(null, "You still stay over 1 day, We will calculate 1 day");
				    	totalPrice.setText(price.getText());
				    }
				    else {
				    	int day = Integer.parseInt(totalDay.getText());
				    	float value = Float.parseFloat(price.getText());
				    	
				    	double kq = day*value;
				    	
				    	totalPrice.setText(String.valueOf(kq));
				    }
			
				}
			});
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_2.setBounds(147, 10, 138, 21);
			panel_3.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("CREATE BILL");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String st = "Empty";
					String query = "update room set Status ='"+st+"' where IDRoom = '"+iDRoom.getText()+"'";
					
					try {
						PreparedStatement ppst = conn.prepareStatement(query);
						ppst.execute();
						ppst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

					dispose();
					GuiBillRentRoom g = new GuiBillRentRoom();
					g.setVisible(true);
				}
			});
			btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_3.setBounds(295, 12, 145, 21);
			panel_3.add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("EXIT ");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					GUII g = new GUII();
					g.setVisible(true);
				}
			});
			btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_4.setBounds(450, 10, 85, 21);
			panel_3.add(btnNewButton_4);
			ppst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
