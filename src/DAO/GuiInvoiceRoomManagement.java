package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiInvoiceRoomManagement extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	Connection conn = null;
	private JTextField iDBill;
	private JTextField iDR;
	private JTextField payDay;
	private JTextField iDRoom;
	private JTextField iDClient;
	private JTextField iDEmployee;
	private JTextField total;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiInvoiceRoomManagement frame = new GuiInvoiceRoomManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GuiInvoiceRoomManagement() {
		conn = DBConnection.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 542);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
		panel.setBounds(10, 10, 714, 92);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IDInvoiceRoom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(139, 38, 121, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textField.setBounds(293, 37, 174, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(513, 34, 129, 21);
		panel.add(btnNewButton);
		table = new JTable();
			

		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				
				String idB = ttable.getValueAt(row, 0).toString();
				String idR = ttable.getValueAt(row, 1).toString();
				String date = ttable.getValueAt(row, 5).toString();
				String value = ttable.getValueAt(row, 6).toString();
				String idC = ttable.getValueAt(row, 2).toString();
				String idE = ttable.getValueAt(row, 3).toString();
				String idR1 = ttable.getValueAt(row, 4).toString();
			
				iDBill.setText(idB);
				iDR.setText(idR);
				payDay.setText(date);
				iDRoom.setText(idR1);
				iDClient.setText(idC);
				iDEmployee.setText(idE);
				total.setText(value);
			}
		});
		scrollPane.setBounds(10, 112, 714, 199);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		String query = "select IDBillRoom,IDRegistration,IDClient,IDEmployee,IDRoom,PayDate,PriceRoom,Status from billroom,registrationrentroom where billroom.IDRegistration=registrationrentroom.IDRegistrationRent";
		
		try {
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			ppst.close();
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("Information\r\n");
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 334, 714, 161);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("IDBill");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(23, 27, 45, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IDRegistration");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(23, 80, 96, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Pay-Day");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(23, 138, 73, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("IDRoom");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(302, 27, 67, 13);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("IDClient");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(302, 80, 67, 13);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("IDEmployee");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(302, 138, 78, 13);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Total");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(534, 27, 45, 13);
		panel_1.add(lblNewLabel_7);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(571, 76, 104, 21);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("EXIT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GUII g = new GUII();
				g.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(571, 130, 104, 21);
		panel_1.add(btnNewButton_2);
		
		iDBill = new JTextField();
		iDBill.setBounds(149, 25, 96, 19);
		panel_1.add(iDBill);
		iDBill.setColumns(10);
		
		iDR = new JTextField();
		iDR.setBounds(149, 78, 96, 19);
		panel_1.add(iDR);
		iDR.setColumns(10);
		
		payDay = new JTextField();
		payDay.setBounds(149, 136, 96, 19);
		panel_1.add(payDay);
		payDay.setColumns(10);
		
		iDRoom = new JTextField();
		iDRoom.setBounds(389, 25, 96, 19);
		panel_1.add(iDRoom);
		iDRoom.setColumns(10);
		
		iDClient = new JTextField();
		iDClient.setBounds(389, 78, 96, 19);
		panel_1.add(iDClient);
		iDClient.setColumns(10);
		
		iDEmployee = new JTextField();
		iDEmployee.setBounds(389, 136, 96, 19);
		panel_1.add(iDEmployee);
		iDEmployee.setColumns(10);
		
		total = new JTextField();
		total.setBounds(608, 27, 96, 19);
		panel_1.add(total);
		total.setColumns(10);
	}
}
