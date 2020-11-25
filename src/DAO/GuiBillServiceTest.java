package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import Bean.BillService;
import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiBillServiceTest extends JFrame {

	private JPanel contentPane;

	private JTable table;
	Connection conn=null;
	private JTextField iDBill;
	private JTextField iDClient;
	private JTable table_1;
	List<BillService> list = new ArrayList<BillService>();
	private JTextField dateCreated;
	public static String luuiD;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBillServiceTest frame = new GuiBillServiceTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	  void refresh() {
	    	try {
				String query ="select *from billservice";
				PreparedStatement ppst = conn.prepareStatement(query);
				ResultSet rs = ppst.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				ppst.close();
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	  
	  void createDate() {
			 Date date = new Date(); 
			 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			  String time= format1.format(date);
			 dateCreated.setText(time);
			 dateCreated.setEnabled(false);	 
		 }

	
	public GuiBillServiceTest() {
		conn = DBConnection.createConnection();
		ListE.createListBillService(conn, list);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 494, 168);
	    contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				String id = ttable.getValueAt(row,0 ).toString();
				iDClient.setText(id);
			}
		});
		scrollPane.setViewportView(table);
		
		try {
			String query ="select *from client";
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JPanel panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(530, 10, 176, 168);
			contentPane.add(panel);
			
			JLabel lblNewLabel = new JLabel("IDBillAuto");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 207, 74, 13);
			contentPane.add(lblNewLabel);
			
			iDBill = new JTextField();
			iDBill.setBounds(81, 205, 96, 19);
			contentPane.add(iDBill);
			iDBill.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("IDClient");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.setBounds(221, 208, 63, 13);
			contentPane.add(lblNewLabel_1);
			
			iDClient = new JTextField();
			iDClient.setBounds(294, 208, 96, 19);
			contentPane.add(iDClient);
			iDClient.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setBounds(438, 211, 45, 13);
			contentPane.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Created Date");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_3.setBounds(423, 208, 92, 13);
			contentPane.add(lblNewLabel_3);
			
			JButton btnNewButton = new JButton("CREATE BILL");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					createDate();
					String query = "insert into billservice(IDBillService,IDClient,DateCreated,Summary) values(?,?,?,?)";
					
					String idClient1 = iDClient.getText();
					String date1 = dateCreated.getText();
					int count = ListE.iDDMax(list);
					count= count+1;
					String idBill = String.valueOf(Random.createStringRandom("HDDV", count));
					BillService b = new BillService();
					b.setDateCreated(date1);
					b.setiDBillService(idBill);
					b.setiDClient(idClient1);
					b.setSummary(0.0);
					list.add(b);
					
					try {
						PreparedStatement ppst = conn.prepareStatement(query);
						
						ppst.setString(1, idBill);
						ppst.setString(2, idClient1);
						ppst.setString(3, date1);
						ppst.setString(4, "0.0");
						
						iDBill.setText(idBill);
						ppst.execute();
						dateCreated.setText(date1);
						JOptionPane.showMessageDialog(null, "Insert Data Successful");
						refresh();
						ppst.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					iDClient.setText("");
					dateCreated.setText("");
					iDBill.setText("");
					
					
				}
			});
			
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton.setBounds(81, 271, 142, 21);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("DETAIL");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					dispose();
				    luuiD = iDBill.getText(); 
					GuiDetailServiceTest g = new GuiDetailServiceTest();
					g.setVisible(true);
					
					
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_1.setBounds(307, 271, 103, 21);
			contentPane.add(btnNewButton_1);
			
			JScrollPane scrollPane_1 = new JScrollPane();

			
			table_1 = new JTable();
			scrollPane_1.setViewportView(table_1);
			String query1 ="select *from billservice";
			
			PreparedStatement ppst1 = conn.prepareStatement(query1);
			ResultSet rs1 = ppst1.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs1));
			ppst1.close();
			rs1.close();
			
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				
					DefaultTableModel ttable = (DefaultTableModel) table_1.getModel();
					int row = table_1.getSelectedRow();
					String id = ttable.getValueAt(row,0 ).toString();
					String idC = ttable.getValueAt(row, 1).toString();
					String date = ttable.getValueAt(row, 2).toString();
					
					iDClient.setText(idC);
					iDBill.setText(id);
					dateCreated.setText(date);
					
					
				}
			});
			scrollPane_1.setBounds(10, 307, 696, 196);
			contentPane.add(scrollPane_1);
			
			dateCreated = new JTextField();
			dateCreated.setBounds(537, 205, 96, 19);
			contentPane.add(dateCreated);
			dateCreated.setColumns(10);
			
			JButton btnNewButton_2 = new JButton("EXIT");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					GUII g = new GUII();
					g.setVisible(true);
				}
			});
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_2.setBounds(520, 273, 96, 21);
			contentPane.add(btnNewButton_2);
			
			
			
			ppst.close();
			rs.close();	
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
