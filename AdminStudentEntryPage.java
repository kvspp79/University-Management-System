import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AdminStudentEntryPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField txtName;
    private JTextField txtFatherName;
    private JTextField txtMotherName;
    private JTextField txtFatherPhone;
    private JTextField txtFatherGmail;
    private JTextField txtStudentPhone;
    private JTextField txtBloodGroup;
    private JTextField txtVillage;
    private JTextField txtPreviousCollege;
    private JTextField txtTenthMarks;
    private JTextField txtInterMarks;
    private JTextField txtGeneratedRoll;
    private JTextField txtGeneratedUsername;
    private JTextField txtGeneratedPassword;

    private JComboBox<String> comboCourse;
    private JTextField txtFees;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminStudentEntryPage frame = new AdminStudentEntryPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminStudentEntryPage() {
        setTitle("Administrative Student Entry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("ADMINISTRATIVE STUDENT ENTRY");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(430, 20, 520, 35);
        contentPane.add(lblTitle);

        JLabel lblName = new JLabel("Student Name");
        lblName.setBounds(80, 90, 150, 25);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(260, 90, 220, 30);
        contentPane.add(txtName);

        JLabel lblFatherName = new JLabel("Father Name");
        lblFatherName.setBounds(80, 140, 150, 25);
        lblFatherName.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblFatherName);

        txtFatherName = new JTextField();
        txtFatherName.setBounds(260, 140, 220, 30);
        contentPane.add(txtFatherName);

        JLabel lblMotherName = new JLabel("Mother Name");
        lblMotherName.setBounds(80, 190, 150, 25);
        lblMotherName.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblMotherName);

        txtMotherName = new JTextField();
        txtMotherName.setBounds(260, 190, 220, 30);
        contentPane.add(txtMotherName);

        JLabel lblFatherPhone = new JLabel("Father Number");
        lblFatherPhone.setBounds(80, 240, 150, 25);
        lblFatherPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblFatherPhone);

        txtFatherPhone = new JTextField();
        txtFatherPhone.setBounds(260, 240, 220, 30);
        contentPane.add(txtFatherPhone);

        JLabel lblFatherGmail = new JLabel("Father Gmail");
        lblFatherGmail.setBounds(80, 290, 150, 25);
        lblFatherGmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblFatherGmail);

        txtFatherGmail = new JTextField();
        txtFatherGmail.setBounds(260, 290, 220, 30);
        contentPane.add(txtFatherGmail);

        JLabel lblStudentPhone = new JLabel("Student Number");
        lblStudentPhone.setBounds(80, 340, 150, 25);
        lblStudentPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblStudentPhone);

        txtStudentPhone = new JTextField();
        txtStudentPhone.setBounds(260, 340, 220, 30);
        contentPane.add(txtStudentPhone);

        JLabel lblBlood = new JLabel("Blood Group");
        lblBlood.setBounds(80, 390, 150, 25);
        lblBlood.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblBlood);

        txtBloodGroup = new JTextField();
        txtBloodGroup.setBounds(260, 390, 220, 30);
        contentPane.add(txtBloodGroup);

        JLabel lblVillage = new JLabel("Village");
        lblVillage.setBounds(80, 440, 150, 25);
        lblVillage.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblVillage);

        txtVillage = new JTextField();
        txtVillage.setBounds(260, 440, 220, 30);
        contentPane.add(txtVillage);

        JLabel lblPreviousCollege = new JLabel("Previous College");
        lblPreviousCollege.setBounds(80, 490, 150, 25);
        lblPreviousCollege.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblPreviousCollege);

        txtPreviousCollege = new JTextField();
        txtPreviousCollege.setBounds(260, 490, 220, 30);
        contentPane.add(txtPreviousCollege);

        JLabel lblTenth = new JLabel("10th Marks");
        lblTenth.setBounds(700, 90, 150, 25);
        lblTenth.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblTenth);

        txtTenthMarks = new JTextField();
        txtTenthMarks.setBounds(900, 90, 220, 30);
        contentPane.add(txtTenthMarks);

        JLabel lblInter = new JLabel("Inter Marks");
        lblInter.setBounds(700, 140, 150, 25);
        lblInter.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblInter);

        txtInterMarks = new JTextField();
        txtInterMarks.setBounds(900, 140, 220, 30);
        contentPane.add(txtInterMarks);

        JLabel lblCourse = new JLabel("Course");
        lblCourse.setBounds(700, 190, 150, 25);
        lblCourse.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblCourse);

        comboCourse = new JComboBox<String>();
        comboCourse.setBounds(900, 190, 220, 30);
        comboCourse.addItem("CSE");
        comboCourse.addItem("AIML");
        comboCourse.addItem("AIDS");
        comboCourse.addItem("Quantum Computing");
        comboCourse.addItem("ECE");
        comboCourse.addItem("EEE");
        comboCourse.addItem("Mechanical");
        comboCourse.addItem("Aerospace");
        comboCourse.addItem("AI");
        comboCourse.addItem("CSE AI");
        contentPane.add(comboCourse);

        JLabel lblFees = new JLabel("Fees");
        lblFees.setBounds(700, 240, 150, 25);
        lblFees.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblFees);

        txtFees = new JTextField();
        txtFees.setBounds(900, 240, 220, 30);
        txtFees.setEditable(false);
        contentPane.add(txtFees);

        JLabel lblRoll = new JLabel("Generated Roll No");
        lblRoll.setBounds(700, 320, 170, 25);
        lblRoll.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblRoll);

        txtGeneratedRoll = new JTextField();
        txtGeneratedRoll.setBounds(900, 320, 220, 30);
        txtGeneratedRoll.setEditable(false);
        contentPane.add(txtGeneratedRoll);

        JLabel lblUser = new JLabel("Generated Username");
        lblUser.setBounds(700, 370, 170, 25);
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblUser);

        txtGeneratedUsername = new JTextField();
        txtGeneratedUsername.setBounds(900, 370, 220, 30);
        txtGeneratedUsername.setEditable(false);
        contentPane.add(txtGeneratedUsername);

        JLabel lblPass = new JLabel("Generated Password");
        lblPass.setBounds(700, 420, 170, 25);
        lblPass.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblPass);

        txtGeneratedPassword = new JTextField();
        txtGeneratedPassword.setBounds(900, 420, 220, 30);
        txtGeneratedPassword.setEditable(false);
        contentPane.add(txtGeneratedPassword);

        JButton btnGenerate = new JButton("GENERATE");
        btnGenerate.setBackground(Color.BLACK);
        btnGenerate.setForeground(Color.WHITE);
        btnGenerate.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnGenerate.setBounds(720, 500, 160, 40);
        contentPane.add(btnGenerate);

        JButton btnSave = new JButton("SAVE");
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSave.setBounds(920, 500, 160, 40);
        contentPane.add(btnSave);

        JButton btnBack = new JButton("BACK");
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBack.setBounds(820, 570, 160, 40);
        contentPane.add(btnBack);

        comboCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String course = comboCourse.getSelectedItem().toString();

                if (course.equals("CSE")) txtFees.setText("400000");
                else if (course.equals("AIML")) txtFees.setText("450000");
                else if (course.equals("AIDS")) txtFees.setText("420000");
                else if (course.equals("Quantum Computing")) txtFees.setText("600000");
                else if (course.equals("ECE")) txtFees.setText("350000");
                else if (course.equals("EEE")) txtFees.setText("330000");
                else if (course.equals("Mechanical")) txtFees.setText("300000");
                else if (course.equals("Aerospace")) txtFees.setText("500000");
                else if (course.equals("AI")) txtFees.setText("430000");
                else if (course.equals("CSE AI")) txtFees.setText("470000");
            }
        });

        btnGenerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (txtName.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter student name first");
                    return;
                }

                Random r = new Random();
                int number = r.nextInt(9000) + 1000;

                String name = txtName.getText().trim().toLowerCase().replace(" ", "");
                String username = name;
                String password = "123";
                String roll = "UMS" + number;

                txtGeneratedRoll.setText(roll);
                txtGeneratedUsername.setText(username);
                txtGeneratedPassword.setText(password);

                JOptionPane.showMessageDialog(null, "Roll, Username, Password generated");
            }
        });

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (txtName.getText().trim().equals("") ||
                    txtFatherName.getText().trim().equals("") ||
                    txtMotherName.getText().trim().equals("") ||
                    txtFatherPhone.getText().trim().equals("") ||
                    txtStudentPhone.getText().trim().equals("") ||
                    txtGeneratedRoll.getText().trim().equals("") ||
                    txtGeneratedUsername.getText().trim().equals("") ||
                    txtGeneratedPassword.getText().trim().equals("")) {

                    JOptionPane.showMessageDialog(null, "Fill all important fields and generate login details");
                    return;
                }

                try {
                    FileWriter fw =new FileWriter("src/students.txt", true);

                    fw.write("Name: " + txtName.getText().trim() + "\n");
                    fw.write("Roll: " + txtGeneratedRoll.getText().trim() + "\n");
                    fw.write("Course: " + comboCourse.getSelectedItem().toString() + "\n");
                    fw.write("Father: " + txtFatherName.getText().trim() + "\n");
                    fw.write("Mother: " + txtMotherName.getText().trim() + "\n");
                    fw.write("FatherPhone: " + txtFatherPhone.getText().trim() + "\n");
                    fw.write("FatherGmail: " + txtFatherGmail.getText().trim() + "\n");
                    fw.write("StudentPhone: " + txtStudentPhone.getText().trim() + "\n");
                    fw.write("BloodGroup: " + txtBloodGroup.getText().trim() + "\n");
                    fw.write("Village: " + txtVillage.getText().trim() + "\n");
                    fw.write("PreviousCollege: " + txtPreviousCollege.getText().trim() + "\n");
                    fw.write("TenthMarks: " + txtTenthMarks.getText().trim() + "\n");
                    fw.write("InterMarks: " + txtInterMarks.getText().trim() + "\n");
                    fw.write("Fees: " + txtFees.getText().trim() + "\n");
                    fw.write("Username: " + txtGeneratedUsername.getText().trim() + "\n");
                    fw.write("Password: " + txtGeneratedPassword.getText().trim() + "\n");
                    fw.write("----------------------\n");

                    fw.close();

                    JOptionPane.showMessageDialog(null, "Student data saved successfully");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error saving student data");
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministrativeLoginPage page = new AdministrativeLoginPage();
                page.setVisible(true);
                dispose();
            }
        });
    }
}