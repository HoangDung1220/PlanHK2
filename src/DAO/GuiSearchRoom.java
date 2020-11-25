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

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GuiSearchRoom extends JFrame {

	private JPanel contentPane;
	private JTextField character;
	private JTable table;

	Connection conn = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiSearchRoom frame = new GuiSearchRoom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GuiSearchRoom() {
		conn = DBConnection.createConnection();
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 556);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, null));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 677, 185);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search for");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(31, 64, 103, 17);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Character");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(31, 125, 92, 13);
		panel.add(lblNewLabel_1);
		
		final JComboBox chose = new JComboBox();
		chose.setFont(new Font("Tahoma", Font.BOLD, 13));
		chose.setModel(new DefaultComboBoxModel(new String[] {"Type", "Status"}));
		chose.setBounds(177, 64, 162, 21);
		panel.add(chose);
		
		character = new JTextField();
		character.setBounds(177, 119, 162, 19);
		panel.add(character);
		character.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("SEARCH ROOM'INFORMATION");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(234, 0, 321, 49);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String seek;
				String check =chose.getSelectedItem().toString();
				if (check.equalsIgnoreCase("Type")) {
		    
				
				 seek = "select *from room where TypeRoom like '%"+character.getText()+"%'"; 

			} else
			{
				seek = "select *from room where Status like '%"+character.getText()+"%'"; 
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(468, 63, 103, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GUII g= new GUII();
				g.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(468, 122, 103, 21);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 240, 677, 220);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
