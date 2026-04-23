import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Timer;

public class StudentWriteExamPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private String studentName;
    private String studentRoll;
    private String subject;
    private String examTitle;

    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String[]> options = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<String> selectedAnswers = new ArrayList<>();

    private int currentIndex = 0;
    private boolean submitted = false;

    private JLabel lblQuestionNo;
    private JLabel lblQuestionText;
    private JLabel lblTimer;
    private JRadioButton rdbtnA;
    private JRadioButton rdbtnB;
    private JRadioButton rdbtnC;
    private JRadioButton rdbtnD;
    private ButtonGroup group;
    private Timer timer;
    private int remainingSeconds = 35 * 60;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentWriteExamPage frame = new StudentWriteExamPage("Student", "UMS1001", "Maths", "Mid1");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentWriteExamPage(String name, String roll, String sub, String exam) {
        this.studentName = name;
        this.studentRoll = roll;
        this.subject = sub;
        this.examTitle = exam;

        if (alreadySubmitted()) {
            JOptionPane.showMessageDialog(null, "You already submitted this exam");
            dispose();
            return;
        }

        setTitle("Write Exam");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(120, 80, 1000, 680);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("WRITE EXAM - " + examTitle + " (" + subject + ")");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(150, 20, 650, 35);
        contentPane.add(lblTitle);

        JLabel lblStudent = new JLabel("Student: " + studentName + " | Roll: " + studentRoll);
        lblStudent.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblStudent.setBounds(220, 70, 500, 30);
        contentPane.add(lblStudent);

        lblTimer = new JLabel("Time Left: 35:00");
        lblTimer.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTimer.setForeground(Color.RED);
        lblTimer.setBounds(760, 70, 180, 30);
        contentPane.add(lblTimer);

        lblQuestionNo = new JLabel("Question");
        lblQuestionNo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblQuestionNo.setBounds(80, 140, 200, 30);
        contentPane.add(lblQuestionNo);

        lblQuestionText = new JLabel("");
        lblQuestionText.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblQuestionText.setBounds(80, 190, 850, 30);
        contentPane.add(lblQuestionText);

        rdbtnA = new JRadioButton("A");
        rdbtnA.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rdbtnA.setBounds(100, 270, 760, 30);
        rdbtnA.setBackground(new Color(240, 240, 240));
        contentPane.add(rdbtnA);

        rdbtnB = new JRadioButton("B");
        rdbtnB.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rdbtnB.setBounds(100, 330, 760, 30);
        rdbtnB.setBackground(new Color(240, 240, 240));
        contentPane.add(rdbtnB);

        rdbtnC = new JRadioButton("C");
        rdbtnC.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rdbtnC.setBounds(100, 390, 760, 30);
        rdbtnC.setBackground(new Color(240, 240, 240));
        contentPane.add(rdbtnC);

        rdbtnD = new JRadioButton("D");
        rdbtnD.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rdbtnD.setBounds(100, 450, 760, 30);
        rdbtnD.setBackground(new Color(240, 240, 240));
        contentPane.add(rdbtnD);

        group = new ButtonGroup();
        group.add(rdbtnA);
        group.add(rdbtnB);
        group.add(rdbtnC);
        group.add(rdbtnD);

        JButton btnPrevious = new JButton("PREVIOUS");
        btnPrevious.setBounds(170, 560, 160, 40);
        btnPrevious.setBackground(Color.BLACK);
        btnPrevious.setForeground(Color.WHITE);
        btnPrevious.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnPrevious);

        JButton btnNext = new JButton("NEXT");
        btnNext.setBounds(400, 560, 160, 40);
        btnNext.setBackground(Color.BLACK);
        btnNext.setForeground(Color.WHITE);
        btnNext.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnNext);

        JButton btnSubmit = new JButton("SUBMIT EXAM");
        btnSubmit.setBounds(630, 560, 180, 40);
        btnSubmit.setBackground(Color.BLACK);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btnSubmit);

        loadQuestions();

        if (questions.size() == 0) {
            JOptionPane.showMessageDialog(null, "No questions found for this exam");
            dispose();
            return;
        }

        for (int i = 0; i < questions.size(); i++) {
            selectedAnswers.add("");
        }

        showQuestion();
        startTimer();

        btnPrevious.addActionListener(e -> {
            saveCurrentAnswer();
            if (currentIndex > 0) {
                currentIndex--;
                showQuestion();
            }
        });

        btnNext.addActionListener(e -> {
            saveCurrentAnswer();
            if (currentIndex < questions.size() - 1) {
                currentIndex++;
                showQuestion();
            } else {
                JOptionPane.showMessageDialog(null, "This is the last question");
            }
        });

        btnSubmit.addActionListener(e -> submitExam());
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            remainingSeconds--;
            int min = remainingSeconds / 60;
            int sec = remainingSeconds % 60;
            lblTimer.setText(String.format("Time Left: %02d:%02d", min, sec));

            if (remainingSeconds <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Time is over. Exam will be submitted automatically.");
                submitExam();
            }
        });
        timer.start();
    }

    private boolean alreadySubmitted() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/student_exam_submissions.txt"));
            String line;
            String r = "", s = "", e = "";

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Roll:")) r = line.substring(5).trim();
                else if (line.startsWith("Subject:")) s = line.substring(8).trim();
                else if (line.startsWith("ExamTitle:")) e = line.substring(10).trim();
                else if (line.startsWith("----------------------")) {
                    if (r.equals(studentRoll) && s.equals(subject) && e.equals(examTitle)) {
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

    private void loadQuestions() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/questionpapers.txt"));

            String line;
            String currentSubject = "";
            String currentExam = "";
            String q = "", a = "", b = "", c = "", d = "", ans = "";

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Subject:")) {
                    currentSubject = line.substring(8).trim();
                } else if (line.startsWith("ExamTitle:")) {
                    currentExam = line.substring(10).trim();
                } else if (line.startsWith("Question:")) {
                    q = line.substring(9).trim();
                } else if (line.startsWith("A:")) {
                    a = line.substring(2).trim();
                } else if (line.startsWith("B:")) {
                    b = line.substring(2).trim();
                } else if (line.startsWith("C:")) {
                    c = line.substring(2).trim();
                } else if (line.startsWith("D:")) {
                    d = line.substring(2).trim();
                } else if (line.startsWith("Answer:")) {
                    ans = line.substring(7).trim();
                } else if (line.startsWith("----------------------")) {
                    if (currentSubject.equals(subject) && currentExam.equals(examTitle)) {
                        questions.add(q);
                        options.add(new String[]{a, b, c, d});
                        answers.add(ans);
                    }
                    q = ""; a = ""; b = ""; c = ""; d = ""; ans = "";
                } else if (line.startsWith("END_EXAM:")) {
                    currentSubject = "";
                    currentExam = "";
                }
            }

            br.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading question paper");
        }
    }

    private void showQuestion() {
        lblQuestionNo.setText("Question " + (currentIndex + 1) + " of " + questions.size());
        lblQuestionText.setText(questions.get(currentIndex));

        String[] op = options.get(currentIndex);
        rdbtnA.setText("A. " + op[0]);
        rdbtnB.setText("B. " + op[1]);
        rdbtnC.setText("C. " + op[2]);
        rdbtnD.setText("D. " + op[3]);

        group.clearSelection();
        String selected = selectedAnswers.get(currentIndex);

        if (selected.equals("A")) rdbtnA.setSelected(true);
        else if (selected.equals("B")) rdbtnB.setSelected(true);
        else if (selected.equals("C")) rdbtnC.setSelected(true);
        else if (selected.equals("D")) rdbtnD.setSelected(true);
    }

    private void saveCurrentAnswer() {
        String selected = "";

        if (rdbtnA.isSelected()) selected = "A";
        else if (rdbtnB.isSelected()) selected = "B";
        else if (rdbtnC.isSelected()) selected = "C";
        else if (rdbtnD.isSelected()) selected = "D";

        selectedAnswers.set(currentIndex, selected);
    }

    private void submitExam() {
        if (submitted) return;

        saveCurrentAnswer();

        int score = 0;
        for (int i = 0; i < answers.size(); i++) {
            if (selectedAnswers.get(i).equals(answers.get(i))) {
                score++;
            }
        }

        try {
            FileWriter fw = new FileWriter("src/student_exam_submissions.txt", true);
            fw.write("Roll: " + studentRoll + "\n");
            fw.write("Student: " + studentName + "\n");
            fw.write("Subject: " + subject + "\n");
            fw.write("ExamTitle: " + examTitle + "\n");
            fw.write("Score: " + score + "\n");
            fw.write("Total: " + questions.size() + "\n");
            fw.write("Published: No\n");
            fw.write("----------------------\n");
            fw.close();

            submitted = true;
            if (timer != null) timer.stop();

            JOptionPane.showMessageDialog(null, "Exam submitted successfully");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error saving exam submission");
        }
    }
}