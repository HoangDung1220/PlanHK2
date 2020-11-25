import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Demo extends JFrame {

	private JPanel contentPane;
	private JTextField fullname;
	private JTextField id;
	private JTextField email;
	private JTextField address;
	private JTextField phonenumber;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo frame = new Demo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn =null;

	
	public Demo() {
		conn = DBConnection.createConnection();
		setTitle("REGISTER FORM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("FullName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(23, 53, 184, 48);
		contentPane.add(lblNewLabel_1);
		
		fullname = new JTextField();
		fullname.setBounds(170, 55, 463, 50);
		contentPane.add(fullname);
		fullname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(40, 161, 77, 13);
		contentPane.add(lblNewLabel_2);
		
		id = new JTextField();
		id.setBounds(170, 137, 463, 48);
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(40, 231, 77, 36);
		contentPane.add(lblNewLabel_3);
		
		email = new JTextField();
		email.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		email.setBounds(167, 231, 466, 43);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setBounds(40, 319, 77, 31);
		contentPane.add(lblNewLabel_4);
		
		address = new JTextField();
		address.setBounds(166, 304, 471, 43);
		contentPane.add(address);
		address.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Phonenumber");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_5.setBounds(23, 388, 142, 31);
		contentPane.add(lblNewLabel_5);
		
		phonenumber = new JTextField();
		phonenumber.setBounds(170, 388, 463, 37);
		contentPane.add(phonenumber);
		phonenumber.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conn = DBConnection.createConnection();
				String sql="insert into customer (id,email,fullname,phonenumber,address) values (?,?,?,?,?)";
				try {
					PreparedStatement pst = conn.prepareStatement(sql);
					
					pst.setString(1, id.getText());
					
					int check =0;
					
					String text = "[a-zA-Z]+[a-zA-Z0-9]+@{1}+[a-zA-Z]{1}+mail.com";
					Pattern pattern = Pattern.compile(text);
					while (check==0)
					{
						String input = email.getText();
					
					Matcher matcher = pattern.matcher(input);
					if (matcher.find()==false) {
						JOptionPane.showMessageDialog(null, "Email is not invalid. Please entry again!");
						
						
					} else check=1;
					}		
					pst.setString(2, email.getText());
 
				pst.setString(3, fullname.getText());
					pst.setString(4, phonenumber.getText());
					pst.setString(5, address.getText());
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Save Data");
					
					pst.close();
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Dont Save Data");
					e.printStackTrace();
				}
				
				
			
			}
		});
		btnNewButton.setBounds(680, 70, 85, 31);
		contentPane.add(btnNewButton);
	}
}
