package MainPageUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;

public class SpecificDayFreeRooms extends JFrame implements ActionListener {

    JLabel dayLabel;
    JLabel timeLabel;
    SpinnerDateModel timeModel;
    JSpinner timeSpinner;
    JButton btn;
    JComboBox<String> dayComboBox;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();

    public SpecificDayFreeRooms(MainFrame ref) {
        ref.setVisible(false);
        this.setTitle("See Empty Rooms for a Custom Day and Time");
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
    }

    public void addcomp() {
        // frame background
        this.getContentPane().setBackground(Color.decode("#f0f0f0"));

        // HEADER
        ImageIcon ob = new ImageIcon("src/images/es.png");
        // get the raw image
        Image rawImage = ob.getImage();
        // scale it
        Image scaledImage = rawImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        // create new ImageIcon with the scaled image
        ob = new ImageIcon(scaledImage);
        JLabel header = new JLabel("Find Available Empty Classrooms", ob, JLabel.CENTER);
        header.setBounds(0, 0, width, 80);
        header.setBackground(Color.decode("#2c3e50"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.setOpaque(true);
        this.add(header);

        // LOGO
        ImageIcon logoIcon = new ImageIcon("src/images/mono.png");
        JLabel logo = new JLabel(logoIcon);
        logo.setBounds((width - 400) / 2, 100, 400, 120);
        logo.setOpaque(false);
        this.add(logo);

        // Label for day selection
        dayLabel = new JLabel("Select Specific Day:");
        dayLabel.setBounds((width - 480) / 2, 290, 250, 40);
        dayLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        this.add(dayLabel);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        dayComboBox = new JComboBox<>(days);
        dayComboBox.setBounds((width - 480) / 2 + 250, 290, 230, 40);
        dayComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        this.add(dayComboBox);

        // Label for time slot
        timeLabel = new JLabel("Enter Specific Time Slot:");
        timeLabel.setBounds((width - 480) / 2, 360, 250, 40);
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        this.add(timeLabel);

        java.util.Date date = new java.util.Date();
        timeModel = new SpinnerDateModel(date, null, null, java.util.Calendar.HOUR_OF_DAY);
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(editor);
        timeSpinner.setBounds((width - 480) / 2 + 250, 360, 230, 40);
        timeSpinner.setFont(new Font("SansSerif", Font.PLAIN, 16));
        this.add(timeSpinner);

        // Button
        btn = new RoundedButton("Find Empty Classrooms");
        btn.setBounds((width - 300) / 2, 460, 300, 50);
        btn.setBackground(Color.decode("#2980b9"));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.addActionListener(this);  // add the ActionListener here
        this.add(btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // get user selections
        String day = dayComboBox.getSelectedItem().toString();

        java.util.Date selectedDate = (java.util.Date) timeSpinner.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String selectedTime = sdf.format(selectedDate);

        // JDBC
        String url = "jdbc:mysql://localhost:3306/final";
        String user = "root";
        String password = "283200";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String sql = """
                SELECT room_name
                FROM Rooms
                WHERE room_name NOT IN (
                    SELECT room_name
                    FROM Timetable
                    WHERE day = ?
                      AND ? BETWEEN slot_start AND slot_end
                )
                """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, day);
            ps.setString(2, selectedTime);

            ResultSet rs = ps.executeQuery();

            ArrayList<String> availableRooms = new ArrayList<>();
            while (rs.next()) {
                availableRooms.add(rs.getString("room_name"));
            }

            if (availableRooms.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty rooms found for the selected day and time.",
                        "No Rooms", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // convert the ArrayList to a 2D array for JTable
                String[][] tableData = new String[availableRooms.size()][1];

                for (int i = 0; i < availableRooms.size(); i++) {
                    tableData[i][0] = availableRooms.get(i);
                }

                String[] columns = {"Available Rooms"};

                JTable table = new JTable(tableData, columns);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setPreferredSize(new java.awt.Dimension(500, 400));
                JOptionPane.showMessageDialog(this, scrollPane,
                        "Available Rooms", JOptionPane.INFORMATION_MESSAGE);
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
