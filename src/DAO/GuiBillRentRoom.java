package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Bean.BillRoom;
import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiBillRentRoom extends JFrame {

	private JPanel contentPane;
	private JTextField IDR;
	private JTable table;
	Connection conn=null;
	List<BillRoom> list = new ArrayList<BillRoom>();
	private JTextField iDBillRoom;
	private JTextField iDRegistration;
	private JTextField iDClient;
	private JTextField iDRoom;
	private JTextField price;
	private JTextField totalDay;
	private JTextField checkOutDay;
	private JTextField iDEmployee;
	private JTextField totalMoney;
	static String valuee;
	static long a;
	private JTable table_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBillRentRoom frame = new GuiBillRentRoom();
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
		  checkOutDay.setText(time);
		 
	 }
	

	 void calculationDate() throws ParseException {
			DefaultTableModel ttable = (DefaultTableModel) table.getModel();
			int row = table.getSelectedRow();
			String arrival = ttable.getValueAt(row,4 ).toString();
			String departure = ttable.getValueAt(row, 5).toString();
			
			
		
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(arrival);
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(departure);
			a = date2.getTime()-date1.getTime();
			a=a/(1000*60*24*60);
			
			if (a==0) {
				totalDay.setText(String.valueOf(1));

			} else
		totalDay.setText(String.valueOf(a));
			
		
			
	 }
	 
	 void createIDAuto() {
		 int count =ListE.iDDMaxBillRoom(list);
			count++;
			String id = Random.createStringRandom("HDP", count);
		//	iDRegistration.setText(id);
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

	 void createCheckOutDate() {
		 Date date = new Date();
		 
		 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		  String time= format1.format(date);
		 checkOutDay.setText(time);
		 checkOutDay.setEnabled(false);
		 
	 }
	 
	 void calculationMoney()
	 {
	    	int day = Integer.parseInt(totalDay.getText());
	    	float value = Float.parseFloat(price.getText());	
	    	double kq = day*value;
	    	totalMoney.setText(String.valueOf(kq));

	    }
	 void refreshBill() {
		 String querry1 ="select *from billroom";
			
			try {
				PreparedStatement ppst1 = conn.prepareStatement(querry1);
				ResultSet rs1 = ppst1.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs1));
				ppst1.close();
				rs1.close();


			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }
	

	public GuiBillRentRoom() {
		conn=DBConnection.createConnection();
		ListE.createListBillRoom(conn, list);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 713, 41);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IDRegistration Room");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(33, 10, 152, 13);
		panel.add(lblNewLabel);
		
		IDR = new JTextField();
		IDR.setBounds(195, 8, 163, 19);
		panel.add(IDR);
		IDR.setColumns(10);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			String query ="select *from registrationrentroom where IDRegistrationRent like '%"+IDR.getText()+"%'";
			
			
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(439, 7, 106, 21);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 723, 106);
		contentPane.add(scrollPane);
		
		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createCheckOutDate();
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				String idRegistration = ttable.getValueAt(row,0 ).toString();
				String idC = ttable.getValueAt(row, 2).toString();
				String idRoom = ttable.getValueAt(row,3 ).toString();
				String idE = ttable.getValueAt(row, 1).toString();
				
				iDEmployee.setText(idE);
				iDRoom.setText(idRoom);
				iDClient.setText(idC);
				iDRegistration.setText(idRegistration);
					
				refreshPrice();
				price.setText(valuee);
				try {
					calculationDate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				calculationMoney();
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		String query ="select *from registrationrentroom";
		
		PreparedStatement ppst;
		try {
			ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(9, 192, 719, 115);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("IDBillRoom");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.setBounds(10, 19, 88, 13);
			panel_1.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("IDRegistration");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_2.setBounds(10, 48, 98, 14);
			panel_1.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("IDClient");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_3.setBounds(10, 79, 82, 13);
			panel_1.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("IDRoom");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_4.setBounds(253, 16, 72, 13);
			panel_1.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Price");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_5.setBounds(256, 50, 59, 13);
			panel_1.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Total Day");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_6.setBounds(256, 81, 65, 13);
			panel_1.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("Check-out Day");
			lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_7.setBounds(481, 17, 105, 13);
			panel_1.add(lblNewLabel_7);
			
			JLabel lblNewLabel_8 = new JLabel("IDEmployee");
			lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_8.setBounds(480, 50, 89, 13);
			panel_1.add(lblNewLabel_8);
			
			JLabel lblNewLabel_9 = new JLabel("Total Money");
			lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_9.setBounds(481, 83, 101, 13);
			panel_1.add(lblNewLabel_9);
			
			iDBillRoom = new JTextField();
			iDBillRoom.setBounds(110, 19, 96, 19);
			panel_1.add(iDBillRoom);
			iDBillRoom.setColumns(10);
			
			iDRegistration = new JTextField();
			iDRegistration.setBounds(111, 49, 96, 19);
			panel_1.add(iDRegistration);
			iDRegistration.setColumns(10);
			
			iDClient = new JTextField();
			iDClient.setBounds(110, 77, 96, 19);
			panel_1.add(iDClient);
			iDClient.setColumns(10);
			
			iDRoom = new JTextField();
			iDRoom.setBounds(344, 14, 96, 19);
			panel_1.add(iDRoom);
			iDRoom.setColumns(10);
			
			price = new JTextField();
			price.setBounds(343, 47, 96, 19);
			panel_1.add(price);
			price.setColumns(10);
			
			totalDay = new JTextField();
			totalDay.setBounds(343, 82, 96, 19);
			panel_1.add(totalDay);
			totalDay.setColumns(10);
			
			checkOutDay = new JTextField();
			checkOutDay.setBounds(612, 14, 96, 19);
			panel_1.add(checkOutDay);
			checkOutDay.setColumns(10);
			
			iDEmployee = new JTextField();
			iDEmployee.setBounds(611, 44, 96, 19);
			panel_1.add(iDEmployee);
			iDEmployee.setColumns(10);
			
			totalMoney = new JTextField();
			totalMoney.setBounds(613, 78, 96, 19);
			panel_1.add(totalMoney);
			totalMoney.setColumns(10);
			
			JButton btnNewButton_1 = new JButton("PAY");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String st ="Complete";
					String query ="update billroom set Status ='"+st+"' where IDBillRoom ='"+iDBillRoom.getText()+"'";
					
					try {
						PreparedStatement ppst = conn.prepareStatement(query);
						ppst.execute();
						ppst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					refreshBill();

					iDBillRoom.setText("");

					
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnNewButton_1.setBounds(304, 317, 123, 21);
			contentPane.add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("SAVE");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					ListE.createListBillRoom(conn, list);
					int count =ListE.iDDMaxBillRoom(list);
					count++;
					String id = Random.createStringRandom("HDP", count);
					String idRegistration = iDRegistration.getText();
					String payDate = checkOutDay.getText();
					String value = totalMoney.getText();
					
					BillRoom b = new BillRoom();
					b.setCheckOutDate(payDate);
					b.setiDBillRoom(id);
					b.setiDRegistration(idRegistration);
					b.setPriceRoom(Double.parseDouble(value));
					b.setStatus("Incomplete");
					
					list.add(b);
					
					
					String query ="insert into billroom (IDBillRoom,IDRegistration,PayDate,PriceRoom,Status) values (?,?,?,?,?)";
					try {
						PreparedStatement ppst = conn.prepareStatement(query);
						ppst.setString(1, id);
						ppst.setString(2, idRegistration);
						ppst.setString(3, payDate);
						ppst.setString(4, value);
						ppst.setString(5, "Incomplete");
						iDBillRoom.setText(id);

						ppst.execute();
						JOptionPane.showMessageDialog(null, "Create Bill Successful");

						ppst.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
					refreshBill();
					iDBillRoom.setText(" ");
					iDRegistration.setText("");
					iDClient.setText("");
					iDRoom.setText("");
					iDEmployee.setText("");
					checkOutDay.setText("");
					totalDay.setText("");
					price.setText("");
					totalMoney.setText("");
					
					
				}
				
				
			});
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnNewButton_2.setBounds(86, 317, 131, 21);
			contentPane.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("EXIT");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					dispose();
					GUII g = new GUII();
					g.setVisible(true);
					
				}
			});
			btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnNewButton_3.setBounds(510, 317, 123, 21);
			contentPane.add(btnNewButton_3);
			table_1 = new JTable();

			
			JScrollPane scrollPane_1 = new JScrollPane();
			//scrollPane_1.addMouseListener(new MouseAdapter()
			table_1.addMouseListener(new MouseAdapter() {

			
				@Override
				public void mouseClicked(MouseEvent e) {
					
					DefaultTableModel ttable = (DefaultTableModel) table_1.getModel();
					int row = table_1.getSelectedRow();
					
					String id = ttable.getValueAt(row, 0).toString();
					iDBillRoom.setText(id);
					
			
				}
			});
			scrollPane_1.setBounds(10, 373, 713, 137);
			contentPane.add(scrollPane_1);
			
			scrollPane_1.setViewportView(table_1);
			
			String querry1 ="select *from billroom";
			
			try {
				PreparedStatement ppst1 = conn.prepareStatement(querry1);
				ResultSet rs1 = ppst1.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs1));
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
