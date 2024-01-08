package Packagep1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AdmissionForm extends JFrame implements ActionListener {
    private JLabel titleLabel, criteriaLabel, marksLabel, branchLabel, feeLabel, concessionLabel, nameLabel, ageLabel, genderLabel, fatherLabel, motherLabel, paymentLabel;
    private JComboBox criteriaCombo;
    private JTextField marksField, nameField, ageField, fatherField, motherField;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;
    private JCheckBox scholarshipCheck;
    private JButton submitButton, clearButton;
    private JTextArea displayArea;
    
    public AdmissionForm() {
        super("Admission Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleLabel = new JLabel("Admission Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        add(titlePanel);
        
        // Criteria panel
        JPanel criteriaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        criteriaLabel = new JLabel("Select criteria:");
        criteriaCombo = new JComboBox(new String[] {"EAMCET", "IPE", "MAINS", "ADVANCED"});
        criteriaCombo.addActionListener(this);
        criteriaPanel.add(criteriaLabel);
        criteriaPanel.add(criteriaCombo);
        add(criteriaPanel);
        
        // Marks panel
        JPanel marksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        marksLabel = new JLabel("Enter marks:");
        marksField = new JTextField(10);
        marksPanel.add(marksLabel);
        marksPanel.add(marksField);
        add(marksPanel);
        
        // Branch panel
        JPanel branchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        branchLabel = new JLabel("Available branches:");
        feeLabel = new JLabel("Fee:");
        concessionLabel = new JLabel("Concession:");
        branchPanel.add(branchLabel);
        branchPanel.add(feeLabel);
        branchPanel.add(concessionLabel);
        add(branchPanel);
        
        // Personal details panel
        JPanel personalPanel = new JPanel(new GridLayout(6, 1));
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        ageLabel = new JLabel("Age:");
        ageField = new JTextField(5);
        genderLabel = new JLabel("Gender:");
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        fatherLabel = new JLabel("Father's name:");
        fatherField = new JTextField(20);
        motherLabel = new JLabel("Mother's name:");
        motherField = new JTextField(20);
        personalPanel.add(nameLabel);
        personalPanel.add(nameField);
        personalPanel.add(ageLabel);
        personalPanel.add(ageField);
        personalPanel.add(genderLabel);
        personalPanel.add(maleRadio);
        personalPanel.add(femaleRadio);
        personalPanel.add(fatherLabel);
        personalPanel.add(fatherField);
        personalPanel.add(motherLabel);
        personalPanel.add(motherField);
        add(personalPanel);
        
        // Payment panel
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paymentLabel = new JLabel("Mode of payment:");
        scholarshipCheck = new JCheckBox("Scholarship");
        paymentPanel.add(paymentLabel);

JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
submitButton = new JButton("Submit");
submitButton.addActionListener(this);
clearButton = new JButton("Clear");
clearButton.addActionListener(this);
buttonPanel.add(submitButton);
buttonPanel.add(clearButton);
add(buttonPanel);

// Display panel
JPanel displayPanel = new JPanel(new BorderLayout());
displayArea = new JTextArea(10, 40);
JScrollPane scrollPane = new JScrollPane(displayArea);
displayPanel.add(scrollPane, BorderLayout.CENTER);
add(displayPanel);

pack();
setVisible(true);
}

public void actionPerformed(ActionEvent e) {
if (e.getSource() == criteriaCombo) {
    String criteria = (String) criteriaCombo.getSelectedItem();
    String[] branches = getBranches(criteria);
    displayBranches(branches);
}
else if (e.getSource() == submitButton) {
    String name = nameField.getText();
    String ageStr = ageField.getText();
    int age = 0;
    try {
        age = Integer.parseInt(ageStr);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid age");
        return;
    }
    String gender = "Male";
    if (femaleRadio.isSelected()) {
        gender = "Female";
    }
    String father = fatherField.getText();
    String mother = motherField.getText();
    String criteria = (String) criteriaCombo.getSelectedItem();
    String[] branches = getBranches(criteria);
    String marksStr = marksField.getText();
    int marks = 0;
    try {
        marks = Integer.parseInt(marksStr);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid marks");
        return;
    }
    String branch = getBranch(marks, branches);
    String fee = getFee(branch);
    String concession = getConcession(marks);
    String scholarship = "";
    if (scholarshipCheck.isSelected()) {
        scholarship = "Yes";
    }
    String payment = "Cash";
    String output = "Name: " + name + "\nAge: " + age + "\nGender: " + gender + "\nFather's name: " + father + "\nMother's name: " + mother + "\nCriteria: " + criteria + "\nMarks: " + marks + "\nBranch: " + branch + "\nFee: " + fee + "\nConcession: " + concession + "\nScholarship: " + scholarship + "\nPayment: " + payment + "\n";
    displayArea.append(output);
    try {
        FileWriter writer = new FileWriter("admission.txt", true);
        writer.write(output);
        writer.close();
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error writing to file");
    }
}
else if (e.getSource() == clearButton) {
    nameField.setText("");
    ageField.setText("");
    maleRadio.setSelected(true);
    fatherField.setText("");
    motherField.setText("");
    marksField.setText("");
    displayArea.setText("");
}
}

private String[] getBranches(String criteria) {
String[] branches = null;
if (criteria.equals("EAMCET")) {
    branches = new String[] {"CSE", "ECE", "MECH", "CIVIL", "CHEMICAL"};
}
else if (criteria.equals("IPE")) {
    branches = new String[] {"CSE", "ECE", "MECH", "CIVIL", "CHEMICAL"};
}
else if (criteria.equals("MAINS")) {
	 branches = new String[] {"CSE", "ECE", "MECH", "CIVIL", "CHEMICAL"};
}
}
private void displayBranches(String[] branches) {
    String message = "";
    for (String branch : branches) {
        message += branch + "\n";
    }    
    JOptionPane.showMessageDialog(this, message);
}

private String getBranch(int marks, String[] branches) {
    String branch = "";
    if (marks >= 90) {
        branch = branches[0];
    }
    else if (marks >= 80) {
        branch = branches[1];
    }
    else if (marks >= 70) {
        branch = branches[2];
    }
    else if (marks >= 60) {
        branch = branches[3];
    }
    else {
        branch = "Not eligible";
    }
    return branch;
}

private String getFee(String branch) {
    String fee = "";
    if (branch.equals("CSE")) {
        fee = "1,00,000";
    }
    else if (branch.equals("ECE")) {
        fee = "90,000";
    }
    else if (branch.equals("MECH")) {
        fee = "80,000";
    }
    else if (branch.equals("CIVIL")) {
        fee = "70,000";
    }
    else if (branch.equals("CHEMICAL")) {
        fee = "60,000";
    }
    else {
        fee = "0";
    }
    return fee;
}

private String getConcession(int marks) {
    String concession = "";
    if (marks >= 90) {
        concession = "50%";
    }
    else if (marks >= 80) {
        concession = "30%";
    }
    else if (marks >= 70) {
        concession = "20%";
    }
    else if (marks >= 60) {
        concession = "10%";
    }
    else {
        concession = "0";
    }
    return concession;
}
public static void main(String[] args)
{
    new AdmissionGUI();
}
}