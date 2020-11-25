package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GuiSearchClient extends JFrame {

	private JPanel contentPane;
	private JTextField character;
	private JTable table;
	private JComboBox search;

	Connection conn;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiSearchClient frame = new GuiSearchClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void check() {
		
	}
	
	public GuiSearchClient() {
		conn = DBConnection.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 543);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 79, 696, 158);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Search for");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(110, 29, 85, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Character");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(110, 98, 85, 13);
		panel.add(lblNewLabel_2);
		
		character = new JTextField();
		character.setBounds(248, 95, 134, 19);
		panel.add(character);
		character.setColumns(10);
	
		
		
		JButton btnNewButton_2 = new JButton("EXIT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GUII g = new GUII();
				g.setVisible(true);
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(500, 94, 115, 21);
		panel.add(btnNewButton_2);
		
		final JComboBox search = new JComboBox();
		search.setFont(new Font("Tahoma", Font.BOLD, 13));
		search.setModel(new DefaultComboBoxModel(new String[] {"Name", "Identified Card"}));
		search.setBackground(Color.WHITE);
		search.setBounds(248, 25, 134, 21);
		panel.add(search);
		
		JButton btnNewButton_1 = new JButton("SEARCH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String seek;
				String check =search.getSelectedItem().toString();
				if (check.equalsIgnoreCase("Name")) {
		    
				
				 seek = "select *from client where Name like '%"+character.getText()+"%'"; 

			} else
			{
				seek = "select *from client where ICard like '%"+character.getText()+"%'"; 
			}
				try {
					PreparedStatement ppst = conn.prepareStatement(seek);
					ResultSet rs = ppst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					ppst.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(500, 25, 115, 21);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("SEARCH CLIENT' INFORMATION\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(245, 10, 397, 59);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 291, 689, 182);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
