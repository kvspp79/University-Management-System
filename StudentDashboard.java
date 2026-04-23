import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class StudentDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String studentName;
    private String studentRoll;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentDashboard frame = new StudentDashboard("Student", "UMS1001");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentDashboard(String name, String roll) {
        this.studentName = name;
        this.studentRoll = roll;

        setTitle("Student Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome " + studentName + " (" + studentRoll + ")");
        lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblWelcome.setBounds(320, 30, 750, 35);
        contentPane.add(lblWelcome);

        JButton btnProfile = new JButton("PROFILE");
        styleButton(btnProfile);
        btnProfile.setBounds(80, 130, 220, 45);
        contentPane.add(btnProfile);

        JButton btnPayments = new JButton("PAYMENTS");
        styleButton(btnPayments);
        btnPayments.setBounds(380, 130, 220, 45);
        contentPane.add(btnPayments);

        JButton btnComplaints = new JButton("COMPLAINTS");
        styleButton(btnComplaints);
        btnComplaints.setBounds(680, 130, 220, 45);
        contentPane.add(btnComplaints);

        JButton btnAttendance = new JButton("CLASS ATTENDANCE");
        styleButton(btnAttendance);
        btnAttendance.setBounds(980, 130, 220, 45);
        contentPane.add(btnAttendance);

        JButton btnMarks = new JButton("MARKS");
        styleButton(btnMarks);
        btnMarks.setBounds(80, 260, 220, 45);
        contentPane.add(btnMarks);

        JButton btnAwards = new JButton("AWARDS");
        styleButton(btnAwards);
        btnAwards.setBounds(380, 260, 220, 45);
        contentPane.add(btnAwards);

        JButton btnTimetable = new JButton("TIMETABLE");
        styleButton(btnTimetable);
        btnTimetable.setBounds(680, 260, 220, 45);
        contentPane.add(btnTimetable);

        JButton btnWriteExam = new JButton("WRITE EXAM");
        styleButton(btnWriteExam);
        btnWriteExam.setBounds(980, 260, 220, 45);
        contentPane.add(btnWriteExam);

        JButton btnLogout = new JButton("LOGOUT");
        styleButton(btnLogout);
        btnLogout.setBounds(560, 390, 220, 45);
        contentPane.add(btnLogout);

        btnProfile.addActionListener(e -> showProfile());
        btnPayments.addActionListener(e -> showPayments());
        btnComplaints.addActionListener(e -> showComplaints());
        btnAttendance.addActionListener(e -> showAttendance());
        btnMarks.addActionListener(e -> showMarks());
        btnAwards.addActionListener(e -> showAwards());
        btnTimetable.addActionListener(e -> showTimetable());

        btnWriteExam.addActionListener(e -> {
            StudentAvailableExamsPage page = new StudentAvailableExamsPage(studentName, studentRoll);
            page.setVisible(true);
        });

        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Logged out successfully");
            MainPage page = new MainPage();
            page.setVisible(true);
            dispose();
        });
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
    }

    private void showProfile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/students.txt"));
            String line;
            String block = "";
            boolean found = false;

            while ((line = br.readLine()) != null) {
                block += line + "\n";

                if (line.equals("Roll: " + studentRoll)) {
                    found = true;
                }

                if (line.startsWith("----")) {
                    if (found) {
                        JOptionPane.showMessageDialog(null, block);
                        br.close();
                        return;
                    }
                    block = "";
                    found = false;
                }
            }

            br.close();
            JOptionPane.showMessageDialog(null, "Profile not found");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading profile");
        }
    }

    private void showPayments() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/payment_records.txt"));
            String line;
            boolean found = false;
            String totalFees = "";
            String paid = "";
            String balance = "";
            String status = "";
            String date = "";

            while ((line = br.readLine()) != null) {
                if (line.equals("Roll: " + studentRoll)) {
                    found = true;
                }

                if (found && line.startsWith("TotalFees:")) totalFees = line.substring(10).trim();
                if (found && line.startsWith("PaidAmount:")) paid = line.substring(11).trim();
                if (found && line.startsWith("Balance:")) balance = line.substring(8).trim();
                if (found && line.startsWith("Status:")) status = line.substring(7).trim();
                if (found && line.startsWith("Date:")) date = line.substring(5).trim();

                if (line.startsWith("----")) {
                    if (found) break;
                    found = false;
                }
            }

            br.close();

            if (!found) {
                BufferedReader br2 = new BufferedReader(new FileReader("src/students.txt"));
                String l2;
                String fees = "";
                boolean f2 = false;

                while ((l2 = br2.readLine()) != null) {
                    if (l2.equals("Roll: " + studentRoll)) f2 = true;
                    if (f2 && l2.startsWith("Fees:")) fees = l2.substring(5).trim();
                    if (l2.startsWith("----")) {
                        if (f2) break;
                        f2 = false;
                    }
                }
                br2.close();

                JOptionPane.showMessageDialog(null,
                    "Student: " + studentName +
                    "\nRoll: " + studentRoll +
                    "\nTotal Fees: " + fees +
                    "\nStatus: Pending");
                return;
            }

            JOptionPane.showMessageDialog(null,
                "Student: " + studentName +
                "\nRoll: " + studentRoll +
                "\nTotal Fees: " + totalFees +
                "\nPaid Amount: " + paid +
                "\nBalance: " + balance +
                "\nStatus: " + status +
                "\nUpdated On: " + date);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Payment details not found");
        }
    }

    private void showComplaints() {
        JFrame f = new JFrame("Complaints");
        f.setBounds(220, 120, 760, 520);
        f.getContentPane().setLayout(null);

        JLabel lbl = new JLabel("Enter Complaint");
        lbl.setBounds(30, 20, 200, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 18));
        f.getContentPane().add(lbl);

        JTextArea area = new JTextArea();
        JScrollPane sp = new JScrollPane(area);
        sp.setBounds(30, 70, 680, 220);
        f.getContentPane().add(sp);

        JButton btnSave = new JButton("SAVE COMPLAINT");
        btnSave.setBounds(90, 340, 180, 35);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
        f.getContentPane().add(btnSave);

        JButton btnStatus = new JButton("VIEW MY COMPLAINT STATUS");
        btnStatus.setBounds(300, 340, 240, 35);
        btnStatus.setBackground(Color.BLACK);
        btnStatus.setForeground(Color.WHITE);
        btnStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
        f.getContentPane().add(btnStatus);

        JButton btnClose = new JButton("CLOSE");
        btnClose.setBounds(570, 340, 120, 35);
        btnClose.setBackground(Color.BLACK);
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 14));
        f.getContentPane().add(btnClose);

        btnSave.addActionListener(e -> {
            try {
                if (area.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter complaint");
                    return;
                }

                FileWriter fw = new FileWriter("src/complaints.txt", true);
                fw.write("Name: " + studentName + "\n");
                fw.write("Roll: " + studentRoll + "\n");
                fw.write("Complaint: " + area.getText().trim() + "\n");
                fw.write("----------------------\n");
                fw.close();

                JOptionPane.showMessageDialog(null, "Complaint saved successfully");
                area.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving complaint");
            }
        });

        btnStatus.addActionListener(e -> showComplaintStatus());

        btnClose.addActionListener(e -> f.dispose());

        f.setVisible(true);
    }

    private void showComplaintStatus() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/complaint_status.txt"));
            String line;
            String block = "";
            boolean found = false;
            StringBuilder result = new StringBuilder();

            while ((line = br.readLine()) != null) {
                block += line + "\n";

                if (line.equals("Roll: " + studentRoll)) {
                    found = true;
                }

                if (line.startsWith("----")) {
                    if (found) {
                        result.append(block).append("\n");
                    }
                    block = "";
                    found = false;
                }
            }

            br.close();

            if (result.length() == 0) {
                JOptionPane.showMessageDialog(null, "No complaint status found yet");
            } else {
                JTextArea area = new JTextArea(result.toString());
                area.setEditable(false);
                JScrollPane sp = new JScrollPane(area);
                sp.setPreferredSize(new java.awt.Dimension(650, 350));
                JOptionPane.showMessageDialog(null, sp, "Complaint Status", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No complaint status found yet");
        }
    }

    private void showAttendance() {
        JFrame f = new JFrame("Attendance");
        f.setBounds(180, 120, 900, 420);
        f.getContentPane().setLayout(null);

        String[] columns = {"Subject", "Total", "Present", "Percentage"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 840, 300);
        f.getContentPane().add(scrollPane);

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/attendance.txt"));
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {

                if (line.equals("Roll: " + studentRoll)) {
                    found = true;
                    continue;
                }

                if (found && line.contains(",")) {
                    String[] p = line.split(",");
                    int total = Integer.parseInt(p[1]);
                    int present = Integer.parseInt(p[2]);
                    double percentage = (present * 100.0) / total;

                    model.addRow(new Object[] {
                        p[0],
                        total,
                        present,
                        String.format("%.2f%%", percentage)
                    });
                }

                if (line.startsWith("----")) {
                    found = false;
                }
            }

            br.close();

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No attendance found for this student");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading attendance");
        }

        f.setVisible(true);
    }

    private void showMarks() {
        JFrame f = new JFrame("Marks");
        f.setBounds(220, 120, 780, 470);
        f.getContentPane().setLayout(null);

        String[] columns = {"Exam", "Marks", "Subject"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 720, 280);
        f.getContentPane().add(scrollPane);

        JButton btnGraph = new JButton("VIEW GRAPH");
        btnGraph.setBounds(280, 340, 180, 35);
        btnGraph.setBackground(Color.BLACK);
        btnGraph.setForeground(Color.WHITE);
        btnGraph.setFont(new Font("Tahoma", Font.BOLD, 14));
        f.getContentPane().add(btnGraph);

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/exams.txt"));
            String line;
            boolean found = false;
            String examName = "";
            String subject = "";

            while ((line = br.readLine()) != null) {

                if (line.startsWith("Exam:")) {
                    examName = line.substring(5).trim();
                }

                if (line.startsWith("Subject:")) {
                    subject = line.substring(8).trim();
                }

                if (line.equals("Roll: " + studentRoll)) {
                    found = true;
                }

                if (found && line.startsWith("Marks:")) {
                    String marks = line.substring(6).trim();
                    model.addRow(new Object[] { examName, marks, subject });
                }

                if (line.startsWith("----")) {
                    found = false;
                }
            }

            br.close();

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No published marks found for this student");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading marks");
        }

        btnGraph.addActionListener(e -> {
            StudentMarksGraphPage page = new StudentMarksGraphPage(studentRoll, studentName);
            page.setVisible(true);
        });

        f.setVisible(true);
    }

    private void showAwards() {
        String awardText;

        if (studentRoll.hashCode() % 3 == 0) {
            awardText = "Best Attendance Award\nCoding Club Participation\nSeminar Excellence";
        } else if (studentRoll.hashCode() % 3 == 1) {
            awardText = "Sports Participation\nClass Representative\nDiscipline Award";
        } else {
            awardText = "Innovation Award\nHackathon Participation\nAcademic Improvement Award";
        }

        JOptionPane.showMessageDialog(null,
            "Awards for " + studentName + ":\n\n" + awardText);
    }

    private void showTimetable() {
        JFrame f = new JFrame("Timetable");
        f.setBounds(200, 150, 950, 420);
        f.getContentPane().setLayout(null);

        String[] columns = {"Day", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 890, 300);
        f.getContentPane().add(scrollPane);

        if (studentRoll.hashCode() % 2 == 0) {
            model.addRow(new Object[]{"Monday", "Maths", "DSA", "Physics", "English", "OOP"});
            model.addRow(new Object[]{"Tuesday", "DSA", "Maths", "Chemistry", "OOP", "Lab"});
            model.addRow(new Object[]{"Wednesday", "Physics", "English", "DSA", "Maths", "Library"});
            model.addRow(new Object[]{"Thursday", "OOP", "Lab", "Maths", "Physics", "Sports"});
            model.addRow(new Object[]{"Friday", "English", "DSA", "Maths", "OOP", "Seminar"});
        } else {
            model.addRow(new Object[]{"Monday", "OOP", "Maths", "English", "Physics", "DSA"});
            model.addRow(new Object[]{"Tuesday", "Maths", "Lab", "OOP", "DSA", "Chemistry"});
            model.addRow(new Object[]{"Wednesday", "English", "Physics", "Maths", "Library", "DSA"});
            model.addRow(new Object[]{"Thursday", "DSA", "OOP", "Sports", "Maths", "Lab"});
            model.addRow(new Object[]{"Friday", "Physics", "Maths", "Seminar", "English", "OOP"});
        }

        f.setVisible(true);
    }
}