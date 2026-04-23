import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class StaffDashboardPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String staffName;
    private String subject;
    private String department;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StaffDashboardPage frame = new StaffDashboardPage("Anand", "Maths", "CSE");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StaffDashboardPage(String name, String subjectName, String dept) {
        this.staffName = name;
        this.subject = subjectName;
        this.department = dept;

        setTitle("Staff Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome " + staffName + " | Subject: " + subject + " | Department: " + department);
        lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblWelcome.setBounds(170, 30, 980, 35);
        contentPane.add(lblWelcome);

        JButton btnViewStudent = new JButton("VIEW STUDENT DETAILS");
        style(btnViewStudent);
        btnViewStudent.setBounds(70, 130, 260, 45);
        contentPane.add(btnViewStudent);

        JButton btnAttendance = new JButton("GIVE ATTENDANCE");
        style(btnAttendance);
        btnAttendance.setBounds(390, 130, 230, 45);
        contentPane.add(btnAttendance);

        JButton btnExamMarks = new JButton("ENTER EXAM MARKS");
        style(btnExamMarks);
        btnExamMarks.setBounds(680, 130, 230, 45);
        contentPane.add(btnExamMarks);

        JButton btnComplaint = new JButton("RAISE STUDENT COMPLAINT");
        style(btnComplaint);
        btnComplaint.setBounds(970, 130, 300, 45);
        contentPane.add(btnComplaint);

        JButton btnCreatePaper = new JButton("CREATE EXAM PAPER");
        style(btnCreatePaper);
        btnCreatePaper.setBounds(90, 280, 260, 45);
        contentPane.add(btnCreatePaper);

        JButton btnSubmissions = new JButton("VIEW EXAM SUBMISSIONS");
        style(btnSubmissions);
        btnSubmissions.setBounds(410, 280, 300, 45);
        contentPane.add(btnSubmissions);

        JButton btnAnalytics = new JButton("VIEW STUDENT ANALYTICS");
        style(btnAnalytics);
        btnAnalytics.setBounds(770, 280, 320, 45);
        contentPane.add(btnAnalytics);

        JButton btnManageComplaints = new JButton("MANAGE COMPLAINTS");
        style(btnManageComplaints);
        btnManageComplaints.setBounds(300, 410, 280, 45);
        contentPane.add(btnManageComplaints);

        JButton btnAllStudents = new JButton("VIEW ALL STUDENTS");
        style(btnAllStudents);
        btnAllStudents.setBounds(760, 410, 280, 45);
        contentPane.add(btnAllStudents);

        JButton btnLogout = new JButton("LOGOUT");
        style(btnLogout);
        btnLogout.setBounds(560, 560, 220, 45);
        contentPane.add(btnLogout);

        btnViewStudent.addActionListener(e -> {
            StaffStudentLookupPage page = new StaffStudentLookupPage();
            page.setVisible(true);
        });

        btnAttendance.addActionListener(e -> {
            StaffAttendanceEntryPage page = new StaffAttendanceEntryPage(subject);
            page.setVisible(true);
        });

        btnExamMarks.addActionListener(e -> {
            StaffExamEntryPage page = new StaffExamEntryPage(staffName, subject);
            page.setVisible(true);
        });

        btnComplaint.addActionListener(e -> {
            StaffComplaintPage page = new StaffComplaintPage(staffName);
            page.setVisible(true);
        });

        btnCreatePaper.addActionListener(e -> {
            StaffCreateExamPage page = new StaffCreateExamPage(subject);
            page.setVisible(true);
        });

        btnSubmissions.addActionListener(e -> {
            StaffViewSubmissionsPage page = new StaffViewSubmissionsPage();
            page.setVisible(true);
        });

        btnAnalytics.addActionListener(e -> {
            StaffAnalyticsPage page = new StaffAnalyticsPage();
            page.setVisible(true);
        });

        btnManageComplaints.addActionListener(e -> {
            StaffManageComplaintsPage page = new StaffManageComplaintsPage();
            page.setVisible(true);
        });

        btnAllStudents.addActionListener(e -> {
            ViewAllStudentsPage page = new ViewAllStudentsPage();
            page.setVisible(true);
        });

        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Logged out successfully");
            MainPage page = new MainPage();
            page.setVisible(true);
            dispose();
        });
    }

    private void style(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
    }
}