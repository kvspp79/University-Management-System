import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class StaffViewSubmissionsPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StaffViewSubmissionsPage frame = new StaffViewSubmissionsPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StaffViewSubmissionsPage() {
        setTitle("Student Exam Submissions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 650);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("STUDENT EXAM SUBMISSIONS");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(360, 20, 420, 35);
        contentPane.add(lblTitle);

        String[] columns = {
            "Roll", "Student", "Subject", "Exam Title", "Score", "Total", "Published"
        };

        model = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 90, 1100, 380);
        contentPane.add(scrollPane);

        JButton btnRefresh = new JButton("REFRESH");
        btnRefresh.setBounds(180, 520, 180, 40);
        styleButton(btnRefresh);
        contentPane.add(btnRefresh);

        JButton btnPublish = new JButton("PUBLISH SELECTED");
        btnPublish.setBounds(470, 520, 220, 40);
        styleButton(btnPublish);
        contentPane.add(btnPublish);

        JButton btnClose = new JButton("CLOSE");
        btnClose.setBounds(800, 520, 180, 40);
        styleButton(btnClose);
        contentPane.add(btnClose);

        loadSubmissions();

        btnRefresh.addActionListener(e -> loadSubmissions());

        btnPublish.addActionListener(e -> publishSelectedSubmission());

        btnClose.addActionListener(e -> dispose());
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
    }

    private void loadSubmissions() {
        model.setRowCount(0);

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/student_exam_submissions.txt"));

            String line;
            String roll = "";
            String student = "";
            String subject = "";
            String examTitle = "";
            String score = "";
            String total = "";
            String published = "";

            while ((line = br.readLine()) != null) {

                if (line.startsWith("Roll:")) {
                    roll = line.substring(5).trim();
                } else if (line.startsWith("Student:")) {
                    student = line.substring(8).trim();
                } else if (line.startsWith("Subject:")) {
                    subject = line.substring(8).trim();
                } else if (line.startsWith("ExamTitle:")) {
                    examTitle = line.substring(10).trim();
                } else if (line.startsWith("Score:")) {
                    score = line.substring(6).trim();
                } else if (line.startsWith("Total:")) {
                    total = line.substring(6).trim();
                } else if (line.startsWith("Published:")) {
                    published = line.substring(10).trim();
                } else if (line.startsWith("----------------------")) {
                    model.addRow(new Object[] {
                        roll, student, subject, examTitle, score, total, published
                    });

                    roll = "";
                    student = "";
                    subject = "";
                    examTitle = "";
                    score = "";
                    total = "";
                    published = "";
                }
            }

            br.close();

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No submissions found");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No submissions file found yet");
        }
    }

    private void publishSelectedSubmission() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select one submission");
            return;
        }

        String roll = model.getValueAt(row, 0).toString();
        String student = model.getValueAt(row, 1).toString();
        String subject = model.getValueAt(row, 2).toString();
        String examTitle = model.getValueAt(row, 3).toString();
        String score = model.getValueAt(row, 4).toString();
        String total = model.getValueAt(row, 5).toString();
        String published = model.getValueAt(row, 6).toString();

        if (published.equalsIgnoreCase("Yes")) {
            JOptionPane.showMessageDialog(null, "This submission is already published");
            return;
        }

        try {
            // 1) Add final published mark to exams.txt
            FileWriter fw = new FileWriter("src/exams.txt", true);
            fw.write("Teacher: PublishedByStaff\n");
            fw.write("Subject: " + subject + "\n");
            fw.write("Roll: " + roll + "\n");
            fw.write("Exam: " + examTitle + "\n");
            fw.write("Marks: " + score + "\n");
            fw.write("----------------------\n");
            fw.close();

            // 2) Update published status inside submissions file
            BufferedReader br = new BufferedReader(new FileReader("src/student_exam_submissions.txt"));
            StringBuilder allData = new StringBuilder();

            String line;
            String block = "";
            boolean blockMatched = false;
            String currentRoll = "";
            String currentExam = "";

            while ((line = br.readLine()) != null) {

                if (line.startsWith("Roll:")) {
                    currentRoll = line.substring(5).trim();
                }

                if (line.startsWith("ExamTitle:")) {
                    currentExam = line.substring(10).trim();
                }

                block += line + "\n";

                if (line.startsWith("----------------------")) {

                    if (currentRoll.equals(roll) && currentExam.equals(examTitle)) {
                        blockMatched = true;
                    } else {
                        blockMatched = false;
                    }

                    if (blockMatched) {
                        block = block.replace("Published: No", "Published: Yes");
                    }

                    allData.append(block);

                    block = "";
                    currentRoll = "";
                    currentExam = "";
                }
            }

            br.close();

            FileWriter fw2 = new FileWriter("src/student_exam_submissions.txt");
            fw2.write(allData.toString());
            fw2.close();

            JOptionPane.showMessageDialog(null,
                "Marks published successfully for\n" +
                "Student: " + student +
                "\nRoll: " + roll +
                "\nScore: " + score + "/" + total);

            loadSubmissions();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error publishing marks");
        }
    }
}