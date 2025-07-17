package MainPageUI;

import java.sql.*;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TimetableViewer extends JFrame implements ActionListener {
    JLabel deptLabel;
    JLabel semLabel;
    JLabel secLabel;
    JButton btnSubmit;
    JComboBox<String> secCombo;
    JComboBox<String> semCombo;
    JComboBox<String> deptCombo;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();
    public TimetableViewer(MainFrame ref)
    {
        ref.setVisible(false);
        this.setTitle("Find my TimeTable");
        this.setSize(width, height);
        this.setLayout(null);
        this.addcomp();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
            ref.setVisible(true);
        }
    });
    
    this.setVisible(true);
    }
    public void addcomp() {
    // light gray background
    this.getContentPane().setBackground(Color.decode("#f0f0f0"));

    // HEADER
    // HEADER
    ImageIcon ob = new ImageIcon("src/images/sc.png");
    // get the raw image
    Image rawImage = ob.getImage();
    // scale it
    Image scaledImage = rawImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

    // create new ImageIcon with the scaled image
    ob = new ImageIcon(scaledImage);
    JLabel header = new JLabel("FIND MY TIMETABLE",ob, JLabel.CENTER);
    header.setBounds(0, 0, width, 80);
    header.setBackground(Color.decode("#2c3e50"));
    header.setForeground(Color.WHITE);
    header.setOpaque(true);
    header.setFont(new Font("SansSerif", Font.BOLD, 24));
    this.add(header);

    // LOGO
    ImageIcon logoIcon = new ImageIcon("src/images/mono.png");
    JLabel logo = new JLabel(logoIcon);
    logo.setBounds(width / 2 - 200, 100, 400, 120);
    logo.setOpaque(false);
    
    logo.setOpaque(true);
    this.add(logo);

    int startY = 300;
    int gapY = 60;

    // DEPARTMENT
    deptLabel = new JLabel("Department:");
    deptLabel.setBounds((width - 310) / 2, startY, 150, 30);
    deptLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
    this.add(deptLabel);

    String[] departments = {"Computer Science", "Software Engineering"};
    deptCombo = new JComboBox<>(departments);
    deptCombo.setBounds((width - 310) / 2 + 160, startY, 200, 30);
    this.add(deptCombo);

    // SEMESTER
    semLabel = new JLabel("Semester:");
    semLabel.setBounds((width - 310) / 2, startY + gapY, 150, 30);
    semLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
    this.add(semLabel);

    String[] semesters = {"1", "2", "3", "4", "5", "6", "7", "8"};
    semCombo = new JComboBox<>(semesters);
    semCombo.setBounds((width - 310) / 2 + 160, startY + gapY, 200, 30);
    this.add(semCombo);

    // SECTION
    secLabel = new JLabel("Section:");
    secLabel.setBounds((width - 310) / 2, startY + 2 * gapY, 150, 30);
    secLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
    this.add(secLabel);

    String[] sections = {"A", "B", "C"};
    secCombo = new JComboBox<>(sections);
    secCombo.setBounds((width - 310) / 2 + 160, startY + 2 * gapY, 200, 30);
    this.add(secCombo);

    // OPTIONAL: A submit button
    btnSubmit = new RoundedButton("Submit");
    btnSubmit.setBounds((width - 150 ) / 2, startY + 3 * gapY + 20, 150, 40);
    btnSubmit.setBackground(Color.decode("#2980b9"));
    btnSubmit.setForeground(Color.WHITE);
    btnSubmit.setFont(new Font("SansSerif", Font.BOLD, 16));
    btnSubmit.setFocusPainted(false);
    btnSubmit.setActionCommand("Submit");

// keep hooking it
    btnSubmit.addActionListener(this);
    this.add(btnSubmit);
}
@Override
    public void actionPerformed(ActionEvent e) {
        String op=e.getActionCommand();
        if (op.compareTo("Submit")==0) {
            // collect user selections
            String dept = deptCombo.getSelectedItem().toString();
            int semester = Integer.parseInt(semCombo.getSelectedItem().toString());
            String section = secCombo.getSelectedItem().toString();

            // JDBC
            String url = "jdbc:mysql://localhost:3306/final";
            String user = "root";
            String password = "283200";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);

                String sql = """
                        SELECT T.day, T.slot_start, T.slot_end, T.room_name, C.course_name
                        FROM Timetable  T
                        JOIN Courses  C ON T.course_id = C.course_id
                        JOIN Departments D ON T.dept_id = D.dept_id
                        WHERE D.dept_name = ?
                          AND T.semester = ?
                          AND T.section = ?
                        ORDER BY FIELD(T.day, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'),
                                 T.slot_start
                        """;

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, dept);
                ps.setInt(2, semester);
                ps.setString(3, section);

                ResultSet rs = ps.executeQuery();

                // put in a JTable
                String[] columns = {"Day", "Start", "End", "Room", "Course"};
                java.util.List<String[]> data = new java.util.ArrayList<>();

                while (rs.next()) {
                    data.add(new String[]{
                        rs.getString("day"),
                        rs.getString("slot_start"),
                        rs.getString("slot_end"),
                        rs.getString("room_name"),
                        rs.getString("course_name")
                    });
                }

                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No timetable found for these selections.",
                            "Timetable", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String[][] tableData = data.toArray(new String[0][0]);
                    JTable table = new JTable(tableData, columns);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setPreferredSize(new java.awt.Dimension(600, 300));
                    JOptionPane.showMessageDialog(this, scrollPane, "My Timetable",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                rs.close();
                ps.close();
                con.close();

            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
}
