package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.toedter.calendar.*;

public class SignupTwo extends JFrame implements ActionListener{

	JTextField pan,aadhar;
	JButton next;
	JRadioButton syes, sno,eyes,eno;
	JComboBox religion, category, income, education, occupation;
	String formno;

	SignupTwo(String fromno) {
		this.formno=formno;
		
		setLayout(null);
		
		setTitle("NEW ACCOUNT APPLICATOIN FORM - PAGE 2");

		JLabel AdditionalDetails = new JLabel("Page 2: Additional Details");
		AdditionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
		AdditionalDetails.setBounds(290, 80, 400, 30);
		add(AdditionalDetails);

		JLabel name = new JLabel("Religion:");
		name.setFont(new Font("Raleway", Font.BOLD, 20));
		name.setBounds(100, 140, 100, 30);
		add(name);
		
		String valReligion[] = {"hindu","Muslim","Sikh","Christinan","Other"};
		religion = new JComboBox(valReligion);
		religion.setBackground(Color.WHITE);
		religion.setBounds(300, 140, 400, 30);
		add(religion);

		JLabel fname = new JLabel("Category:");
		fname.setFont(new Font("Raleway", Font.BOLD, 20));
		fname.setBounds(100, 190, 200, 30);
		add(fname);
		
		String valCategory[] = {"General","OBC","SC","ST","Other"};
		category = new JComboBox(valCategory);
		category.setBackground(Color.WHITE);
		category.setBounds(300, 190, 400, 30);
		add(category);

		JLabel dob = new JLabel("Income:");
		dob.setFont(new Font("Raleway", Font.BOLD, 20));
		dob.setBounds(100, 240, 200, 30);
		add(dob);
		
		String incomeCategory[] = {"Nill","< 1,50,000","< 2,50,00","< 5,00,00","upto 10,000,00"};
		income = new JComboBox(incomeCategory);
		income.setBackground(Color.WHITE);
		income.setBounds(300, 240, 400, 30);
		add(income);

		JLabel gender = new JLabel("Educational");
		gender.setFont(new Font("Raleway", Font.BOLD, 20));
		gender.setBounds(100, 290, 200, 30);
		add(gender);

		JLabel email = new JLabel("Qualification:");
		email.setFont(new Font("Raleway", Font.BOLD, 20));
		email.setBounds(100, 315, 200, 30);
		add(email);
		
		String educationValues[] = {"Non-Graduate","Graduate","Post-Graduate","Doctrate","Others"};
		education = new JComboBox(educationValues);
		education.setBackground(Color.WHITE);
		education.setBounds(300, 315, 400, 30);
		add(education);

		JLabel merital = new JLabel("Occupation:");
		merital.setFont(new Font("Raleway", Font.BOLD, 20));
		merital.setBounds(100, 390, 200, 30);
		add(merital);

		String occupationValues[] = {"Salaried","Self Employed","Bussiness","Student","Retired","Others"};
		occupation = new JComboBox(occupationValues);
		occupation.setBackground(Color.WHITE);
		occupation.setBounds(300, 390, 400, 30);
		add(occupation);

		JLabel address = new JLabel("PAN Number:");
		address.setFont(new Font("Raleway", Font.BOLD, 20));
		address.setBounds(100, 440, 200, 30);
		add(address);

		pan = new JTextField();
		pan.setFont(new Font("Raleway", Font.BOLD, 14));
		pan.setBounds(300, 440, 400, 30);
		add(pan);

		JLabel city = new JLabel("Aadhar Number:");
		city.setFont(new Font("Raleway", Font.BOLD, 20));
		city.setBounds(100, 490, 200, 30);
		add(city);

		aadhar = new JTextField();
		aadhar.setFont(new Font("Raleway", Font.BOLD, 14));
		aadhar.setBounds(300, 490, 400, 30);
		add(aadhar);

		JLabel state = new JLabel("Senior Citizen:");
		state.setFont(new Font("Raleway", Font.BOLD, 20));
		state.setBounds(100, 540, 200, 30);
		add(state);

		syes = new JRadioButton("Yes");
		syes.setBounds(300, 540, 120, 30);
		syes.setBackground(Color.white);
		add(syes);
		
		sno = new JRadioButton("No");
		sno.setBounds(450, 540, 120, 30);
		sno.setBackground(Color.white);
		add(sno);
		
		ButtonGroup seniorGroup = new ButtonGroup();
		seniorGroup.add(syes);
		seniorGroup.add(sno);
		
		JLabel pincode = new JLabel("Existing Account:");
		pincode.setFont(new Font("Raleway", Font.BOLD, 20));
		pincode.setBounds(100, 590, 200, 30);
		add(pincode);

		eyes = new JRadioButton("Yes");
		eyes.setBounds(300, 590, 120, 30);
		eyes.setBackground(Color.white);
		add(eyes);
		
		eno = new JRadioButton("No");
		eno.setBounds(450, 590, 120, 30);
		eno.setBackground(Color.white);
		add(eno);
		
		ButtonGroup existingGroup = new ButtonGroup();
		existingGroup.add(eyes);
		existingGroup.add(eno);

		next = new JButton("Next");
		next.setFont(new Font("Raleway", Font.BOLD, 14));
		next.setBounds(620, 640, 80, 30);
		next.setBackground(Color.black);
		next.setForeground(Color.white);
		next.addActionListener(this);
		add(next);

		getContentPane().setBackground(Color.WHITE);

		setSize(800, 720);
		setLocation(300, 10);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		String sformno = formno;
		String sreligion= (String) religion.getSelectedItem();
		String scategory= (String) category.getSelectedItem();
		String sincome= (String) income.getSelectedItem();
		String seducation= (String) education.getSelectedItem();
		String soccupation= (String) occupation.getSelectedItem();
		String seniorcitizon =null;
		if(syes.isSelected()) {
			seniorcitizon = "Yes";	
		}else if (sno.isSelected()) {
			seniorcitizon="No";
		}
		
		String existingaccount = null;
		if(eyes.isSelected()) {
			existingaccount = "Yes";
		}else if (eno.isSelected()) {
			existingaccount = "No";
		}
		
		String span = pan.getText();
		String saadhar = aadhar.getText();
		
		try {
			Conn c = new Conn();
			String sql = "insert into signuptwo values('"+sformno+"','"+sreligion+"','"+scategory+"','"+sincome+"','"+seducation+"','"+soccupation+"','"+seniorcitizon+"','"+existingaccount+"','"+span+"','"+saadhar+"')";
			c.stmt.executeUpdate(sql);
			
			setVisible(false);
			new SignupThree(formno).setVisible(true);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		new SignupTwo("");
	}
}
