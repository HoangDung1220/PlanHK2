package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiDetailServiceManagement extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField iDDetail;
	private JTextField iDService;
	private JTextField iDBill;
	private JTextField priceService;
	private JTextField quantity;
	private JTextField amount;

	Connection conn;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiDetailServiceManagement frame = new GuiDetailServiceManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GuiDetailServiceManagement() {
		conn = DBConnection.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		table = new JTable();

		
		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				
				String idDetail = ttable.getValueAt(row, 0).toString();
				String idBill = ttable.getValueAt(row, 1).toString();
				String idService = ttable.getValueAt(row, 2).toString();
				String sum  = ttable.getValueAt(row, 5).toString();
				String quati = ttable.getValueAt(row, 4).toString();
				String price = ttable.getValueAt(row, 3).toString();

				iDDetail.setText(idDetail);
				iDBill.setText(idBill);
				iDService.setText(idService);
				quantity.setText(quati);
				priceService.setText(price);
				amount.setText(sum);
				
				
				
			}
		});
		scrollPane.setBounds(10, 31, 711, 216);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
String query ="select IDBillDetailService,IDBillService,detailservice.IDService,Price,Amount,Sum from detailservice join service on detailservice.IDService=Service.IDService and detailService.IDBillService = '"+GuiInvoiceServiceManagement.id+"'";
		
		try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			ppst.close();
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), null));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 323, 711, 142);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IDDetail");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(29, 23, 70, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IDService");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(29, 76, 70, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IDBill");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(252, 24, 45, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PriceService");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(252, 77, 82, 13);
		panel.add(lblNewLabel_3);
		
		iDDetail = new JTextField();
		iDDetail.setBounds(105, 21, 96, 19);
		panel.add(iDDetail);
		iDDetail.setColumns(10);
		
		iDService = new JTextField();
		iDService.setBounds(109, 74, 96, 19);
		panel.add(iDService);
		iDService.setColumns(10);
		
		iDBill = new JTextField();
		iDBill.setBounds(344, 21, 96, 19);
		panel.add(iDBill);
		iDBill.setColumns(10);
		
		priceService = new JTextField();
		priceService.setBounds(344, 74, 96, 19);
		panel.add(priceService);
		priceService.setColumns(10);
		
		JButton btnNewButton = new JButton("EXIT ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiInvoiceServiceManagement g= new GuiInvoiceServiceManagement();
				g.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(520, 116, 85, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(508, 24, 65, 13);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Amount");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(508, 77, 97, 13);
		panel.add(lblNewLabel_5);
		
		quantity = new JTextField();
		quantity.setBounds(605, 21, 96, 19);
		panel.add(quantity);
		quantity.setColumns(10);
		
		amount = new JTextField();
		amount.setBounds(605, 74, 96, 19);
		panel.add(amount);
		amount.setColumns(10);
	}
}
