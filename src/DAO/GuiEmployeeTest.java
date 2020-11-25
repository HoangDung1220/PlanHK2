package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;

import Bean.Employee;
import DB.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class GuiEmployeeTest extends JFrame {

	private JPanel contentPane;
	private JTextField iDe;
	private JTextField fullname;
	private JTextField iCard;
	private JTextField phonenumber;
	private JTextField email;
	private JTextField iDRole;
     List<Employee> list = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiEmployeeTest frame = new GuiEmployeeTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	
	private JTable table;
	private JTextField address;
	
	 public void refresh() {
	    	try {
				String query ="select *from employee";
				PreparedStatement ppst = conn.prepareStatement(query);
				ResultSet rs = ppst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				ppst.close();
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }

	public GuiEmployeeTest() {
		conn = DBConnection.createConnection();
		ListE.createList(conn, list);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IDEmployee");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(24, 21, 78, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fullname");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(24, 64, 78, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gender");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(26, 101, 76, 13);
		
		contentPane.add(lblNewLabel_2);
		final JComboBox sex = new JComboBox();
		sex.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Undefined"}));
		sex.setBounds(131, 98, 116, 21);
		contentPane.add(sex);
		
		JLabel lblNewLabel_3 = new JLabel("ICard ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(26, 143, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Phonenumber");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(357, 26, 90, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_7 = new JLabel("IDRole");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(357, 102, 45, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_9 = new JLabel("Email");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9.setBounds(357, 64, 45, 13);
		contentPane.add(lblNewLabel_9);
		
		iDe = new JTextField();
		iDe.setBounds(131, 24, 116, 19);
		contentPane.add(iDe);
		iDe.setColumns(10);
		
		fullname = new JTextField();
		fullname.setBounds(131, 62, 116, 19);
		contentPane.add(fullname);
		fullname.setColumns(10);
		
		
		
		iCard = new JTextField();
		iCard.setBounds(131, 141, 116, 19);
		contentPane.add(iCard);
		iCard.setColumns(10);
		
		phonenumber = new JTextField();
		phonenumber.setBounds(505, 24, 116, 19);
		contentPane.add(phonenumber);
		phonenumber.setColumns(10);
		
		email = new JTextField();
		email.setBounds(505, 62, 116, 19);
		contentPane.add(email);
		email.setColumns(10);
		
		iDRole = new JTextField();
		iDRole.setBounds(505, 99, 116, 19);
		contentPane.add(iDRole);
		iDRole.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(357, 144, 68, 13);
		contentPane.add(lblNewLabel_5);
		
		address = new JTextField();
		address.setBounds(505, 141, 116, 19);
		contentPane.add(address);
		address.setColumns(10);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int count =ListE.iDMaxEmployee(list);
				count++;
				String id =Random.createStringRandom("NV", count);
				
				
				String fullname1 = fullname.getText();
				String iCard1 = iCard.getText();
				String phonenumber1 = phonenumber.getText();
				String email1 = email.getText();
				String address1 = address.getText();
				String iDRole1 = iDRole.getText();
				String sex1 = sex.getSelectedItem().toString();
				
				Employee e = new Employee();
				
				e.setiDe(id);
				e.setAddress(address1);
				e.setEmail(email1);
				e.setFullname(fullname1);
				e.setiCard(iCard1);
				e.setPhonenumber(phonenumber1);
				e.setRole(iDRole1);
				e.setSex(sex1);
				
				list.add(e);
				
				
			String query = "insert into employee (IDEmployee,Fullname,ICard,Sex,Address,Email,Phonenumber,Role) values (?,?,?,?,?,?,?,?)";
				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					
					
					ppst.setString(1, id);
					ppst.setString(2, fullname1);
					ppst.setString(3, iCard1);
					ppst.setString(4, sex1);
					ppst.setString(5,address1);
					ppst.setString(6, email1);
					ppst.setString(7, phonenumber1);
					ppst.setString(8, iDRole1);
					
					ppst.execute();
					iDe.setText(id);

					JOptionPane.showMessageDialog(null,"Successful");
					ppst.close();

							
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				refresh();
				iDe.setText("");
				fullname.setText("");
				address.setText("");
				email.setText("");
				phonenumber.setText("");
				iDRole.setText("");
				iCard.setText("");
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(49, 217, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					String query ="update employee set IDEmployee ='"+iDe.getText()+"',Fullname ='"+fullname.getText()+"',ICard = '"+iCard.getText()+"',Sex = '"+sex.getSelectedItem().toString()+"',Address ='"+address.getText()+"',Email ='"+email.getText()+"',Phonenumber ='"+phonenumber.getText()+"',Role = '"+iDRole.getText()+"'where IDEmployee ='"+iDe.getText()+"' ";

				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					ppst.execute();
					for (Employee i:list) {
						if (i.getiDe()==iDe.getText()) {
							i.setiDe(iDe.getText());
							i.setAddress(address.getText());
							i.setSex(sex.getSelectedItem().toString());
							i.setEmail(email.getText());
							i.setFullname(fullname.getText());
							i.setiCard(iCard.getText());
							i.setRole(iDRole.getText());
							i.setPhonenumber(phonenumber.getText());
							
						}
					}
					
					JOptionPane.showMessageDialog(null, "Databases are updated");
					ppst.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Databases are not updated");
					e.printStackTrace();
				}
				iDe.setText("");
				fullname.setText("");
				address.setText("");
				email.setText("");
				phonenumber.setText("");
				iDRole.setText("");
				iCard.setText("");
				
				refresh();
				
		
	
				
			}
		});
		btnNewButton_1.setBounds(215, 217, 101, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			int res=JOptionPane.showConfirmDialog(null, "you are sure delete databases","confirm", JOptionPane.YES_NO_OPTION);
			if (res!= JOptionPane.YES_OPTION) {
				return ;
			} else 
			{
			        String query = "delete from employee where IDEmployee ='"+iDe.getText()+"'";
				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					ppst.execute();
					JOptionPane.showMessageDialog(null, "databases are deleted");
					ppst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				iDe.setText("");
				fullname.setText("");
				address.setText("");
				email.setText("");
				phonenumber.setText("");
				iDRole.setText("");
				iCard.setText("");
				
			
			refresh();
			}
			}
		});
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(384, 217, 101, 21);
		contentPane.add(btnNewButton_2);
		
		table = new JTable();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
		try {
		//	String query ="select *from employee";
			String query ="select IDEmployee,Fullname,ICard,Sex as Gender, Address, Email,Phonenumber,Role from employee";

			PreparedStatement ppst = conn.prepareStatement(query);
			ResultSet rs = ppst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			ppst.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		
		
	
		scrollPane.setBounds(49, 280, 634, 193);
		contentPane.add(scrollPane);
		
		JButton btnNewButton_3 = new JButton("EXIT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GUII g = new GUII();
				g.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(559, 217, 101, 21);
		contentPane.add(btnNewButton_3);
		
			table.addMouseListener(new MouseAdapter() {
					@Override
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel ttable = (DefaultTableModel) table.getModel();
					int row = table.getSelectedRow();
					String id = ttable.getValueAt(row,0 ).toString();
					String name = ttable.getValueAt(row,1 ).toString();
					String  icard= ttable.getValueAt(row,2 ).toString();
					String sexx= ttable.getValueAt(row,3 ).toString();
					String address1 = ttable.getValueAt(row,4 ).toString();
					String email1 = ttable.getValueAt(row,5 ).toString();
					String phonenumber1= ttable.getValueAt(row,6 ).toString();
					String role1 = ttable.getValueAt(row,7 ).toString();

					iDe.setText(id);
					fullname.setText(name);
					iCard.setText(icard);
				    sex.setSelectedItem(sexx);;
					address.setText(address1);
					email.setText(email1);
					phonenumber.setText(phonenumber1);
					iDRole.setText(role1);
					
					
				}	
		
				
	});
		
		
		
		
	}
}
