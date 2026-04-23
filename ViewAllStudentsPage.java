import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class ViewAllStudentsPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewAllStudentsPage frame = new ViewAllStudentsPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewAllStudentsPage() {
        setTitle("All Students");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBounds(20, 20, 840, 500);
        contentPane.add(scrollPane);

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/students.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                area.append(line + "\n");
            }

            br.close();

        } catch (Exception ex) {
            area.setText("No student data found");
        }
    }
}