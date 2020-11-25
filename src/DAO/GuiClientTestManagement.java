package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Bean.Client;
import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiClientTestManagement extends JFrame {

	private JPanel contentPane;

	private JTextField iDc;
	private JTextField name;
	private JTextField phonenumber;
	private JTextField email;
	private JTextField address;
	private JTextField icard;
	static List<Client> list = new ArrayList<Client>();
    

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guiclienttest frame = new Guiclienttest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 Connection conn =null;
	    private JTable table;

	    void refresh() {
	    	try {
				String query ="select *from client";
				PreparedStatement ppst = conn.prepareStatement(query);
				ResultSet rs = ppst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				ppst.close();
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    }
	
	public GuiClientTestManagement() {
		conn= DBConnection.createConnection();
		ListE.createListClient(conn, list);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 859, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 816, 242);
		panel.setBackground(Color.GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("MANAGEMENT CLIENT");
		lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel1.setBounds(334, 21, 220, 13);
		panel.add(lblNewLabel1);
		
		JLabel lblNewLabel_1 = new JLabel("IDClientAuto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(59, 62, 90, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(59, 109, 45, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(406, 110, 63, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ICard\r\n\r\n");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(406, 153, 45, 13);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Phonenumber");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(59, 153, 90, 13);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Email");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(406, 62, 45, 13);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Gender");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(648, 63, 69, 13);
		panel.add(lblNewLabel_7);
		
		final JComboBox gender = new JComboBox();
		gender.setFont(new Font("Tahoma", Font.BOLD, 13));
		gender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Undefined"}));
		gender.setBounds(733, 59, 73, 21);
		panel.add(gender);
		
		iDc = new JTextField();
		iDc.setBounds(183, 60, 96, 19);
		panel.add(iDc);
		iDc.setColumns(10);
		
		name = new JTextField();
		name.setBounds(183, 108, 96, 19);
		panel.add(name);
		name.setColumns(10);
		
		phonenumber = new JTextField();
		phonenumber.setBounds(183, 151, 96, 19);
		panel.add(phonenumber);
		phonenumber.setColumns(10);
		
		email = new JTextField();
		email.setBounds(511, 60, 96, 19);
		panel.add(email);
		email.setColumns(10);
		
		address = new JTextField();
		address.setBounds(511, 108, 96, 19);
		panel.add(address);
		address.setColumns(10);
		
		icard = new JTextField();
		icard.setBounds(511, 151, 96, 19);
		panel.add(icard);
		icard.setColumns(10);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count = ListE.iDMax(list);
				count = count+1;
				Client c = new Client();

				String id = Random.createStringRandom("KH",count);
				String name1 = name.getText();
				String email1 = email.getText();
				String address1 = address.getText();
				String icard1 = icard.getText();
				String phone = phonenumber.getText();
				String gender1 = gender.getSelectedItem().toString();
				c.setAddress(address1);
				c.setEmail(email1);
				c.setGender(gender1);
				c.setiCard(icard1);
				c.setNameClient(name1);
				c.setPhonenumber(phone);
				c.setiDClient(id);
				
				list.add(c);				
				String query = "insert into client(IDClient,Name,Address,Email,Phonenumber,ICard,Gender) values (?,?,?,?,?,?,?)";
				try {
					
					PreparedStatement ppst = conn.prepareStatement(query);
					ppst.setString(1, id);
					ppst.setString(2, name1);
					ppst.setString(3, address1);
					ppst.setString(4, email1);
					ppst.setString(5, phone);
					ppst.setString(6, icard1);
					ppst.setString(7, gender1);
					
					
					ppst.execute();
					iDc.setText(id);

					JOptionPane.showMessageDialog(null, "Insert data successful");
					
					
					ppst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				refresh();
				iDc.setText("");
				name.setText("");
				phonenumber.setText("");
				address.setText("");
				icard.setText("");
				gender.setSelectedItem("");
				email.setText("");
			}
		});
		btnNewButton.setBounds(74, 199, 85, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "update client set IDClient ='"+iDc.getText()+"',Name='"+name.getText()+"',Address='"+address.getText()+"',Email='"+email.getText()+"',Phonenumber='"+phonenumber.getText()+"',ICard= '"+icard.getText()+"',Gender='"+gender.getSelectedItem().toString()+"' where IDClient ='"+iDc.getText()+"'";
				
				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					ppst.execute();
								
					String id = iDc.getText();
					String name1 = name.getText();
					String email1 = email.getText();
					String address1 = address.getText();
					String icard1 = icard.getText();
					String phone = phonenumber.getText();
					String gender1 = gender.getSelectedItem().toString();
					
					for (Client i:list)
						if (i.getiDClient().equalsIgnoreCase(id)) {
					i.setAddress(address1);
					i.setEmail(email1);
					i.setGender(gender1);
					i.setiCard(icard1);
					i.setNameClient(name1);
					i.setPhonenumber(phone);
					i.setiDClient(id);
						}
					
					JOptionPane.showMessageDialog(null, "Update database successful");
					ppst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				refresh();
				iDc.setText("");
				name.setText("");
				phonenumber.setText("");
				address.setText("");
				icard.setText("");
				gender.setSelectedItem("");
				email.setText("");
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(230, 199, 96, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int res=JOptionPane.showConfirmDialog(null, "you are sure delete databases","confirm", JOptionPane.YES_NO_OPTION);
				if (res!= JOptionPane.YES_OPTION) {
					return ;
				} else 
				{
				        String query = "delete from client where IDClient ='"+iDc.getText()+"'";
					try {
						PreparedStatement ppst = conn.prepareStatement(query);
				    //	ListE.delClient(list, iDc.getText());

					    ppst.execute();
						JOptionPane.showMessageDialog(null, "databases are deleted");
						ppst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
				}
				refresh();
				iDc.setText("");
				name.setText("");
				phonenumber.setText("");
				address.setText("");
				icard.setText("");
				gender.setSelectedItem("");
				email.setText("");
				
			
			
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(380, 199, 89, 21);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("EXIT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GUII g = new GUII();
				g.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(522, 199, 85, 21);
		panel.add(btnNewButton_3);
		
		
		table = new JTable();
		try {
			String query ="select *from client";
			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			ppst.close();
			rs.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				String id = ttable.getValueAt(row,0 ).toString();
				String name1 = ttable.getValueAt(row,1 ).toString();
				String address1 = ttable.getValueAt(row,2 ).toString();
				String email1 = ttable.getValueAt(row,3 ).toString();
				String phonenumber1= ttable.getValueAt(row,4 ).toString();
				String  icard1 = ttable.getValueAt(row,5 ).toString();
				String gender1= ttable.getValueAt(row,6 ).toString();

				iDc.setText(id);
				name.setText(name1);
				icard.setText(icard1);
			    gender.setSelectedItem(gender1);;
				address.setText(address1);
				email.setText(email1);
				phonenumber.setText(phonenumber1);
				
				
			}
		});
		scrollPane.setBounds(10, 279, 816, 167);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		
		
		
	}
		
	
	}
	
	



