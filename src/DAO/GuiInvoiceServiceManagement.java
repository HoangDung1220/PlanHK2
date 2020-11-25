package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiInvoiceServiceManagement extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;


	Connection conn = null;
	private JTextField iDBill;
	private JTextField iDClient;
	private JTextField date;
	private JTextField payment;
	public static String id;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiInvoiceServiceManagement frame = new GuiInvoiceServiceManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GuiInvoiceServiceManagement() {
		conn = DBConnection.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 552);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
		panel.setBounds(10, 21, 709, 102);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IDInvoiceService ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(112, 44, 157, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(279, 43, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(432, 40, 125, 21);
		panel.add(btnNewButton);
		
		table = new JTable();
		

		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				String idB = ttable.getValueAt(row,0).toString();
				String idc = ttable.getValueAt(row, 1).toString();
				String time = ttable.getValueAt(row, 2).toString();
				String total = ttable.getValueAt(row, 3).toString();
				
				iDBill.setText(idB);
				iDClient.setText(idc);
				date.setText(time);
				payment.setText(total);
				
				
			}
		});
		scrollPane.setBounds(10, 168, 709, 162);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		String query = "select *from billservice";
		try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(10, 340, 709, 120);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("IDBill");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.setBounds(28, 27, 45, 13);
			panel_1.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("IDClient");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_2.setBounds(28, 77, 54, 13);
			panel_1.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Date of issue");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_3.setBounds(268, 27, 92, 13);
			panel_1.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Payment");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_4.setBounds(268, 77, 77, 13);
			panel_1.add(lblNewLabel_4);
			
			JButton btnNewButton_1 = new JButton("DETAIL");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					id = iDBill.getText();
					dispose();
					GuiDetailServiceManagement g = new GuiDetailServiceManagement();
					g.setVisible(true);
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_1.setBounds(543, 24, 104, 21);
			panel_1.add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("EXIT ");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					GUII g = new GUII();
					g.setVisible(true);
				}
			});
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_2.setBounds(543, 74, 104, 21);
			panel_1.add(btnNewButton_2);
			
			iDBill = new JTextField();
			iDBill.setBounds(109, 25, 96, 19);
			panel_1.add(iDBill);
			iDBill.setColumns(10);
			
			iDClient = new JTextField();
			iDClient.setBounds(109, 75, 96, 19);
			panel_1.add(iDClient);
			iDClient.setColumns(10);
			
			date = new JTextField();
			date.setBounds(370, 25, 96, 19);
			panel_1.add(date);
			date.setColumns(10);
			
			payment = new JTextField();
			payment.setBounds(370, 75, 96, 19);
			panel_1.add(payment);
			payment.setColumns(10);
			ppst.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
