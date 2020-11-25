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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class StaticRoom extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection conn = null;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaticRoom frame = new StaticRoom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	public StaticRoom() {
		conn = DBConnection.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 547);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 677, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FROM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(56, 42, 45, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("TO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(356, 42, 45, 13);
		panel.add(lblNewLabel_1);
		
		final JDateChooser from = new JDateChooser();
		from.setBounds(125, 36, 158, 19);
		panel.add(from);
		
		final JDateChooser to = new JDateChooser();
		to.setBounds(418, 42, 145, 19);
		panel.add(to);
		
		JButton btnNewButton = new JButton("STATISTIC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date start = from.getDate();
				Date end = to.getDate();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String from1 = sdf.format(start);
				String to1 = sdf.format(end);
				
 				String query = "select *from billroom where PayDate between '"+from1+"' and '"+to1+"'";
 				
				
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(149, 86, 134, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GUII g = new GUII();
				g.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(348, 86, 117, 21);
		panel.add(btnNewButton_1);
		
	
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 677, 231);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
