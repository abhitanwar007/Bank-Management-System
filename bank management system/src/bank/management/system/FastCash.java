package bank.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{
	
	JButton deposit, withdrawl, fastcash, miniStatement, pinchange, balanceEnquiry, exit;
	String pinnumber;
	
	FastCash(String pinnumber){
		this.pinnumber = pinnumber;
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,900,700);
		add(image);
		
		JLabel text = new JLabel("SELECT WITHDROWL AMOUNT");
		text.setBounds(235,250,700,35);
		text.setForeground(Color.WHITE);
		image.add(text);
		
		deposit = new JButton("Rs 100"); 
		deposit.setBounds(160,320,150,25);
		deposit.addActionListener(this);
		image.add(deposit);
		
		withdrawl = new JButton("Rs 500"); 
		withdrawl.setBounds(360,320,150,25);
		withdrawl.addActionListener(this);
		image.add(withdrawl);
		
		fastcash = new JButton("Rs 1000"); 
		fastcash.setBounds(160,350,150,25);
		fastcash.addActionListener(this);
		image.add(fastcash);
		
		miniStatement = new JButton("Rs 2000"); 
		miniStatement.setBounds(360,350,150,25);
		miniStatement.addActionListener(this);
		image.add(miniStatement);
		
		pinchange = new JButton("Rs 5000"); 
		pinchange.setBounds(160,380,150,25);
		pinchange.addActionListener(this);
		image.add(pinchange);
		
		balanceEnquiry = new JButton("Rs 10000"); 
		balanceEnquiry.setBounds(360,380,150,25);
		balanceEnquiry.addActionListener(this);
		image.add(balanceEnquiry);
		
		exit = new JButton("BACK"); 
		exit.setBounds(360,410,150,25);
		exit.addActionListener(this);
		image.add(exit);
		
		setSize(900,700);
		setLocation(300,10);
		setUndecorated(true);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==exit) {
			setVisible(false);
			new Transaction(pinnumber).setVisible(true);
		}else {
			String amount = ((JButton)ae.getSource()).getText().substring(3);
			Conn c = new Conn();
			try {
				ResultSet rs = c.stmt.executeQuery("select * from bank where pin = '"+pinnumber+"'");
				int balance=0;
				while(rs.next()) {
					if(rs.getString("type").equals("Deposit")) {
						balance += Integer.parseInt(rs.getString("amount"));
					}else {
						balance -= Integer.parseInt(rs.getString("amount"));
					}
				}
				if(ae.getSource() != exit && balance < Integer.parseInt(amount)) {
					JOptionPane.showMessageDialog(null, "Insufficient Balance");
					return;
				}
				Date date = new Date();
				String query = "insert into bank values('" + pinnumber + "','" + date + "','Withdrawl','" + amount + "')";
				c.stmt.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Rs "+amount+" debited successfully");
				
				setVisible(false);
				new Transaction(pinnumber).setVisible(true);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public static void main(String[] args) {
		new FastCash("");
	}
}
