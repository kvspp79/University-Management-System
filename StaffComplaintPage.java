import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StaffComplaintPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtRoll;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StaffComplaintPage frame = new StaffComplaintPage("Staff");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StaffComplaintPage(String staffName) {
        setTitle("Raise Complaint on Student");
        setBounds(200, 150, 800, 550);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("RAISE STUDENT COMPLAINT");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(200, 30, 380, 35);
        contentPane.add(lblTitle);

        JLabel lblRoll = new JLabel("Student Roll");
        lblRoll.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRoll.setBounds(100, 110, 120, 30);
        contentPane.add(lblRoll);

        txtRoll = new JTextField();
        txtRoll.setBounds(250, 110, 250, 32);
        contentPane.add(txtRoll);

        JLabel lblComplaint = new JLabel("Complaint");
        lblComplaint.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblComplaint.setBounds(100, 180, 120, 30);
        contentPane.add(lblComplaint);

        JTextArea area = new JTextArea();
        JScrollPane sp = new JScrollPane(area);
        sp.setBounds(250, 180, 400, 180);
        contentPane.add(sp);

        JButton btnSave = new JButton("SAVE COMPLAINT");
        btnSave.setBounds(280, 410, 220, 40);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnSave);

        btnSave.addActionListener(e -> {
            try {
                if (txtRoll.getText().trim().equals("") || area.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill all fields");
                    return;
                }

                FileWriter fw = new FileWriter("src/staff_complaints.txt", true);
                fw.write("Staff: " + staffName + "\n");
                fw.write("Roll: " + txtRoll.getText().trim() + "\n");
                fw.write("Complaint: " + area.getText().trim() + "\n");
                fw.write("----------------------\n");
                fw.close();

                JOptionPane.showMessageDialog(null, "Complaint saved successfully");
                txtRoll.setText("");
                area.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving complaint");
            }
        });
    }
}