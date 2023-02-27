package bank.management.system;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

	JButton login, signup, clear;
	JTextField cardTextField;
	JPasswordField pinTextField;

	Login() {
		setTitle("ATM");

		setLayout(null);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel lable = new JLabel(i3);
		lable.setBounds(70, 10, 100, 100);
		add(lable);

		getContentPane().setBackground(Color.WHITE);

		JLabel text = new JLabel("Welcome to ATM");
		text.setFont(new Font("Osword", Font.BOLD, 38));
		text.setBounds(200, 40, 400, 40);
		add(text);

		JLabel cardno = new JLabel("Card no.");
		cardno.setFont(new Font("raleway", Font.BOLD, 28));
		cardno.setBounds(120, 150, 150, 30);
		add(cardno);

		cardTextField = new JTextField();
		cardTextField.setBounds(300, 150, 250, 30);
		cardTextField.setFont(new Font("Arial", Font.BOLD,14));
		add(cardTextField);

		JLabel pin = new JLabel("PIN");
		pin.setFont(new Font("raleway", Font.BOLD, 28));
		pin.setBounds(120, 220, 150, 30);
		add(pin);

		pinTextField = new JPasswordField();
		pinTextField.setBounds(300, 220, 250, 30);
		pinTextField.setFont(new Font("Arial", Font.BOLD,14));
		add(pinTextField);

		login = new JButton("SIGN IN");	
		login.setBounds(300, 300, 100, 30);
		login.setBackground(Color.black);
		login.setForeground(Color.white);
		login.addActionListener(this);
		add(login);

		clear = new JButton("CLEAR");
		clear.setBounds(450, 300, 100, 30);
		clear.setBackground(Color.black);
		clear.setForeground(Color.white);
		clear.addActionListener(this);
		add(clear);

		signup = new JButton("SIGN UP");
		signup.setBounds(300, 350, 250, 30);
		signup.setBackground(Color.black);
		signup.setForeground(Color.white);
		signup.addActionListener(this);
		add(signup);

		setSize(800, 480);
		setVisible(true);
		setLocation(300, 150);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == clear) {
			cardTextField.setText("");
			pinTextField.setText("");
		}else if (ae.getSource() == signup) {
			setVisible(false);
			new SignupOne().setVisible(true);
		}else if(ae.getSource() == login) {
			Conn c = new Conn();
			String cardnumber= cardTextField.getText();
			String pinnumber = pinTextField.getText();
			
			String query = "select * from login where card_number = '"+cardnumber+"' and pin_number = '"+pinnumber+"'";
			try {
				ResultSet rs =c.stmt.executeQuery(query);
				if(rs.next()) {
					setVisible(false);
					new Transaction(pinnumber).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect card number or pin");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
