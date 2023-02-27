package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class PinChange extends JFrame implements ActionListener {

	JPasswordField pin, repin;
	JButton change, back;
	String pinnumber;

	public PinChange(String pinnumber) {
		this.pinnumber =pinnumber;

		setLayout(null);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(800, 700, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);

		JLabel image = new JLabel(i3);
		image.setBounds(0, 0, 800, 700);
		add(image);

		JLabel text = new JLabel("CHANGE YOUR PIN");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Raleway", Font.BOLD, 16));
		text.setBounds(220, 220, 400, 30);
		image.add(text);

		JLabel pintext = new JLabel("New PIN:");
		pintext.setForeground(Color.WHITE);
		pintext.setFont(new Font("Raleway", Font.BOLD, 12));
		pintext.setBounds(150, 270, 150, 20);
		image.add(pintext);

		pin = new JPasswordField();
		pin.setFont(new Font("Raleway", Font.BOLD, 20));
		pin.setBounds(280, 270, 150, 20);
		image.add(pin);

		JLabel repintext = new JLabel("Re-Enter New PIN:");
		repintext.setForeground(Color.WHITE);
		repintext.setFont(new Font("Raleway", Font.BOLD, 12));
		repintext.setBounds(150, 300, 150, 20);
		image.add(repintext);

		repin = new JPasswordField();
		repin.setFont(new Font("Raleway", Font.BOLD, 20));
		repin.setBounds(280, 300, 150, 20);
		image.add(repin);

		change = new JButton("Change");
		change.setBounds(300, 370, 130, 25);
		change.addActionListener(this);
		image.add(change);

		back = new JButton("Back");
		back.setBounds(300, 400, 130, 25);
		back.addActionListener(this);
		image.add(back);

		setSize(800, 700);
		setLocation(300, 10);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == change) {
			try {
				String npin = pin.getText();
				String rpin= repin.getText();
				if(!npin.equals(rpin)) {
					JOptionPane.showMessageDialog(null, "Entered Pin not matched");
					return;
				}
				if(npin.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter New PIN");
					return;
				}
				if(rpin.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Re-enter New PIN");
					return;
				}
				
				Conn c =new Conn();
				String query1 = "update bank set pin ='"+rpin+"' where pin = '"+pinnumber+"'";
				String query2 = "update login set pin_number ='"+rpin+"' where pin_number = '"+pinnumber+"'";
				String query3 = "update signupthree set pin_number ='"+rpin+"' where pin_number = '"+pinnumber+"'";
				
				c.stmt.executeUpdate(query1);
				c.stmt.executeUpdate(query2);
				c.stmt.executeUpdate(query3);
				
				JOptionPane.showMessageDialog(null, "PIN change successfully");
				
				setVisible(false);
				new Transaction(rpin).setVisible(true);
			} catch (Exception e) {
				System.out.println(e);
			}
		}else {
			setVisible(false);
			new Transaction(pinnumber).setVisible(true);
		}
	}

	public static void main(String[] args) {
		new PinChange("");
	}
}
