package AdminPageUI;

//import adminui.TimetableEditFrame;
//import adminui.RoomReservationFrame;
//import adminui.RoundedButton;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setTitle("Admin");
        setSize(width, height);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Header
        JLabel header = new JLabel("Admin Panel", JLabel.CENTER);
        header.setOpaque(true);
        header.setBackground(Color.decode("#2c3e50"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 32));
        header.setBounds(0, 0, width, 100);
        add(header);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 30));
        buttonPanel.setBackground(Color.decode("#f0f0f0"));
        int panelWidth = 400;
        int panelHeight = 300;
        buttonPanel.setBounds((width - panelWidth) / 2, 200, panelWidth, panelHeight);

        JButton timetableBtn = new RoundedButton("Edited Timetable");
        JButton coursesBtn = new RoundedButton("Edited Courses");
        JButton reserveBtn = new RoundedButton("Reserve a Room");

        timetableBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        coursesBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        reserveBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        timetableBtn.setBackground(Color.decode("#2980b9"));
        coursesBtn.setBackground(Color.decode("#2980b9"));
        reserveBtn.setBackground(Color.decode("#2980b9"));
        timetableBtn.setForeground(Color.WHITE);
        coursesBtn.setForeground(Color.WHITE);
        reserveBtn.setForeground(Color.WHITE);
        timetableBtn.setFocusPainted(false);
        coursesBtn.setFocusPainted(false);
        reserveBtn.setFocusPainted(false);

        buttonPanel.add(timetableBtn);
        buttonPanel.add(coursesBtn);
        buttonPanel.add(reserveBtn);
        add(buttonPanel);

        timetableBtn.addActionListener(e -> {
            setVisible(false);
            new TimetableEditFrame(this);
        });
        coursesBtn.addActionListener(e -> {
            setVisible(false);
            new CourseEditFrame(this);
        });
        reserveBtn.addActionListener(e -> {
            setVisible(false);
            new RoomReservationFrame(this);
        });

        setVisible(true);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(AdminFrame::new);
//    }
} 