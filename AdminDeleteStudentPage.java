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
import javax.swing.JTextField;
import javax.swing.JButton;

public class AdminDeleteStudentPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtRoll;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminDeleteStudentPage frame = new AdminDeleteStudentPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminDeleteStudentPage() {
        setTitle("Delete Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 150, 700, 400);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("DELETE STUDENT BY ROLL NUMBER");
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

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBounds(260, 210, 160, 40);
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnDelete);

        btnDelete.addActionListener(e -> {
            try {
                if (txtRoll.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter roll number");
                    return;
                }

                BufferedReader br = new BufferedReader(new FileReader("src/students.txt"));
                StringBuilder newData = new StringBuilder();

                String line;
                String block = "";
                boolean found = false;
                boolean deleteBlock = false;

                while ((line = br.readLine()) != null) {
                    block += line + "\n";

                    if (line.equals("Roll: " + txtRoll.getText().trim())) {
                        deleteBlock = true;
                        found = true;
                    }

                    if (line.startsWith("----")) {
                        if (!deleteBlock) {
                            newData.append(block);
                        }
                        block = "";
                        deleteBlock = false;
                    }
                }

                br.close();

                FileWriter fw = new FileWriter("src/students.txt");
                fw.write(newData.toString());
                fw.close();

                if (found) {
                    JOptionPane.showMessageDialog(null, "Student deleted successfully");
                    txtRoll.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Student not found");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error deleting student");
            }
        });
    }
}