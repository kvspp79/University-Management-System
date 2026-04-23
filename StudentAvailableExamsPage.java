import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;

public class StudentAvailableExamsPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String studentName;
    private String studentRoll;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentAvailableExamsPage frame = new StudentAvailableExamsPage("Student", "UMS1001");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentAvailableExamsPage(String name, String roll) {
        this.studentName = name;
        this.studentRoll = roll;

        setTitle("Available Exams");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 120, 900, 600);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("AVAILABLE EXAMS");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setBounds(310, 25, 300, 35);
        contentPane.add(lblTitle);

        JLabel lblStudent = new JLabel("Student: " + studentName + " | Roll: " + studentRoll);
        lblStudent.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblStudent.setBounds(180, 75, 500, 30);
        contentPane.add(lblStudent);

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> examList = new JList<>(model);
        examList.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(examList);
        scrollPane.setBounds(150, 130, 580, 280);
        contentPane.add(scrollPane);

        JButton btnOpenExam = new JButton("OPEN EXAM");
        btnOpenExam.setBounds(180, 460, 180, 40);
        btnOpenExam.setBackground(Color.BLACK);
        btnOpenExam.setForeground(Color.WHITE);
        btnOpenExam.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnOpenExam);

        JButton btnRefresh = new JButton("REFRESH");
        btnRefresh.setBounds(380, 460, 150, 40);
        btnRefresh.setBackground(Color.BLACK);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnRefresh);

        JButton btnClose = new JButton("CLOSE");
        btnClose.setBounds(550, 460, 150, 40);
        btnClose.setBackground(Color.BLACK);
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnClose);

        loadExams(model);

        btnRefresh.addActionListener(e -> {
            model.clear();
            loadExams(model);
        });

        btnOpenExam.addActionListener(e -> {
            String selected = examList.getSelectedValue();

            if (selected == null || selected.equals("No exams available")) {
                JOptionPane.showMessageDialog(null, "Please select an exam");
                return;
            }

            if (selected.contains("(Submitted)")) {
                JOptionPane.showMessageDialog(null, "You already submitted this exam");
                return;
            }

            String clean = selected.replace(" (Submitted)", "");
            String[] parts = clean.split(" \\| ");
            String subject = parts[0].replace("Subject: ", "").trim();
            String examTitle = parts[1].replace("Exam: ", "").trim();

            StudentWriteExamPage page = new StudentWriteExamPage(studentName, studentRoll, subject, examTitle);
            page.setVisible(true);
        });

        btnClose.addActionListener(e -> dispose());
    }

    private void loadExams(DefaultListModel<String> model) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/questionpapers.txt"));

            String line;
            LinkedHashSet<String> uniqueExams = new LinkedHashSet<>();

            while ((line = br.readLine()) != null) {
                if (line.startsWith("END_EXAM:")) {
                    String data = line.substring(9).trim();
                    String[] parts = data.split("\\|");

                    if (parts.length == 2) {
                        String sub = parts[0].trim();
                        String exam = parts[1].trim();

                        String item = "Subject: " + sub + " | Exam: " + exam;
                        if (alreadySubmitted(studentRoll, sub, exam)) {
                            item = item + " (Submitted)";
                        }
                        uniqueExams.add(item);
                    }
                }
            }

            br.close();

            if (uniqueExams.isEmpty()) {
                model.addElement("No exams available");
            } else {
                for (String exam : uniqueExams) {
                    model.addElement(exam);
                }
            }

        } catch (Exception ex) {
            model.addElement("No exams available");
        }
    }

    private boolean alreadySubmitted(String roll, String subject, String examTitle) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/student_exam_submissions.txt"));
            String line;
            String r = "", s = "", e = "";

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Roll:")) r = line.substring(5).trim();
                else if (line.startsWith("Subject:")) s = line.substring(8).trim();
                else if (line.startsWith("ExamTitle:")) e = line.substring(10).trim();
                else if (line.startsWith("----------------------")) {
                    if (r.equals(roll) && s.equals(subject) && e.equals(examTitle)) {
                        br.close();
                        return true;
                    }
                    r = ""; s = ""; e = "";
                }
            }

            br.close();
        } catch (Exception ex) {
        }
        return false;
    }
}