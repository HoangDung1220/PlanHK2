package DAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class GUII extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUII frame = new GUII();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUII() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 551);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Introduction");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Introduction");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Management");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Room");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiRoomTestEnglish g = new GuiRoomTestEnglish();
				g.setVisible(true);
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Employee");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				GuiEmployeeTest g = new GuiEmployeeTest();
				g.setVisible(true);
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Client");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiClientTestManagement g = new GuiClientTestManagement();
				g.setVisible(true);
				
				
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Service");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiServiceTest g = new GuiServiceTest();
				g.setVisible(true);
			}
		});
		mntmNewMenuItem_14.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_1.add(mntmNewMenuItem_14);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Invoice Room");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				dispose();
				GuiInvoiceRoomManagement g = new GuiInvoiceRoomManagement();
				g.setVisible(true);
				
			}
		});
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Invoice Service");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiInvoiceServiceManagement g = new GuiInvoiceServiceManagement();
				g.setVisible(true);
			}
		});
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_2 = new JMenu("Function");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Check-in Room");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiRegistrationRentRoomTest g = new GuiRegistrationRentRoomTest();
				g.setVisible(true);
				
			}
		});
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Check-out Room");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				GuiCheckOut g = new GuiCheckOut();
				g.setVisible(true);
			}
		});
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Service");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiBillServiceTest g = new GuiBillServiceTest();
				g.setVisible(true);
			}
		});
		mntmNewMenuItem_9.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_2.add(mntmNewMenuItem_9);
		
		JMenu mnNewMenu_3 = new JMenu("Search");
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Client");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			GuiSearchClient g = new GuiSearchClient();
			g.setVisible(true);
				
			}
		});
		mntmNewMenuItem_10.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_3.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Room");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiSearchRoom g = new GuiSearchRoom();
				g.setVisible(true);
			}
		});
		mntmNewMenuItem_11.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_3.add(mntmNewMenuItem_11);
		
		JMenu mnNewMenu_4 = new JMenu("Statistics");
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Room");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				StaticRoom g = new StaticRoom();
				g.setVisible(true);
			}
		});
		mntmNewMenuItem_12.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_4.add(mntmNewMenuItem_12);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Service");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				StaticService g = new StaticService();
				g.setVisible(true);
			}
		});
		mntmNewMenuItem_13.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_4.add(mntmNewMenuItem_13);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
