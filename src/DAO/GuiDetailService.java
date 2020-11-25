package DAO;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Bean.DetailService;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiDetailService {

	private JFrame frame;
	private JTable table;
    Connection conn;
    private JTextField iDService;
    private JTextField price;
    private JTextField amount;
    private JTextField summary;
    List<DetailService> list = new ArrayList<DetailService>();
    private JTable table_1;
    private JTextField iDDetail;
    private JTextField iDBillService;
    private JTextField totalMoney;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiDetailService window = new GuiDetailService();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refresh() {
    	try {
			String query ="select IDBillDetailService,IDBillService,detailservice.IDService,Price,Amount,Sum from detailservice join service on detailservice.IDService=Service.IDService";
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			ppst.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	public void calculation() {
		  float pricee = Float.parseFloat(price.getText());
			int quantity = Integer.parseInt(amount.getText());
			float money = pricee*quantity;
			summary.setText(String.valueOf(money));

		
	}

	public double total(List<DetailService> list) {
		double s=0;
		for (DetailService i:list)
		{
			s=s+i.getSum();
		}
		return s;
	}
	public GuiDetailService() {
		conn = DBConnection.createConnection();
		ListE.createListDetailService(conn, list);
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		 table = new JTable();

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.LIGHT_GRAY);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				String id = ttable.getValueAt(row, 0).toString();
				String value= ttable.getValueAt(row, 2).toString();
				
				iDService.setText(id);
				price.setText(value);
				amount.setText("1");
				summary.setText("0.0");

			}
		});
		scrollPane.setBounds(10, 23, 388, 144);
		frame.getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		String query = "select *from service";
		try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(420, 23, 210, 144);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("IDService");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 10, 62, 13);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Price");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.setBounds(10, 44, 45, 13);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Amount");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_2.setBounds(10, 79, 62, 13);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Summary\r\n");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_3.setBounds(10, 102, 62, 32);
			panel.add(lblNewLabel_3);
			
			iDService = new JTextField();
			iDService.setBounds(82, 8, 96, 19);
			panel.add(iDService);
			iDService.setColumns(10);
			
			price = new JTextField();
			price.setBounds(82, 41, 96, 19);
			panel.add(price);
			price.setColumns(10);
			
			amount = new JTextField();
			amount.setBounds(82, 76, 96, 19);
			panel.add(amount);
			amount.setColumns(10);
			
			summary = new JTextField();
			summary.setBounds(82, 109, 96, 19);
			panel.add(summary);
			summary.setColumns(10);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 209, 392, 271);
			frame.getContentPane().add(scrollPane_1);
			
			table_1 = new JTable();
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel ttable = (DefaultTableModel) table_1.getModel();
					int row = table_1.getSelectedRow();
					String value = ttable.getValueAt(row, 5).toString();
					String idService = ttable.getValueAt(row, 2).toString();
					String quantity = ttable.getValueAt(row, 4).toString();
					String idDetail = ttable.getValueAt(row, 0).toString();
					String iDBill = ttable.getValueAt(row, 1).toString();
					String pricee = ttable.getValueAt(row, 3).toString();
							
     				price.setText(pricee);
					summary.setText(value);
					iDDetail.setText(idDetail);
					iDBillService.setText(iDBill);
					amount.setText(quantity);
					iDService.setText(idService);
					
				}
			});
			scrollPane_1.setViewportView(table_1);
			String query1 ="select IDBillDetailService,IDBillService,detailservice.IDService,Price,Amount,Sum from detailservice join service on detailservice.IDService=Service.IDService";
			try {
				PreparedStatement ppst1 = conn.prepareStatement(query1);
				ResultSet rs1 = ppst1.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs1));
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.LIGHT_GRAY);
				panel_1.setBounds(420, 209, 306, 271);
				frame.getContentPane().add(panel_1);
				panel_1.setLayout(null);
				
				iDDetail = new JTextField();
				iDDetail.setBounds(119, 30, 125, 19);
				panel_1.add(iDDetail);
				iDDetail.setColumns(10);
				
				JLabel lblNewLabel_4 = new JLabel("IDDetail");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_4.setBounds(26, 33, 69, 13);
				panel_1.add(lblNewLabel_4);
				
				JLabel lblNewLabel_5 = new JLabel("IDBillService ");
				lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_5.setBounds(26, 70, 87, 13);
				panel_1.add(lblNewLabel_5);
				
				iDBillService = new JTextField();
				iDBillService.setBounds(119, 68, 125, 19);
				panel_1.add(iDBillService);
				iDBillService.setColumns(10);
				
				JLabel lblNewLabel_6 = new JLabel("Total Money");
				lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_6.setBounds(26, 103, 87, 13);
				panel_1.add(lblNewLabel_6);
				
				totalMoney = new JTextField();
				totalMoney.setBounds(119, 101, 125, 19);
				panel_1.add(totalMoney);
				totalMoney.setColumns(10);
				
				JButton btnNewButton_3 = new JButton("SAVE BILL");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnNewButton_3.setBounds(26, 167, 123, 21);
				panel_1.add(btnNewButton_3);
				
				JButton btnNewButton_4 = new JButton("EXIT");
				btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnNewButton_4.setBounds(177, 167, 85, 21);
				panel_1.add(btnNewButton_4);
				
				JButton btnNewButton_2 = new JButton("CALCULATE");
				btnNewButton_2.setBackground(Color.WHITE);
				btnNewButton_2.setBounds(678, 119, 130, 21);
				frame.getContentPane().add(btnNewButton_2);
				btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JButton btnNewButton_1 = new JButton("UNCHOOSE");
				btnNewButton_1.setBackground(Color.WHITE);
				btnNewButton_1.setBounds(678, 76, 130, 21);
				frame.getContentPane().add(btnNewButton_1);
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String query ="delete from detailservice where IDBillDetailService = '"+iDDetail.getText()+"'";
						try {
							PreparedStatement ppst = conn.prepareStatement(query);
							ppst.execute();
							JOptionPane.showMessageDialog(null, "Database is deleted");
							refresh();
							List<DetailService> list1 =  new ArrayList<DetailService>();
							ListE.createListDetailService(conn, list1);
                        totalMoney.setText(String.valueOf(total(list1)));
							ppst.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						iDService.setText("");
						price.setText("");
						amount.setText("");
						summary.setText("");
						
						
						
					}
				});
				btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JButton btnNewButton = new JButton("CHOOSE");
				btnNewButton.setBackground(Color.WHITE);
				btnNewButton.setBounds(678, 35, 130, 21);
				frame.getContentPane().add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						String query = "insert into detailservice(IDBillDetailService,IDBillService,IDService,Amount,Sum) values (?,?,?,?,?)";
						int count = ListE.iDDMaxDetailService(list);
						count++;
						String idDetail = String.valueOf(Random.createStringRandom("DS", count));
						String idBill ="HDDV001";
						String idService = iDService.getText();
						int amountt = Integer.parseInt(amount.getText());
						double summ = Double.parseDouble(summary.getText());
						
						DetailService e = new DetailService();
						e.setAmount(amountt);
						e.setiDBillService(idBill);
						e.setiDDetail(idDetail);
						e.setiDService(idService);
						e.setSum(summ);
						
						list.add(e);
						try {
							PreparedStatement ppst = conn.prepareStatement(query);
							
							ppst.setString(1, idDetail);
							ppst.setString(2, idBill);
							ppst.setString(3, idService);
							ppst.setString(4, String.valueOf(amountt));
							ppst.setString(5, String.valueOf(summ));
							
							ppst.execute();
							JOptionPane.showMessageDialog(null, "Successful");
							refresh();
							List<DetailService> list1 =  new ArrayList<DetailService>();
							ListE.createListDetailService(conn, list1);
                        totalMoney.setText(String.valueOf(total(list1)));
							ppst.close();
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
                    
						
						iDService.setText("");
						price.setText("");
						amount.setText("");
						summary.setText("");
						
						
					}
				});
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					    calculation();
						String query = "update detailservice set IDBillDetailService ='"+iDDetail.getText()+"',IDBillService='"+iDBillService.getText()+"',AMount='"+amount.getText()+"',Sum ='"+summary.getText()+"' where IDBillDetailService ='"+iDDetail.getText()+"'";
					
						try {

							PreparedStatement ppst = conn.prepareStatement(query);

							ppst.execute();

							JOptionPane.showMessageDialog(null, "Successful");
							List<DetailService> list1 =  new ArrayList<DetailService>();
							ListE.createListDetailService(conn, list1);
                        totalMoney.setText(String.valueOf(total(list1)));
							ppst.close();
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					refresh();

					iDService.setText("");
					price.setText("");
					amount.setText("");
					summary.setText("");
					
					}
				});
				ppst1.close();
				rs.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ppst.close();
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
	}
}
