import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

public class StudentMarksGraphPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public StudentMarksGraphPage(String studentRoll, String studentName) {
        setTitle("Marks Graph");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(180, 120, 800, 560);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(240, 240, 240));

        JLabel lblTitle = new JLabel("SUBJECT MARKS GRAPH - " + studentName);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setBounds(140, 20, 520, 30);
        mainPanel.add(lblTitle);

        ArrayList<String> subjects = new ArrayList<>();
        ArrayList<Integer> marks = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/exams.txt"));
            String line;
            boolean found = false;
            String subject = "";

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Subject:")) {
                    subject = line.substring(8).trim();
                }

                if (line.equals("Roll: " + studentRoll)) {
                    found = true;
                }

                if (found && line.startsWith("Marks:")) {
                    int mark = Integer.parseInt(line.substring(6).trim());
                    subjects.add(subject);
                    marks.add(mark);
                }

                if (line.startsWith("----")) {
                    found = false;
                }
            }

            br.close();
        } catch (Exception ex) {
        }

        int y = 80;

        if (subjects.size() == 0) {
            JLabel lblNoData = new JLabel("No published marks found");
            lblNoData.setFont(new Font("Tahoma", Font.BOLD, 18));
            lblNoData.setBounds(260, 120, 250, 30);
            mainPanel.add(lblNoData);
        } else {
            for (int i = 0; i < subjects.size(); i++) {
                JLabel lblSub = new JLabel(subjects.get(i));
                lblSub.setFont(new Font("Tahoma", Font.BOLD, 16));
                lblSub.setBounds(70, y, 180, 30);
                mainPanel.add(lblSub);

                JProgressBar bar = new JProgressBar();
                bar.setBounds(260, y, 400, 30);
                bar.setMaximum(100);
                bar.setValue(marks.get(i));
                bar.setStringPainted(true);
                bar.setString(marks.get(i) + "%");
                mainPanel.add(bar);

                y += 60;
            }
        }

        mainPanel.setPreferredSize(new java.awt.Dimension(740, Math.max(500, y + 40)));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        setContentPane(scrollPane);
    }
}