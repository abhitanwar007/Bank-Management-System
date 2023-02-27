package bank.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BalanceEnquiry extends JFrame implements ActionListener{
	String pinnumber;
	JButton back;
	
	BalanceEnquiry(String pinnumber){
		this.pinnumber=pinnumber;
		
		setLayout(null);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(800, 700, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);

		JLabel image = new JLabel(i3);
		image.setBounds(0, 0, 800, 700);
		add(image);
		
		back = new JButton("Back");
		back.setBounds(300,400,150,25);
		back.addActionListener(this);
		image.add(back);
		
		Conn c = new Conn();
		int balance=0;
		try {
			ResultSet rs = c.stmt.executeQuery("select * from bank where pin = '"+pinnumber+"'");
			
			while(rs.next()) {
				if(rs.getString("type").equals("Deposit")) {
					balance += Integer.parseInt(rs.getString("amount"));
				}else {
					balance -= Integer.parseInt(rs.getString("amount"));
				}
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		
		JLabel text = new JLabel("Your Current Account balance is Rs: "+balance);
		text.setForeground(Color.WHITE);
		text.setBounds(150, 250, 400, 30);
		image.add(text);
		
		setSize(800, 700);
		setLocation(300, 10);
		setVisible(true);

	}
	public void actionPerformed(ActionEvent ae) {
		setVisible(false);
		new Transaction(pinnumber).setVisible(true);
	}
	
	public static void main(String[] args) {
		new BalanceEnquiry("");
	}
}
