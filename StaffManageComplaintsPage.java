import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class StaffManageComplaintsPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StaffManageComplaintsPage frame = new StaffManageComplaintsPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StaffManageComplaintsPage() {
        setTitle("Manage Student Complaints");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1150, 620);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("MANAGE STUDENT COMPLAINTS");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(330, 20, 420, 35);
        contentPane.add(lblTitle);

        String[] columns = {"Name", "Roll", "Complaint"};
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
        scrollPane.setBounds(40, 90, 1050, 330);
        contentPane.add(scrollPane);

        JButton btnRefresh = new JButton("REFRESH");
        style(btnRefresh);
        btnRefresh.setBounds(140, 470, 180, 40);
        contentPane.add(btnRefresh);

        JButton btnPending = new JButton("MARK PENDING");
        style(btnPending);
        btnPending.setBounds(380, 470, 180, 40);
        contentPane.add(btnPending);

        JButton btnSolved = new JButton("MARK SOLVED");
        style(btnSolved);
        btnSolved.setBounds(620, 470, 180, 40);
        contentPane.add(btnSolved);

        JButton btnClose = new JButton("CLOSE");
        style(btnClose);
        btnClose.setBounds(860, 470, 180, 40);
        contentPane.add(btnClose);

        loadComplaints();

        btnRefresh.addActionListener(e -> loadComplaints());

        btnPending.addActionListener(e -> saveComplaintStatus("Pending"));

        btnSolved.addActionListener(e -> saveComplaintStatus("Solved"));

        btnClose.addActionListener(e -> dispose());
    }

    private void style(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
    }

    private void loadComplaints() {
        model.setRowCount(0);

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/complaints.txt"));
            String line;
            String name = "";
            String roll = "";
            String complaint = "";

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    name = line.substring(5).trim();
                } else if (line.startsWith("Roll:")) {
                    roll = line.substring(5).trim();
                } else if (line.startsWith("Complaint:")) {
                    complaint = line.substring(10).trim();
                } else if (line.startsWith("----------------------")) {
                    model.addRow(new Object[]{name, roll, complaint});
                    name = "";
                    roll = "";
                    complaint = "";
                }
            }

            br.close();

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No student complaints found");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No complaints file found yet");
        }
    }

    private void saveComplaintStatus(String status) {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select one complaint");
            return;
        }

        String name = model.getValueAt(row, 0).toString();
        String roll = model.getValueAt(row, 1).toString();
        String complaint = model.getValueAt(row, 2).toString();

        String response = JOptionPane.showInputDialog(null, "Enter staff response");
        if (response == null) {
            return;
        }

        try {
            FileWriter fw = new FileWriter("src/complaint_status.txt", true);
            fw.write("Name: " + name + "\n");
            fw.write("Roll: " + roll + "\n");
            fw.write("Complaint: " + complaint + "\n");
            fw.write("Status: " + status + "\n");
            fw.write("Response: " + response + "\n");
            fw.write("Date: " + LocalDate.now().toString() + "\n");
            fw.write("----------------------\n");
            fw.close();

            JOptionPane.showMessageDialog(null, "Complaint status updated successfully");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error saving complaint status");
        }
    }
}