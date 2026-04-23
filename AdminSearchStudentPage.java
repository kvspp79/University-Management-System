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

public class AdminSearchStudentPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtRoll;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminSearchStudentPage frame = new AdminSearchStudentPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminSearchStudentPage() {
        setTitle("Search Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 150, 700, 400);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("SEARCH STUDENT BY ROLL NUMBER");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setBounds(150, 40, 420, 30);
        contentPane.add(lblTitle);

        JLabel lblRoll = new JLabel("Roll Number");
        lblRoll.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRoll.setBounds(120, 120, 120, 30);
        contentPane.add(lblRoll);

        txtRoll = new JTextField();
        txtRoll.setBounds(260, 120, 240, 32);
        contentPane.add(txtRoll);

        JButton btnSearch = new JButton("SEARCH");
        btnSearch.setBounds(260, 210, 160, 40);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnSearch);

        btnSearch.addActionListener(e -> {
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/students.txt"));
                String line;
                String block = "";
                boolean found = false;

                while ((line = br.readLine()) != null) {
                    block += line + "\n";

                    if (line.equals("Roll: " + txtRoll.getText().trim())) {
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
                JOptionPane.showMessageDialog(null, "Student not found");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error searching student");
            }
        });
    }
}