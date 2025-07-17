package AdminPageUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoomReservationFrame extends JFrame {
    private AdminDashboard parent;
    private JComboBox<String> dayCombo, courseCombo, timeSlotCombo;
    private JTextField roomField;
    private JButton reserveBtn;

    public RoomReservationFrame(AdminDashboard parent) {
        this.parent = parent;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setTitle("Reserve a Room");
        setSize(width, height);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel header = new JLabel("Reserve a Room", JLabel.CENTER);
        header.setOpaque(true);
        header.setBackground(Color.decode("#2c3e50"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 32));
        header.setBounds(0, 0, width, 100);
        add(header);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.decode("#f0f0f0"));
        int panelWidth = 500;
        int panelHeight = 350;
        formPanel.setBounds((width - panelWidth) / 2, 180, panelWidth, panelHeight);

        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        dayLabel.setBounds(30, 30, 120, 30);
        formPanel.add(dayLabel);
        dayCombo = new JComboBox<>(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
        dayCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        dayCombo.setBounds(180, 30, 250, 30);
        formPanel.add(dayCombo);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        courseLabel.setBounds(30, 80, 120, 30);
        formPanel.add(courseLabel);
        // Fetch courses from database
        DefaultComboBoxModel<String> courseModel = new DefaultComboBoxModel<>();
        try {
            String url = "jdbc:mysql://localhost:3306/final";
            String user = "root";
            String password = "283200";
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = java.sql.DriverManager.getConnection(url, user, password);
            String sql = "SELECT DISTINCT course_name FROM Courses ORDER BY course_name ASC";
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courseModel.addElement(rs.getString("course_name"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception ex) {
            courseModel.addElement("No courses found");
        }
        courseCombo = new JComboBox<>(courseModel);
        courseCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        courseCombo.setBounds(180, 80, 250, 30);
        formPanel.add(courseCombo);

        JLabel roomLabel = new JLabel("Room No:");
        roomLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        roomLabel.setBounds(30, 130, 120, 30);
        formPanel.add(roomLabel);
        // Room ComboBox for available rooms
        DefaultComboBoxModel<String> roomModel = new DefaultComboBoxModel<>();
        JComboBox<String> roomCombo = new JComboBox<>(roomModel);
        roomCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        roomCombo.setBounds(180, 130, 250, 30);
        formPanel.add(roomCombo);

        JLabel slotLabel = new JLabel("Time Slot:");
        slotLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        slotLabel.setBounds(30, 180, 120, 30);
        formPanel.add(slotLabel);
        String[] timeSlots = {
            "9:00 - 10:30",
            "10:30 - 12:00",
            "12:00 - 2:00",
            "2:00 - 3:30",
            "3:30 - 5:00"
        };
        timeSlotCombo = new JComboBox<>(timeSlots);
        timeSlotCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        timeSlotCombo.setBounds(180, 180, 250, 30);
        formPanel.add(timeSlotCombo);

        reserveBtn = new RoundedButton("Reserve");
        reserveBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        reserveBtn.setBackground(Color.decode("#2980b9"));
        reserveBtn.setForeground(Color.WHITE);
        reserveBtn.setFocusPainted(false);
        reserveBtn.setBounds(180, 240, 120, 40);
        formPanel.add(reserveBtn);

        add(formPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
            }
        });
        setVisible(true);

        // Update available rooms when day or time slot changes
        ActionListener updateRoomsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomModel.removeAllElements();
                String url = "jdbc:mysql://localhost:3306/final";
                String user = "root";
                String password = "283200";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = java.sql.DriverManager.getConnection(url, user, password);
                    // Get all unique rooms
                    String allRoomsSQL = "SELECT DISTINCT room_name FROM Rooms ORDER BY room_name ASC";
                    java.sql.PreparedStatement psAll = con.prepareStatement(allRoomsSQL);
                    java.sql.ResultSet rsAll = psAll.executeQuery();
                    boolean found = false;
                    while (rsAll.next()) {
                        roomModel.addElement(rsAll.getString("room_name"));
                        found = true;
                    }
                    rsAll.close();
                    psAll.close();
                    con.close();
                    if (!found) {
                        roomModel.addElement("No rooms available");
                    }
                } catch (Exception ex) {
                    roomModel.removeAllElements();
                    roomModel.addElement("No rooms available");
                }
            }
        };
        dayCombo.addActionListener(updateRoomsListener);
        timeSlotCombo.addActionListener(updateRoomsListener);
        // Initial population
        updateRoomsListener.actionPerformed(null);

        reserveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String day = dayCombo.getSelectedItem().toString();
                String course = courseCombo.getSelectedItem().toString();
                String room = roomCombo.getSelectedItem() != null ? roomCombo.getSelectedItem().toString() : "";
                String timeSlot = timeSlotCombo.getSelectedItem().toString();

                // Convert selected day to the next date for that day
                java.time.LocalDate today = java.time.LocalDate.now();
                java.time.DayOfWeek selectedDay = java.time.DayOfWeek.valueOf(day.toUpperCase());
                int daysUntil = (selectedDay.getValue() - today.getDayOfWeek().getValue() + 7) % 7;
                if (daysUntil == 0) daysUntil = 7; // always next occurrence
                java.time.LocalDate reservedDate = today.plusDays(daysUntil);

                String url = "jdbc:mysql://localhost:3306/final";
                String user = "root";
                String password = "283200";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection con = java.sql.DriverManager.getConnection(url, user, password);

                    // Clean up expired reservations
                    String deleteSQL = "DELETE FROM ReservedRooms WHERE reserved_date < CURDATE()";
                    java.sql.PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                    psDelete.executeUpdate();
                    psDelete.close();

                    // Check if room is already reserved for that date and slot
                    String checkSQL = "SELECT COUNT(*) FROM ReservedRooms WHERE room_name = ? AND reserved_date = ? AND time_slot = ?";
                    java.sql.PreparedStatement psCheck = con.prepareStatement(checkSQL);
                    psCheck.setString(1, room);
                    psCheck.setDate(2, java.sql.Date.valueOf(reservedDate));
                    psCheck.setString(3, timeSlot);
                    java.sql.ResultSet rs = psCheck.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);
                    rs.close();
                    psCheck.close();

                    if (count > 0) {
                        JOptionPane.showMessageDialog(RoomReservationFrame.this, "Room is already reserved for that day and time slot.", "Info", JOptionPane.INFORMATION_MESSAGE);
                        con.close();
                        return;
                    }

                    // Insert reservation (add time_slot field)
                    String insertSQL = "INSERT INTO ReservedRooms (room_name, reserved_date, reserved_by, time_slot) VALUES (?, ?, ?, ?)";
                    java.sql.PreparedStatement psInsert = con.prepareStatement(insertSQL);
                    psInsert.setString(1, room);
                    psInsert.setDate(2, java.sql.Date.valueOf(reservedDate));
                    psInsert.setString(3, "admin"); // or get actual user if available
                    psInsert.setString(4, timeSlot);
                    psInsert.executeUpdate();
                    psInsert.close();

                    JOptionPane.showMessageDialog(RoomReservationFrame.this, "Room reserved successfully for " + reservedDate + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RoomReservationFrame.this, "Database Error:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
} 