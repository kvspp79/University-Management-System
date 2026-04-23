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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class StaffCreateExamPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtSubject;
    private JTextField txtExamTitle;
    private JTextField txtQuestion;
    private JTextField txtOptionA;
    private JTextField txtOptionB;
    private JTextField txtOptionC;
    private JTextField txtOptionD;
    private JTextField txtAnswer;
    private JLabel lblCount;

    private int questionCount = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StaffCreateExamPage frame = new StaffCreateExamPage("Maths");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StaffCreateExamPage(String subjectFromStaff) {
        setTitle("Create Exam Paper");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 720);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("CREATE MCQ QUESTION PAPER");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setBounds(280, 20, 420, 40);
        contentPane.add(lblTitle);

        JLabel lblSubject = new JLabel("Subject");
        lblSubject.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSubject.setBounds(80, 100, 120, 30);
        contentPane.add(lblSubject);

        txtSubject = new JTextField();
        txtSubject.setBounds(220, 100, 220, 32);
        txtSubject.setText(subjectFromStaff);
        contentPane.add(txtSubject);

        JLabel lblExamTitle = new JLabel("Exam Title");
        lblExamTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblExamTitle.setBounds(520, 100, 120, 30);
        contentPane.add(lblExamTitle);

        txtExamTitle = new JTextField();
        txtExamTitle.setBounds(650, 100, 220, 32);
        contentPane.add(txtExamTitle);

        lblCount = new JLabel("Questions Added: 0");
        lblCount.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCount.setBounds(390, 145, 220, 30);
        contentPane.add(lblCount);

        JLabel lblQuestion = new JLabel("Question");
        lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblQuestion.setBounds(80, 190, 120, 30);
        contentPane.add(lblQuestion);

        txtQuestion = new JTextField();
        txtQuestion.setBounds(220, 190, 650, 32);
        contentPane.add(txtQuestion);

        JLabel lblA = new JLabel("Option A");
        lblA.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblA.setBounds(80, 250, 120, 30);
        contentPane.add(lblA);

        txtOptionA = new JTextField();
        txtOptionA.setBounds(220, 250, 250, 32);
        contentPane.add(txtOptionA);

        JLabel lblB = new JLabel("Option B");
        lblB.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblB.setBounds(520, 250, 120, 30);
        contentPane.add(lblB);

        txtOptionB = new JTextField();
        txtOptionB.setBounds(650, 250, 220, 32);
        contentPane.add(txtOptionB);

        JLabel lblC = new JLabel("Option C");
        lblC.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblC.setBounds(80, 310, 120, 30);
        contentPane.add(lblC);

        txtOptionC = new JTextField();
        txtOptionC.setBounds(220, 310, 250, 32);
        contentPane.add(txtOptionC);

        JLabel lblD = new JLabel("Option D");
        lblD.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblD.setBounds(520, 310, 120, 30);
        contentPane.add(lblD);

        txtOptionD = new JTextField();
        txtOptionD.setBounds(650, 310, 220, 32);
        contentPane.add(txtOptionD);

        JLabel lblAnswer = new JLabel("Correct Answer");
        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAnswer.setBounds(80, 370, 140, 30);
        contentPane.add(lblAnswer);

        txtAnswer = new JTextField();
        txtAnswer.setBounds(220, 370, 120, 32);
        contentPane.add(txtAnswer);

        JLabel lblHint = new JLabel("Enter only A / B / C / D");
        lblHint.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblHint.setBounds(360, 370, 200, 30);
        contentPane.add(lblHint);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        infoArea.setText("How this works:\n"
                + "1. Enter Subject and Exam Title once.\n"
                + "2. Enter one MCQ question.\n"
                + "3. Click ADD QUESTION.\n"
                + "4. Repeat for any number of questions.\n"
                + "5. Click FINISH PAPER at the end.");
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(80, 450, 380, 120);
        contentPane.add(scrollPane);

        JButton btnAddQuestion = new JButton("ADD QUESTION");
        btnAddQuestion.setBounds(560, 460, 180, 40);
        btnAddQuestion.setBackground(Color.BLACK);
        btnAddQuestion.setForeground(Color.WHITE);
        btnAddQuestion.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnAddQuestion);

        JButton btnFinishPaper = new JButton("FINISH PAPER");
        btnFinishPaper.setBounds(560, 520, 180, 40);
        btnFinishPaper.setBackground(Color.BLACK);
        btnFinishPaper.setForeground(Color.WHITE);
        btnFinishPaper.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnFinishPaper);

        JButton btnClose = new JButton("CLOSE");
        btnClose.setBounds(560, 580, 180, 40);
        btnClose.setBackground(Color.BLACK);
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnClose);

        btnAddQuestion.addActionListener(e -> {
            try {
                String subject = txtSubject.getText().trim();
                String examTitle = txtExamTitle.getText().trim();
                String question = txtQuestion.getText().trim();
                String a = txtOptionA.getText().trim();
                String b = txtOptionB.getText().trim();
                String c = txtOptionC.getText().trim();
                String d = txtOptionD.getText().trim();
                String answer = txtAnswer.getText().trim().toUpperCase();

                if (subject.equals("") || examTitle.equals("") || question.equals("") ||
                    a.equals("") || b.equals("") || c.equals("") || d.equals("") || answer.equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill all fields");
                    return;
                }

                if (!(answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D"))) {
                    JOptionPane.showMessageDialog(null, "Correct answer must be A / B / C / D");
                    return;
                }

                FileWriter fw = new FileWriter("src/questionpapers.txt", true);

                fw.write("Subject: " + subject + "\n");
                fw.write("ExamTitle: " + examTitle + "\n");
                fw.write("Question: " + question + "\n");
                fw.write("A: " + a + "\n");
                fw.write("B: " + b + "\n");
                fw.write("C: " + c + "\n");
                fw.write("D: " + d + "\n");
                fw.write("Answer: " + answer + "\n");
                fw.write("----------------------\n");

                fw.close();

                questionCount++;
                lblCount.setText("Questions Added: " + questionCount);

                JOptionPane.showMessageDialog(null, "Question added successfully");

                txtQuestion.setText("");
                txtOptionA.setText("");
                txtOptionB.setText("");
                txtOptionC.setText("");
                txtOptionD.setText("");
                txtAnswer.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving question");
            }
        });

        btnFinishPaper.addActionListener(e -> {
            try {
                String subject = txtSubject.getText().trim();
                String examTitle = txtExamTitle.getText().trim();

                if (subject.equals("") || examTitle.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter subject and exam title");
                    return;
                }

                if (questionCount == 0) {
                    JOptionPane.showMessageDialog(null, "Add at least one question first");
                    return;
                }

                FileWriter fw = new FileWriter("src/questionpapers.txt", true);
                fw.write("END_EXAM: " + subject + " | " + examTitle + "\n");
                fw.write("====================================\n");
                fw.close();

                JOptionPane.showMessageDialog(null, "Exam paper finished successfully");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error finishing paper");
            }
        });

        btnClose.addActionListener(e -> dispose());
    }
}