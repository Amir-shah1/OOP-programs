package AdminPageUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class TimetableEditFrame extends JFrame {
    private JButton addBtn, removeBtn;
    private JComboBox<String> deptComboBox, semComboBox, secComboBox;
    private AdminDashboard parent;
    private JButton showTimetableBtn;
    private JTable timetableTable;
    private JScrollPane timetableScrollPane;
    private JPanel rightPanel;
    private JButton editTimetableBtn;
    private JComboBox<String> addDayCombo, addTimeSlotCombo;
    private JTextField delCourseIdField, delCourseNameField, delRoomField, delDayField, delTimeSlotField;
    private int selectedSemester;
    private String selectedSection, selectedDept;
    private JComboBox<String> addRoomCombo;
    private JComboBox<String> addCourseCombo;

    public TimetableEditFrame(AdminDashboard parent) {
        this.parent = parent;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setTitle("Edited Timetable");
        setSize(width, height);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel header = new JLabel("Edited Timetable", JLabel.CENTER);
        header.setOpaque(true);
        header.setBackground(Color.decode("#2c3e50"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 32));
        header.setBounds(0, 0, width, 100);
        add(header);

        // Clean, centered panel for inputs
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.decode("#f0f0f0"));
        int panelWidth = 500;
        int panelHeight = 320;
        formPanel.setBounds((width - panelWidth) / 2, 180, panelWidth, panelHeight);

        int labelX = 30;
        int fieldX = 180;
        int y = 30;
        int gapY = 50;
        int fieldWidth = 250;
        int fieldHeight = 30;

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        deptLabel.setBounds(labelX, y, 140, fieldHeight);
        formPanel.add(deptLabel);
        deptComboBox = new JComboBox<>(new String[]{"CS", "SE"});
        deptComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        deptComboBox.setBounds(fieldX, y, fieldWidth, fieldHeight);
        formPanel.add(deptComboBox);

        y += gapY;
        JLabel semLabel = new JLabel("Semester:");
        semLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        semLabel.setBounds(labelX, y, 140, fieldHeight);
        formPanel.add(semLabel);
        semComboBox = new JComboBox<>(new String[]{"1","2","3","4","5","6","7","8"});
        semComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        semComboBox.setBounds(fieldX, y, fieldWidth, fieldHeight);
        formPanel.add(semComboBox);

        y += gapY;
        JLabel secLabel = new JLabel("Section:");
        secLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        secLabel.setBounds(labelX, y, 140, fieldHeight);
        formPanel.add(secLabel);
        secComboBox = new JComboBox<>(new String[]{"A", "B", "C", "D"});
        secComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        secComboBox.setBounds(fieldX, y, fieldWidth, fieldHeight);
        formPanel.add(secComboBox);

        y += gapY + 10;
        showTimetableBtn = new RoundedButton("Show Timetable");
        showTimetableBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        showTimetableBtn.setBackground(Color.decode("#2980b9"));
        showTimetableBtn.setForeground(Color.WHITE);
        showTimetableBtn.setFocusPainted(false);
        showTimetableBtn.setBounds(fieldX, y, fieldWidth, 45);
        formPanel.add(showTimetableBtn);

        addBtn = new RoundedButton("Add Course");
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        addBtn.setBackground(Color.decode("#27ae60"));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setBounds(60, 220, 170, 40);
        addBtn.setVisible(false);
        formPanel.add(addBtn);

        removeBtn = new RoundedButton("Remove Course");
        removeBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        removeBtn.setBackground(Color.decode("#c0392b"));
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setFocusPainted(false);
        removeBtn.setBounds(260, 220, 170, 40);
        removeBtn.setVisible(false);
        formPanel.add(removeBtn);

        showTimetableBtn.addActionListener(e -> {
            selectedDept = deptComboBox.getSelectedItem().toString();
            selectedSemester = Integer.parseInt(semComboBox.getSelectedItem().toString());
            selectedSection = secComboBox.getSelectedItem().toString();
            showTimetablePanel();
        });

        add(formPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
            }
        });
        setVisible(true);
    }

    private void showTimetablePanel() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        // Left: Timetable
        JPanel leftPanel = new JPanel(new BorderLayout());
        timetableTable = new JTable();
        timetableScrollPane = new JScrollPane(timetableTable);
        leftPanel.add(timetableScrollPane, BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(getWidth()/2, getHeight()-100));
        // Right: Controls
        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setPreferredSize(new Dimension(getWidth()/2, getHeight()-100));
        editTimetableBtn = new RoundedButton("Edit Timetable");
        editTimetableBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        editTimetableBtn.setBackground(Color.decode("#2980b9"));
        editTimetableBtn.setForeground(Color.WHITE);
        editTimetableBtn.setFocusPainted(false);
        editTimetableBtn.setBounds(100, 100, 250, 50);
        rightPanel.add(editTimetableBtn);
        editTimetableBtn.addActionListener(e -> showEditControls());
        // Add panels to split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(getWidth()/2);
        add(splitPane, BorderLayout.CENTER);
        revalidate();
        repaint();
        loadTimetableTable();
    }

    private void loadTimetableTable() {
        String dbDept = selectedDept.equals("CS") ? "Computer Science" : "Software Engineering";
        String url = "jdbc:mysql://localhost:3306/final";
        String user = "root";
        String password = "283200";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            String sql =
                "SELECT T.day, T.slot_start, T.slot_end, T.room_name, C.course_id, C.course_name " +
                "FROM Timetable  T " +
                "JOIN Courses  C ON T.course_id = C.course_id " +
                "JOIN Departments D ON T.dept_id = D.dept_id " +
                "WHERE D.dept_name = ? " +
                "AND T.semester = ? " +
                "AND T.section = ? " +
                "ORDER BY FIELD(T.day, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'), " +
                "T.slot_start";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dbDept);
            ps.setInt(2, selectedSemester);
            ps.setString(3, selectedSection);
            ResultSet rs = ps.executeQuery();
            String[] columns = {"Day", "Start", "End", "Room", "Course ID", "Course Name"};
            java.util.List<String[]> data = new java.util.ArrayList<>();
            while (rs.next()) {
                data.add(new String[]{
                    rs.getString("day"),
                    rs.getString("slot_start"),
                    rs.getString("slot_end"),
                    rs.getString("room_name"),
                    rs.getString("course_id"),
                    rs.getString("course_name")
                });
            }
            String[][] tableData = data.toArray(new String[0][0]);
            timetableTable.setModel(new javax.swing.table.DefaultTableModel(tableData, columns));
            rs.close();
            ps.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        timetableTable.getSelectionModel().addListSelectionListener(e -> fillDeleteFieldsFromTable());
    }

    private void showEditControls() {
        rightPanel.removeAll();
        // Add Course Section
        JLabel addLabel = new JLabel("Add Course");
        addLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addLabel.setBounds(40, 20, 200, 30);
        rightPanel.add(addLabel);
        JLabel addIdLabel = new JLabel("Course:");
        addIdLabel.setBounds(40, 60, 100, 25);
        rightPanel.add(addIdLabel);
        // ComboBox for course names
        DefaultComboBoxModel<String> courseModel = new DefaultComboBoxModel<>();
        addCourseCombo = new JComboBox<>(courseModel);
        addCourseCombo.setBounds(150, 60, 180, 25);
        rightPanel.add(addCourseCombo);
        // Populate courseModel from database
        try {
            String url = "jdbc:mysql://localhost:3306/final";
            String user = "root";
            String password = "283200";
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = java.sql.DriverManager.getConnection(url, user, password);
            String sql = "SELECT MIN(course_id) as course_id, course_name FROM Courses GROUP BY course_name ORDER BY course_name ASC";
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String entry = rs.getInt("course_id") + " - " + rs.getString("course_name");
                courseModel.addElement(entry);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception ex) {
            courseModel.addElement("No courses found");
        }
        JLabel addRoomLabel = new JLabel("Room:");
        addRoomLabel.setBounds(40, 95, 100, 25);
        rightPanel.add(addRoomLabel);
        addRoomCombo = new JComboBox<>();
        addRoomCombo.setBounds(150, 95, 180, 25);
        rightPanel.add(addRoomCombo);
        JLabel addDayLabel = new JLabel("Day:");
        addDayLabel.setBounds(40, 130, 100, 25);
        rightPanel.add(addDayLabel);
        addDayCombo = new JComboBox<>(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
        addDayCombo.setBounds(150, 130, 180, 25);
        rightPanel.add(addDayCombo);
        JLabel addSlotLabel = new JLabel("Time Slot:");
        addSlotLabel.setBounds(40, 165, 100, 25);
        rightPanel.add(addSlotLabel);
        addTimeSlotCombo = new JComboBox<>(new String[]{"9:00 - 10:30", "10:30 - 12:00", "12:00 - 2:00", "2:00 - 3:30", "3:30 - 5:00"});
        addTimeSlotCombo.setBounds(150, 165, 180, 25);
        rightPanel.add(addTimeSlotCombo);
        JButton addCourseBtn = new RoundedButton("Add to Timetable");
        addCourseBtn.setBounds(150, 200, 180, 35);
        addCourseBtn.setBackground(Color.decode("#27ae60"));
        addCourseBtn.setForeground(Color.WHITE);
        addCourseBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        addCourseBtn.setFocusPainted(false);
        rightPanel.add(addCourseBtn);
        addCourseBtn.addActionListener(e -> addCourseToDB());
        // Delete Course Section
        JLabel delLabel = new JLabel("Delete Course");
        delLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        delLabel.setBounds(40, 300, 200, 30);
        rightPanel.add(delLabel);
        JLabel delIdLabel = new JLabel("Course ID:");
        delIdLabel.setBounds(40, 340, 100, 25);
        rightPanel.add(delIdLabel);
        delCourseIdField = new JTextField();
        delCourseIdField.setBounds(150, 340, 180, 25);
        rightPanel.add(delCourseIdField);
        JLabel delNameLabel = new JLabel("Name:");
        delNameLabel.setBounds(40, 375, 100, 25);
        rightPanel.add(delNameLabel);
        delCourseNameField = new JTextField();
        delCourseNameField.setBounds(150, 375, 180, 25);
        rightPanel.add(delCourseNameField);
        JLabel delRoomLabel = new JLabel("Room:");
        delRoomLabel.setBounds(40, 410, 100, 25);
        rightPanel.add(delRoomLabel);
        delRoomField = new JTextField();
        delRoomField.setBounds(150, 410, 180, 25);
        rightPanel.add(delRoomField);
        JLabel delDayLabel = new JLabel("Day:");
        delDayLabel.setBounds(40, 445, 100, 25);
        rightPanel.add(delDayLabel);
        delDayField = new JTextField();
        delDayField.setBounds(150, 445, 180, 25);
        rightPanel.add(delDayField);
        JLabel delSlotLabel = new JLabel("Time Slot:");
        delSlotLabel.setBounds(40, 480, 100, 25);
        rightPanel.add(delSlotLabel);
        delTimeSlotField = new JTextField();
        delTimeSlotField.setBounds(150, 480, 180, 25);
        rightPanel.add(delTimeSlotField);
        JButton delCourseBtn = new RoundedButton("Delete from Timetable");
        delCourseBtn.setBounds(150, 515, 180, 35);
        delCourseBtn.setBackground(Color.decode("#c0392b"));
        delCourseBtn.setForeground(Color.WHITE);
        delCourseBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        delCourseBtn.setFocusPainted(false);
        rightPanel.add(delCourseBtn);
        delCourseBtn.addActionListener(e -> deleteCourseFromDB());
        rightPanel.revalidate();
        rightPanel.repaint();
        addDayCombo.addActionListener(e -> updateAvailableRooms());
        addTimeSlotCombo.addActionListener(e -> updateAvailableRooms());
        updateAvailableRooms();
    }

    private void fillDeleteFieldsFromTable() {
        int row = timetableTable.getSelectedRow();
        if (row >= 0) {
            delCourseIdField.setText(timetableTable.getValueAt(row, 4).toString());
            delCourseNameField.setText(timetableTable.getValueAt(row, 5).toString());
            delRoomField.setText(timetableTable.getValueAt(row, 3).toString());
            delDayField.setText(timetableTable.getValueAt(row, 0).toString());
            delTimeSlotField.setText(timetableTable.getValueAt(row, 1).toString() + " - " + timetableTable.getValueAt(row, 2).toString());
        }
    }

    private void updateAvailableRooms() {
        String day = addDayCombo.getSelectedItem().toString();
        String slot = addTimeSlotCombo.getSelectedItem().toString();
        String[] times = slot.split(" - ");
        String slotStart = times[0] + ":00";
        String slotEnd = times[1] + ":00";
        String url = "jdbc:mysql://localhost:3306/final";
        String user = "root";
        String password = "283200";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            String sql =
                "SELECT room_name FROM Rooms WHERE room_name NOT IN (" +
                "SELECT room_name FROM Timetable WHERE day = ? " +
                "AND ((slot_start < ? AND slot_end > ?) OR (slot_start < ? AND slot_end > ?) OR (slot_start >= ? AND slot_end <= ?))" +
                ")";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, day);
            ps.setString(2, slotEnd);
            ps.setString(3, slotStart);
            ps.setString(4, slotEnd);
            ps.setString(5, slotStart);
            ps.setString(6, slotStart);
            ps.setString(7, slotEnd);
            ResultSet rs = ps.executeQuery();
            addRoomCombo.removeAllItems();
            while (rs.next()) {
                addRoomCombo.addItem(rs.getString("room_name"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception ex) {
            addRoomCombo.removeAllItems();
            addRoomCombo.addItem("No rooms available");
        }
    }

    private void addCourseToDB() {
        String courseEntry = (addCourseCombo.getSelectedItem() != null) ? addCourseCombo.getSelectedItem().toString() : "";
        String room = (addRoomCombo.getSelectedItem() != null) ? addRoomCombo.getSelectedItem().toString() : "";
        String day = addDayCombo.getSelectedItem().toString();
        String slot = addTimeSlotCombo.getSelectedItem().toString();
        String[] times = slot.split(" - ");
        String slotStart = times[0] + ":00";
        String slotEnd = times[1] + ":00";
        int semester = selectedSemester;
        String section = selectedSection;
        String dept = selectedDept;
        if (courseEntry.isEmpty() || room.isEmpty() || room.equals("No rooms available")) {
            JOptionPane.showMessageDialog(this, "Please fill all fields and select a valid room and course.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Extract courseId from entry
        int courseId;
        try {
            courseId = Integer.parseInt(courseEntry.split(" - ")[0].trim());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid course selection.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int deptId = dept.equals("CS") ? 1 : 2;
        String url = "jdbc:mysql://localhost:3306/final";
        String user = "root";
        String password = "283200";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = java.sql.DriverManager.getConnection(url, user, password);
            // Check for conflicts
            String conflictSQL = "SELECT COUNT(*) FROM Timetable WHERE day = ? AND ((slot_start < ? AND slot_end > ?) OR (slot_start < ? AND slot_end > ?) OR (slot_start >= ? AND slot_end <= ?)) AND room_name = ?";
            java.sql.PreparedStatement psConflict = con.prepareStatement(conflictSQL);
            psConflict.setString(1, day);
            psConflict.setString(2, slotEnd);
            psConflict.setString(3, slotStart);
            psConflict.setString(4, slotEnd);
            psConflict.setString(5, slotStart);
            psConflict.setString(6, slotStart);
            psConflict.setString(7, slotEnd);
            psConflict.setString(8, room);
            java.sql.ResultSet rs = psConflict.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            psConflict.close();
            if (count > 0) {
                JOptionPane.showMessageDialog(this, "Room is already reserved for this slot.", "Error", JOptionPane.ERROR_MESSAGE);
                con.close();
                return;
            }
            // Insert into Timetable
            String insertSQL = "INSERT INTO Timetable (day, slot_start, slot_end, room_name, course_id, section, semester, dept_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            java.sql.PreparedStatement psInsert = con.prepareStatement(insertSQL);
            psInsert.setString(1, day);
            psInsert.setString(2, slotStart);
            psInsert.setString(3, slotEnd);
            psInsert.setString(4, room);
            psInsert.setInt(5, courseId);
            psInsert.setString(6, section);
            psInsert.setInt(7, semester);
            psInsert.setInt(8, deptId);
            psInsert.executeUpdate();
            psInsert.close();
            con.close();
            JOptionPane.showMessageDialog(this, "Course added to timetable!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTimetableTable();
            updateAvailableRooms();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCourseFromDB() {
        String courseIdStr = delCourseIdField.getText().trim();
        String room = delRoomField.getText().trim();
        String day = delDayField.getText().trim();
        String slot = delTimeSlotField.getText().trim();
        String[] times = slot.split(" - ");
        String slotStart = times[0].trim();
        String slotEnd = times[1].trim();
        int semester = selectedSemester;
        String section = selectedSection;
        String dept = selectedDept;
        if (courseIdStr.isEmpty() || room.isEmpty() || day.isEmpty() || slot.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a course from the timetable to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int courseId;
        try {
            courseId = Integer.parseInt(courseIdStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Course ID must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int deptId = dept.equals("CS") ? 1 : 2;
        String url = "jdbc:mysql://localhost:3306/final";
        String user = "root";
        String password = "283200";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            String deleteSQL = "DELETE FROM Timetable WHERE day = ? AND slot_start = ? AND slot_end = ? AND room_name = ? AND course_id = ? AND section = ? AND semester = ? AND dept_id = ?";
            PreparedStatement psDelete = con.prepareStatement(deleteSQL);
            psDelete.setString(1, day);
            psDelete.setString(2, slotStart);
            psDelete.setString(3, slotEnd);
            psDelete.setString(4, room);
            psDelete.setInt(5, courseId);
            psDelete.setString(6, section);
            psDelete.setInt(7, semester);
            psDelete.setInt(8, deptId);
            int rows = psDelete.executeUpdate();
            psDelete.close();
            con.close();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Course removed from timetable!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No matching course found to delete.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            loadTimetableTable();
            updateAvailableRooms();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
} 