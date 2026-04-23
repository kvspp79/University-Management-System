import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AdminPaymentUpdatePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtRoll;
    private JTextField txtStudentName;
    private JTextField txtTotalFees;
    private JTextField txtPaidAmount;
    private JTextField txtBalance;
    private JTextField txtStatus;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminPaymentUpdatePage frame = new AdminPaymentUpdatePage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminPaymentUpdatePage() {
        setTitle("Update Payment Status");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(180, 120, 800, 560);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("UPDATE PAYMENT STATUS");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(230, 20, 360, 35);
        contentPane.add(lblTitle);

        addLabel("Roll Number", 120, 100);
        addLabel("Student Name", 120, 150);
        addLabel("Total Fees", 120, 200);
        addLabel("Paid Amount", 120, 250);
        addLabel("Balance", 120, 300);
        addLabel("Status", 120, 350);

        txtRoll = addField(280, 100);
        txtStudentName = addField(280, 150);
        txtStudentName.setEditable(false);
        txtTotalFees = addField(280, 200);
        txtTotalFees.setEditable(false);
        txtPaidAmount = addField(280, 250);
        txtBalance = addField(280, 300);
        txtBalance.setEditable(false);
        txtStatus = addField(280, 350);
        txtStatus.setEditable(false);

        JButton btnLoad = new JButton("LOAD STUDENT");
        style(btnLoad);
        btnLoad.setBounds(560, 100, 170, 35);
        contentPane.add(btnLoad);

        JButton btnCalculate = new JButton("CALCULATE");
        style(btnCalculate);
        btnCalculate.setBounds(560, 250, 170, 35);
        contentPane.add(btnCalculate);

        JButton btnSave = new JButton("SAVE PAYMENT");
        style(btnSave);
        btnSave.setBounds(220, 430, 170, 40);
        contentPane.add(btnSave);

        JButton btnClose = new JButton("CLOSE");
        style(btnClose);
        btnClose.setBounds(430, 430, 170, 40);
        contentPane.add(btnClose);

        btnLoad.addActionListener(e -> loadStudent());
        btnCalculate.addActionListener(e -> calculateStatus());
        btnSave.addActionListener(e -> savePayment());
        btnClose.addActionListener(e -> dispose());
    }

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbl.setBounds(x, y, 140, 30);
        contentPane.add(lbl);
    }

    private JTextField addField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 220, 32);
        contentPane.add(tf);
        return tf;
    }

    private void style(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
    }

    private void loadStudent() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/students.txt"));
            String line;
            boolean found = false;
            String name = "";
            String fees = "";

            while ((line = br.readLine()) != null) {
                if (line.equals("Roll: " + txtRoll.getText().trim())) {
                    found = true;
                }

                if (found && line.startsWith("Name:")) {
                    name = line.substring(5).trim();
                }

                if (found && line.startsWith("Fees:")) {
                    fees = line.substring(5).trim();
                }

                if (line.startsWith("----")) {
                    if (found) break;
                    found = false;
                }
            }

            br.close();

            if (!found) {
                JOptionPane.showMessageDialog(null, "Student not found");
                return;
            }

            txtStudentName.setText(name);
            txtTotalFees.setText(fees);
            JOptionPane.showMessageDialog(null, "Student loaded successfully");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading student");
        }
    }

    private void calculateStatus() {
        try {
            int total = Integer.parseInt(txtTotalFees.getText().trim());
            int paid = Integer.parseInt(txtPaidAmount.getText().trim());
            int balance = total - paid;

            txtBalance.setText(String.valueOf(balance));

            if (paid <= 0) txtStatus.setText("Pending");
            else if (balance == 0) txtStatus.setText("Completed");
            else txtStatus.setText("Partial");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Enter valid paid amount");
        }
    }

    private void savePayment() {
        try {
            if (txtRoll.getText().trim().equals("") ||
                txtStudentName.getText().trim().equals("") ||
                txtTotalFees.getText().trim().equals("") ||
                txtPaidAmount.getText().trim().equals("") ||
                txtBalance.getText().trim().equals("") ||
                txtStatus.getText().trim().equals("")) {

                JOptionPane.showMessageDialog(null, "Load student and calculate payment first");
                return;
            }

            FileWriter fw = new FileWriter("src/payment_records.txt", true);
            fw.write("Roll: " + txtRoll.getText().trim() + "\n");
            fw.write("Student: " + txtStudentName.getText().trim() + "\n");
            fw.write("TotalFees: " + txtTotalFees.getText().trim() + "\n");
            fw.write("PaidAmount: " + txtPaidAmount.getText().trim() + "\n");
            fw.write("Balance: " + txtBalance.getText().trim() + "\n");
            fw.write("Status: " + txtStatus.getText().trim() + "\n");
            fw.write("Date: " + LocalDate.now().toString() + "\n");
            fw.write("----------------------\n");
            fw.close();

            JOptionPane.showMessageDialog(null, "Payment updated successfully");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error saving payment");
        }
    }
}