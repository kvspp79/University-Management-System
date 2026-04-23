import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class StaffAnalyticsPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtRoll;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StaffAnalyticsPage frame = new StaffAnalyticsPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StaffAnalyticsPage() {
        setTitle("Student Analytics");
        setBounds(200, 150, 800, 500);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("STUDENT ANALYTICS");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(250, 30, 300, 35);
        contentPane.add(lblTitle);

        JLabel lblRoll = new JLabel("Enter Roll");
        lblRoll.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRoll.setBounds(120, 100, 100, 30);
        contentPane.add(lblRoll);

        txtRoll = new JTextField();
        txtRoll.setBounds(250, 100, 220, 32);
        contentPane.add(txtRoll);

        JButton btnLoad = new JButton("LOAD ANALYTICS");
        btnLoad.setBounds(520, 100, 180, 35);
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(btnLoad);

        JLabel lblAttendance = new JLabel("Attendance Percentage");
        lblAttendance.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAttendance.setBounds(120, 200, 220, 30);
        contentPane.add(lblAttendance);

        JProgressBar attendanceBar = new JProgressBar();
        attendanceBar.setBounds(350, 200, 250, 30);
        attendanceBar.setStringPainted(true);
        contentPane.add(attendanceBar);

        JLabel lblMarks = new JLabel("Average Marks Percentage");
        lblMarks.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMarks.setBounds(120, 280, 240, 30);
        contentPane.add(lblMarks);

        JProgressBar marksBar = new JProgressBar();
        marksBar.setBounds(350, 280, 250, 30);
        marksBar.setStringPainted(true);
        contentPane.add(marksBar);

        btnLoad.addActionListener(e -> {
            try {
                String roll = txtRoll.getText().trim();
                if (roll.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter roll number");
                    return;
                }

                int totalClasses = 0;
                int totalPresent = 0;

                BufferedReader br1 = new BufferedReader(new FileReader("src/attendance.txt"));
                String line;
                boolean found = false;

                while ((line = br1.readLine()) != null) {
                    if (line.equals("Roll: " + roll)) {
                        found = true;
                        continue;
                    }

                    if (found && line.contains(",")) {
                        String[] p = line.split(",");
                        totalClasses += Integer.parseInt(p[1]);
                        totalPresent += Integer.parseInt(p[2]);
                    }

                    if (line.startsWith("----")) {
                        found = false;
                    }
                }

                br1.close();

                int attendancePercent = 0;
                if (totalClasses > 0) {
                    attendancePercent = (int)((totalPresent * 100.0) / totalClasses);
                }

                double totalMarks = 0;
                int markCount = 0;

                BufferedReader br2 = new BufferedReader(new FileReader("src/exams.txt"));
                boolean foundExam = false;

                while ((line = br2.readLine()) != null) {
                    if (line.equals("Roll: " + roll)) {
                        foundExam = true;
                    }

                    if (foundExam && line.startsWith("Marks:")) {
                        totalMarks += Double.parseDouble(line.substring(6).trim());
                        markCount++;
                    }

                    if (line.startsWith("----")) {
                        foundExam = false;
                    }
                }

                br2.close();

                int marksPercent = 0;
                if (markCount > 0) {
                    marksPercent = (int)(totalMarks / markCount);
                }

                attendanceBar.setValue(attendancePercent);
                attendanceBar.setString(attendancePercent + "%");

                marksBar.setValue(marksPercent);
                marksBar.setString(marksPercent + "%");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error loading analytics");
            }
        });
    }
}