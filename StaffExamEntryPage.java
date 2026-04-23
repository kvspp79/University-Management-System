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

public class StaffExamEntryPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtRoll;
    private JTextField txtExam;
    private JTextField txtMarks;
    private JTextField txtSubject;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StaffExamEntryPage frame = new StaffExamEntryPage("Teacher", "Maths");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StaffExamEntryPage(String teacherName, String subjectFromStaff) {
        setTitle("Enter Exam Marks");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 150, 700, 500);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("ENTER EXAM MARKS");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(220, 30, 300, 35);
        contentPane.add(lblTitle);

        JLabel lblRoll = new JLabel("Student Roll");
        lblRoll.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRoll.setBounds(120, 110, 120, 30);
        contentPane.add(lblRoll);

        txtRoll = new JTextField();
        txtRoll.setBounds(280, 110, 220, 32);
        contentPane.add(txtRoll);

        JLabel lblSubject = new JLabel("Subject");
        lblSubject.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSubject.setBounds(120, 170, 120, 30);
        contentPane.add(lblSubject);

        txtSubject = new JTextField();
        txtSubject.setBounds(280, 170, 220, 32);
        txtSubject.setText(subjectFromStaff);
        contentPane.add(txtSubject);

        JLabel lblExam = new JLabel("Exam Name");
        lblExam.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblExam.setBounds(120, 230, 120, 30);
        contentPane.add(lblExam);

        txtExam = new JTextField();
        txtExam.setBounds(280, 230, 220, 32);
        contentPane.add(txtExam);

        JLabel lblMarks = new JLabel("Marks");
        lblMarks.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMarks.setBounds(120, 290, 120, 30);
        contentPane.add(lblMarks);

        txtMarks = new JTextField();
        txtMarks.setBounds(280, 290, 220, 32);
        contentPane.add(txtMarks);

        JButton btnSave = new JButton("SAVE MARKS");
        btnSave.setBounds(240, 370, 180, 42);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnSave);

        btnSave.addActionListener(e -> {
            try {
                if (txtRoll.getText().trim().equals("") ||
                    txtSubject.getText().trim().equals("") ||
                    txtExam.getText().trim().equals("") ||
                    txtMarks.getText().trim().equals("")) {

                    JOptionPane.showMessageDialog(null, "Fill all fields");
                    return;
                }

                FileWriter fw = new FileWriter("src/exams.txt", true);

                fw.write("Teacher: " + teacherName + "\n");
                fw.write("Subject: " + txtSubject.getText().trim() + "\n");
                fw.write("Roll: " + txtRoll.getText().trim() + "\n");
                fw.write("Exam: " + txtExam.getText().trim() + "\n");
                fw.write("Marks: " + txtMarks.getText().trim() + "\n");
                fw.write("----------------------\n");

                fw.close();

                JOptionPane.showMessageDialog(null, "Marks saved successfully");

                txtRoll.setText("");
                txtExam.setText("");
                txtMarks.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving marks");
            }
        });
    }
}