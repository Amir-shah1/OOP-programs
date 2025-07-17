package AdminPageUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class CourseEditFrame extends JFrame {
    private AdminDashboard parent;
    private JPanel formPanel;
    private JButton addBtn, deleteBtn, submitAddBtn, submitDeleteBtn;
    private JTextField courseIdField, courseNameField;
    private JComboBox<String> semCombo, deptCombo, delSemCombo, delDeptCombo;
    private JTextField delCourseIdField;

    public CourseEditFrame(AdminDashboard parent) {
        this.parent = parent;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setTitle("Edited Courses");
        setSize(width, height);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel header = new JLabel("Edited Courses", JLabel.CENTER);
        header.setOpaque(true);
        header.setBackground(Color.decode("#2c3e50"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 32));
        header.setBounds(0, 0, width, 100);
        add(header);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 30, 0));
        buttonPanel.setBackground(Color.decode("#f0f0f0"));
        int panelWidth = 400;
        int panelHeight = 60;
        buttonPanel.setBounds((width - panelWidth) / 2, 180, panelWidth, panelHeight);

        addBtn = new RoundedButton("Add Course");
        deleteBtn = new RoundedButton("Delete Course");
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        deleteBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        addBtn.setBackground(Color.decode("#27ae60"));
        deleteBtn.setBackground(Color.decode("#c0392b"));
        addBtn.setForeground(Color.WHITE);
        deleteBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        deleteBtn.setFocusPainted(false);
        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        add(buttonPanel);

        formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.decode("#f0f0f0"));
        int formWidth = 500;
        int formHeight = 300;
        formPanel.setBounds((width - formWidth) / 2, 270, formWidth, formHeight);
        add(formPanel);

        addBtn.addActionListener(e -> showAddCourseForm());
        deleteBtn.addActionListener(e -> showDeleteCourseForm());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
            }
        });
        setVisible(true);
    }

    private void showAddCourseForm() {
        formPanel.removeAll();
        JLabel idLabel = new JLabel("Course ID:");
        idLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        idLabel.setBounds(30, 30, 120, 30);
        formPanel.add(idLabel);
        courseIdField = new JTextField();
        courseIdField.setBounds(180, 30, 250, 30);
        formPanel.add(courseIdField);

        JLabel nameLabel = new JLabel("Course Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setBounds(30, 80, 120, 30);
        formPanel.add(nameLabel);
        courseNameField = new JTextField();
        courseNameField.setBounds(180, 80, 250, 30);
        formPanel.add(courseNameField);

        JLabel semLabel = new JLabel("Semester:");
        semLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        semLabel.setBounds(30, 130, 120, 30);
        formPanel.add(semLabel);
        semCombo = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
        semCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        semCombo.setBounds(180, 130, 250, 30);
        formPanel.add(semCombo);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        deptLabel.setBounds(30, 180, 120, 30);
        formPanel.add(deptLabel);
        deptCombo = new JComboBox<>(new String[]{"1", "2"});
        deptCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        deptCombo.setBounds(180, 180, 250, 30);
        formPanel.add(deptCombo);

        submitAddBtn = new RoundedButton("Submit");
        submitAddBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        submitAddBtn.setBackground(Color.decode("#2980b9"));
        submitAddBtn.setForeground(Color.WHITE);
        submitAddBtn.setFocusPainted(false);
        submitAddBtn.setBounds(180, 230, 120, 35);
        formPanel.add(submitAddBtn);

        formPanel.revalidate();
        formPanel.repaint();

        submitAddBtn.addActionListener(e -> addCourseToDB());
    }

    private void showDeleteCourseForm() {
        formPanel.removeAll();
        JLabel idLabel = new JLabel("Course ID:");
        idLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        idLabel.setBounds(30, 30, 120, 30);
        formPanel.add(idLabel);
        delCourseIdField = new JTextField();
        delCourseIdField.setBounds(180, 30, 250, 30);
        formPanel.add(delCourseIdField);

        JLabel semLabel = new JLabel("Semester:");
        semLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        semLabel.setBounds(30, 80, 120, 30);
        formPanel.add(semLabel);
        delSemCombo = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
        delSemCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        delSemCombo.setBounds(180, 80, 250, 30);
        formPanel.add(delSemCombo);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        deptLabel.setBounds(30, 130, 120, 30);
        formPanel.add(deptLabel);
        delDeptCombo = new JComboBox<>(new String[]{"1", "2"});
        delDeptCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        delDeptCombo.setBounds(180, 130, 250, 30);
        formPanel.add(delDeptCombo);

        submitDeleteBtn = new RoundedButton("Delete");
        submitDeleteBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        submitDeleteBtn.setBackground(Color.decode("#c0392b"));
        submitDeleteBtn.setForeground(Color.WHITE);
        submitDeleteBtn.setFocusPainted(false);
        submitDeleteBtn.setBounds(180, 180, 120, 35);
        formPanel.add(submitDeleteBtn);

        formPanel.revalidate();
        formPanel.repaint();

        submitDeleteBtn.addActionListener(e -> deleteCourseFromDB());
    }

    private void addCourseToDB() {
        String courseId = courseIdField.getText().trim();
        String courseName = courseNameField.getText().trim();
        String semester = (String) semCombo.getSelectedItem();
        String dept = (String) deptCombo.getSelectedItem();
        String dbDept = dept.equals("1") ? "Computer Science" : "Software Engineering";
        String url = "jdbc:mysql://localhost:3306/final";
        String user = "root";
        String password = "283200";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO Courses (course_id, course_name, semester, dept_id) VALUES (?, ?, ?, (SELECT dept_id FROM Departments WHERE dept_name = ?))";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, courseId);
            ps.setString(2, courseName);
            ps.setString(3, semester);
            ps.setString(4, dbDept);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add course.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            ps.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCourseFromDB() {
        String courseId = delCourseIdField.getText().trim();
        String semester = (String) delSemCombo.getSelectedItem();
        String dept = (String) delDeptCombo.getSelectedItem();
        String dbDept = dept.equals("1") ? "Computer Science" : "Software Engineering";
        String url = "jdbc:mysql://localhost:3306/final";
        String user = "root";
        String password = "283200";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            String sql = "DELETE FROM Courses WHERE course_id = ? AND semester = ? AND dept_id = (SELECT dept_id FROM Departments WHERE dept_name = ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, courseId);
            ps.setString(2, semester);
            ps.setString(3, dbDept);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Course deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No matching course found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            ps.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
} 