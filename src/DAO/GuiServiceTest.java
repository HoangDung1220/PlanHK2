package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Bean.Service;
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
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
public class GuiServiceTest extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField iDs;
	private JTextField nameService;
	private JTextField price;
	private JTextField unit;
	List<Service> list = new ArrayList<Service>();
	//private JTable table;

	JTable table = new JTable();

	Connection conn =null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiServiceTest frame = new GuiServiceTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 public void refresh() {
	    	try {
				String query ="select *from service";
				PreparedStatement ppst = conn.prepareStatement(query);
				ResultSet rs = ppst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				ppst.close();
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	
	public GuiServiceTest() {
		conn=DBConnection.createConnection();
		ListE.createService(conn, list);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 10, 701, 176);
		contentPane.add(panel);
		panel.setLayout(null);
	
		JLabel lblNewLabel = new JLabel("MANAGEMENT SERVICE");
		lblNewLabel.setBounds(262, 5, 171, 17);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IDServiceAuto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 44, 100, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NameService");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(17, 100, 93, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(396, 44, 45, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Unit");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(398, 94, 45, 13);
		panel.add(lblNewLabel_4);
		
		iDs = new JTextField();
		iDs.setBounds(129, 38, 96, 19);
		panel.add(iDs);
		iDs.setColumns(10);
		
		nameService = new JTextField();
		nameService.setBounds(129, 95, 96, 19);
		panel.add(nameService);
		nameService.setColumns(10);
		
		price = new JTextField();
		price.setBounds(471, 39, 96, 19);
		panel.add(price);
		price.setColumns(10);
		
		unit = new JTextField();
		unit.setBounds(472, 90, 96, 19);
		panel.add(unit);
		unit.setColumns(10);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int count = ListE.iDDMaxService(list);
				count++;
				
				String id = Random.createStringRandom("DV", count);
				String name = nameService.getText();
				String value = price.getText();
				String un = unit.getText();
				
				Service s = new Service();
				s.setiDService(id);
				s.setNameService(name);
				s.setPrice(Float.parseFloat(value));
				s.setUnit(un);
				
				list.add(s);
				
				String query = "insert into service(IDService,NameService,Price,Unit) values (?,?,?,?)";
				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					ppst.setString(1, id);
					ppst.setString(2, name);
					ppst.setString(3, value);
					ppst.setString(4, un);
					
					ppst.execute();
					JOptionPane.showMessageDialog(null, "Insert data successful");
					
					
					ppst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				iDs.setText("");
				nameService.setText("");
				price.setText("");
				unit.setText("");
				refresh();
			}
		});
		btnNewButton.setBounds(32, 142, 85, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int res=JOptionPane.showConfirmDialog(null, "you are sure delete databases","confirm", JOptionPane.YES_NO_OPTION);
				if (res!= JOptionPane.YES_OPTION) {
					return ;
				} else 
				{
				        String query = "delete from service where IDService ='"+iDs.getText()+"'";
					try {
						PreparedStatement ppst = conn.prepareStatement(query);
						ppst.execute();
						JOptionPane.showMessageDialog(null, "databases are deleted");
						ppst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
				}
				
			refresh();
			iDs.setText("");
			nameService.setText("");
			price.setText("");
			unit.setText("");
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(189, 142, 96, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("UPDATE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "update service set IDService ='"+iDs.getText()+"',NameService='"+nameService.getText()+"',Price='"+price.getText()+"',Unit='"+unit.getText()+"' where IDService ='"+iDs.getText()+"'";
			
				try {
					PreparedStatement ppst = conn.prepareStatement(query);
					ppst.execute();
					
					String id = iDs.getText();
					String name = nameService.getText();
					String value = price.getText();
					String un = unit.getText();
					
					iDs.setText(id);
					nameService.setText(name);
					price.setText(value);
					unit.setText(un);
				
					JOptionPane.showMessageDialog(null, "Update database successful");
					ppst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				refresh();
				iDs.setText("");
				nameService.setText("");
				price.setText("");
				unit.setText("");
				
			}
		});
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(348, 142, 95, 21);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("EXIT ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GUII g = new GUII();
				g.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(520, 144, 85, 21);
		panel.add(btnNewButton_3);
		

		
		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel ttable = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				
				String id = table.getValueAt(row, 0).toString();
				String name = table.getValueAt(row, 1).toString();
				String value = table.getValueAt(row,2).toString();
				String un = table.getValueAt(row, 3).toString();
				
				iDs.setText(id);
				nameService.setText(name);
				price.setText(value);
				unit.setText(un);
			
				
				
			}
		});
		
		scrollPane.setBounds(7, 236, 704, 209);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		String query = "select *from service";
		
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
}
