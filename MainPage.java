import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MainPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainPage frame = new MainPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainPage() {
        setTitle("University Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("UNIVERSITY MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitle.setBounds(400, 120, 600, 40);
        contentPane.add(lblTitle);

        JButton btnStudent = new JButton("STUDENT LOGIN");
        btnStudent.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnStudent.setForeground(new Color(64, 0, 64));
        btnStudent.setBackground(new Color(0, 0, 0));
        btnStudent.setBounds(540, 250, 280, 40);
        contentPane.add(btnStudent);

        JButton btnStaff = new JButton("STAFF LOGIN");
        btnStaff.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnStaff.setForeground(new Color(64, 0, 64));
        btnStaff.setBackground(Color.BLACK);
        btnStaff.setBounds(540, 330, 280, 40);
        contentPane.add(btnStaff);

        JButton btnAdmin = new JButton("ADMINISTRATIVE LOGIN");
        btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnAdmin.setForeground(new Color(64, 0, 64));
        btnAdmin.setBackground(Color.BLACK);
        btnAdmin.setBounds(540, 410, 280, 40);
        contentPane.add(btnAdmin);

        btnStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentLoginPage page = new StudentLoginPage();
                page.setVisible(true);
                dispose();
            }
        });

        btnStaff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StaffLoginPage page = new StaffLoginPage();
                page.setVisible(true);
                dispose();
            }
        });

        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministrativeLoginPage page = new AdministrativeLoginPage();
                page.setVisible(true);
                dispose();
            }
        });
    }
}