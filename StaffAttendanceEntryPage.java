import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StaffAttendanceEntryPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtRoll;
    private JTextField txtTotal;
    private JTextField txtPresent;
    private JTextField txtSubject;
    private JTextField txtDate;
    private JTextField txtPeriod;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StaffAttendanceEntryPage frame = new StaffAttendanceEntryPage("Maths");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StaffAttendanceEntryPage(String subjectFromStaff) {
        setTitle("Give Attendance");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 150, 750, 560);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("GIVE ATTENDANCE");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(220, 30, 260, 35);
        contentPane.add(lblTitle);

        JLabel lblRoll = new JLabel("Student Roll");
        lblRoll.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRoll.setBounds(120, 100, 120, 30);
        contentPane.add(lblRoll);

        txtRoll = new JTextField();
        txtRoll.setBounds(300, 100, 220, 32);
        contentPane.add(txtRoll);

        JLabel lblSubject = new JLabel("Subject");
        lblSubject.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSubject.setBounds(120, 150, 120, 30);
        contentPane.add(lblSubject);

        txtSubject = new JTextField();
        txtSubject.setBounds(300, 150, 220, 32);
        txtSubject.setText(subjectFromStaff);
        contentPane.add(txtSubject);

        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDate.setBounds(120, 200, 120, 30);
        contentPane.add(lblDate);

        txtDate = new JTextField();
        txtDate.setBounds(300, 200, 220, 32);
        txtDate.setToolTipText("Example: 2026-04-15");
        contentPane.add(txtDate);

        JLabel lblPeriod = new JLabel("Period");
        lblPeriod.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPeriod.setBounds(120, 250, 120, 30);
        contentPane.add(lblPeriod);

        txtPeriod = new JTextField();
        txtPeriod.setBounds(300, 250, 220, 32);
        txtPeriod.setToolTipText("Example: Period 1");
        contentPane.add(txtPeriod);

        JLabel lblTotal = new JLabel("Total Classes");
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotal.setBounds(120, 300, 120, 30);
        contentPane.add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(300, 300, 220, 32);
        contentPane.add(txtTotal);

        JLabel lblPresent = new JLabel("Present");
        lblPresent.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPresent.setBounds(120, 350, 120, 30);
        contentPane.add(lblPresent);

        txtPresent = new JTextField();
        txtPresent.setBounds(300, 350, 220, 32);
        contentPane.add(txtPresent);

        JButton btnSave = new JButton("SAVE ATTENDANCE");
        btnSave.setBounds(240, 430, 240, 42);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnSave);

        btnSave.addActionListener(e -> {
            try {
                if (txtRoll.getText().trim().equals("") ||
                    txtSubject.getText().trim().equals("") ||
                    txtDate.getText().trim().equals("") ||
                    txtPeriod.getText().trim().equals("") ||
                    txtTotal.getText().trim().equals("") ||
                    txtPresent.getText().trim().equals("")) {

                    JOptionPane.showMessageDialog(null, "Fill all fields");
                    return;
                }

                FileWriter fw = new FileWriter("src/attendance.txt", true);
                fw.write("Roll: " + txtRoll.getText().trim() + "\n");
                fw.write(txtSubject.getText().trim() + "," +
                         txtTotal.getText().trim() + "," +
                         txtPresent.getText().trim() + "," +
                         txtDate.getText().trim() + "," +
                         txtPeriod.getText().trim() + "\n");
                fw.write("----------------------\n");
                fw.close();

                JOptionPane.showMessageDialog(null, "Attendance saved successfully");

                txtRoll.setText("");
                txtDate.setText("");
                txtPeriod.setText("");
                txtTotal.setText("");
                txtPresent.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving attendance");
            }
        });
    }
}