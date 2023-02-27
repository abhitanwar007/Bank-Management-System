package bank.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Transaction extends JFrame implements ActionListener{
	
	JButton deposit, withdrawl, fastcash, miniStatement, pinchange, balanceEnquiry, exit;
	String pinnumber;
	
	Transaction(String pinnumber){
		this.pinnumber = pinnumber;
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,900,700);
		add(image);
		
		JLabel text = new JLabel("Please select your Transaction");
		text.setBounds(235,250,700,35);
		text.setForeground(Color.WHITE);
		image.add(text);
		
		deposit = new JButton("Deposit"); 
		deposit.setBounds(160,320,150,25);
		deposit.addActionListener(this);
		image.add(deposit);
		
		withdrawl = new JButton("Cash Withdrawl"); 
		withdrawl.setBounds(360,320,150,25);
		withdrawl.addActionListener(this);
		image.add(withdrawl);
		
		fastcash = new JButton("Fast Cash"); 
		fastcash.setBounds(160,350,150,25);
		fastcash.addActionListener(this);
		image.add(fastcash);
		
		miniStatement = new JButton("miniStatement"); 
		miniStatement.setBounds(360,350,150,25);
		miniStatement.addActionListener(this);
		image.add(miniStatement);
		
		pinchange = new JButton("Pin change"); 
		pinchange.setBounds(160,380,150,25);
		pinchange.addActionListener(this);
		image.add(pinchange);
		
		balanceEnquiry = new JButton("Balance Enquiry"); 
		balanceEnquiry.setBounds(360,380,150,25);
		balanceEnquiry.addActionListener(this);
		image.add(balanceEnquiry);
		
		exit = new JButton("Exit"); 
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
			System.exit(0);
		}else if (ae.getSource()==deposit) {
			setVisible(false);
			new Deposit(pinnumber).setVisible(true);
		}
		else if (ae.getSource()==withdrawl) {
			setVisible(false);
			new Withdrawl(pinnumber).setVisible(true);
		}
		else if (ae.getSource()==fastcash) {
			setVisible(false);
			new FastCash(pinnumber).setVisible(true);
		}else if (ae.getSource()==pinchange) {
			setVisible(false);
			new PinChange(pinnumber).setVisible(true);
		}else if (ae.getSource()==balanceEnquiry) {
			setVisible(false);
			new BalanceEnquiry(pinnumber).setVisible(true);
		}else if (ae.getSource()==miniStatement) {
			setVisible(false);
			new MiniStatement(pinnumber).setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		new Transaction("");
	}
}
