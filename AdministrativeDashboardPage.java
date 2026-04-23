import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AdministrativeDashboardPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdministrativeDashboardPage frame = new AdministrativeDashboardPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdministrativeDashboardPage() {
        setTitle("Administrative Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("UNIVERSITY MANAGEMENT SYSTEM - ADMIN PORTAL");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(270, 30, 820, 35);
        contentPane.add(lblTitle);

        JLabel lblSub = new JLabel("Authorized Administrative Access");
        lblSub.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSub.setBounds(500, 75, 300, 25);
        contentPane.add(lblSub);

        JButton btnAdd = new JButton("ADD NEW STUDENT");
        style(btnAdd);
        btnAdd.setBounds(120, 170, 260, 45);
        contentPane.add(btnAdd);

        JButton btnView = new JButton("VIEW ALL STUDENTS");
        style(btnView);
        btnView.setBounds(430, 170, 260, 45);
        contentPane.add(btnView);

        JButton btnSearch = new JButton("SEARCH STUDENT");
        style(btnSearch);
        btnSearch.setBounds(740, 170, 260, 45);
        contentPane.add(btnSearch);

        JButton btnDelete = new JButton("DELETE STUDENT");
        style(btnDelete);
        btnDelete.setBounds(1050, 170, 220, 45);
        contentPane.add(btnDelete);

        JButton btnPayment = new JButton("UPDATE PAYMENT STATUS");
        style(btnPayment);
        btnPayment.setBounds(300, 300, 300, 45);
        contentPane.add(btnPayment);

        JButton btnLogout = new JButton("LOGOUT");
        style(btnLogout);
        btnLogout.setBounds(760, 300, 220, 45);
        contentPane.add(btnLogout);

        btnAdd.addActionListener(e -> {
            AdminStudentEntryPage page = new AdminStudentEntryPage();
            page.setVisible(true);
        });

        btnView.addActionListener(e -> {
            ViewAllStudentsPage page = new ViewAllStudentsPage();
            page.setVisible(true);
        });

        btnSearch.addActionListener(e -> {
            AdminSearchStudentPage page = new AdminSearchStudentPage();
            page.setVisible(true);
        });

        btnDelete.addActionListener(e -> {
            AdminDeleteStudentPage page = new AdminDeleteStudentPage();
            page.setVisible(true);
        });

        btnPayment.addActionListener(e -> {
            AdminPaymentUpdatePage page = new AdminPaymentUpdatePage();
            page.setVisible(true);
        });

        btnLogout.addActionListener(e -> {
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