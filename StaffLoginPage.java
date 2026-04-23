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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffLoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StaffLoginPage frame = new StaffLoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StaffLoginPage() {
        setTitle("Staff Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("STAFF LOGIN");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitle.setBounds(560, 120, 250, 40);
        contentPane.add(lblTitle);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblUsername.setBounds(470, 250, 120, 30);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(620, 250, 250, 35);
        contentPane.add(txtUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPassword.setBounds(470, 330, 120, 30);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(620, 330, 250, 35);
        contentPane.add(txtPassword);

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(Color.BLACK);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogin.setBounds(560, 430, 180, 40);
        contentPane.add(btnLogin);

        JButton btnBack = new JButton("BACK");
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBack.setBounds(560, 500, 180, 40);
        contentPane.add(btnBack);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String u = txtUsername.getText().trim();
                String p = new String(txtPassword.getPassword()).trim();

                if (u.equals("") || p.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter username and password");
                    return;
                }

                try {
                    BufferedReader br = new BufferedReader(new FileReader("src/staff.txt"));

                    String line;
                    String staffName = "";
                    String subject = "";
                    String dept = "";
                    boolean userMatch = false;

                    while ((line = br.readLine()) != null) {

                        if (line.startsWith("Name:")) {
                            staffName = line.substring(6).trim();
                        }

                        if (line.startsWith("Subject:")) {
                            subject = line.substring(9).trim();
                        }

                        if (line.startsWith("Department:")) {
                            dept = line.substring(11).trim();
                        }

                        if (line.equals("Username: " + u)) {
                            userMatch = true;
                        }

                        if (userMatch && line.equals("Password: " + p)) {
                            JOptionPane.showMessageDialog(null, "Staff Login Success");
                            StaffDashboardPage page = new StaffDashboardPage(staffName, subject, dept);
                            page.setVisible(true);
                            br.close();
                            dispose();
                            return;
                        }

                        if (line.startsWith("----")) {
                            userMatch = false;
                        }
                    }

                    br.close();
                    JOptionPane.showMessageDialog(null, "Invalid Staff Login");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error reading staff.txt");
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPage page = new MainPage();
                page.setVisible(true);
                dispose();
            }
        });
    }
}