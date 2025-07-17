package MainPageUI;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener{
    JLabel logo;
    JLabel name;
    JButton [] btns;
    
    public void addcomp() {
    this.getContentPane().setBackground(Color.decode("#f0f0f0"));

    // HEADER
    ImageIcon ob = new ImageIcon("src/images/tt.png");
    // get the raw image
    Image rawImage = ob.getImage();
    // scale it
    Image scaledImage = rawImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

    // create new ImageIcon with the scaled image
    ob = new ImageIcon(scaledImage);
    name = new JLabel("Slot Management System (SMS)",ob, JLabel.CENTER);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    name.setBounds(0, 0, width, 80);
    name.setBackground(Color.decode("#2c3e50"));
    name.setForeground(Color.WHITE);
    name.setOpaque(true);
    name.setFont(new Font("SansSerif", Font.BOLD, 28));
    this.add(name);

    // LOGO
    ImageIcon obj = new ImageIcon("src/images/mono.png");
    logo = new JLabel(obj, JLabel.CENTER);
    logo.setBounds(width / 2 - 200, 130, 400, 120);
    this.add(logo);

    // ROUNDED PANEL
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(null);
    buttonPanel.setBounds(width / 2 - 200, 315, 400, 300);
    buttonPanel.setBackground(Color.WHITE);
    
    buttonPanel.setOpaque(true);

    String[] data = {
        "Find My TimeTable",
        "Find Current Empty Slots",
        "Find Specific Empty Slots",
        "Admin Login"
    };

    btns = new JButton[data.length];

    int btnWidth = 360;
    int btnHeight = 45;
    int x = 20;
    int y = 40;
    

    for (int i = 0; i < btns.length; i++) {
        btns[i] = new RoundedButton(data[i]);
        btns[i].setBounds(x, y, btnWidth, btnHeight);
        btns[i].setBackground(Color.decode("#2980b9"));
        btns[i].setForeground(Color.WHITE);
        btns[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btns[i].setFocusPainted(false);
        btns[i].addActionListener(this);
        buttonPanel.add(btns[i]);
        y += btnHeight + 15;
    }

    this.add(buttonPanel);
}



    @Override
    public void actionPerformed(ActionEvent e)
    {
        String op=e.getActionCommand();
        if(op.compareTo("Find My TimeTable")==0)
        {
            TimetableViewer obj=new TimetableViewer(this);
            
        }
        else if(op.compareTo("Find Specific Empty Slots")==0)
        {
            SpecificDayFreeRooms obj=new SpecificDayFreeRooms(this);
        }
        else if(op.compareTo("Admin Login")==0)
        {
            AdminLogin obj=new AdminLogin(this);
        }
        else if(op.compareTo("Find Current Empty Slots")==0)
        {
        try {
        // database connection parameters
        String url = "jdbc:mysql://localhost:3306/final";
        String user = "root";
        String password = "283200";  // your password here

        // load driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);

        // your SQL
        String sql = """
        SELECT room_name
        FROM Rooms
        WHERE room_name NOT IN (
            SELECT room_name
            FROM Timetable
            WHERE day = DAYNAME(CURDATE())
              AND CURTIME() BETWEEN slot_start AND slot_end
        )
        """;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        java.util.List<String> rooms = new java.util.ArrayList<>();
        // display results
        while (rs.next()) {
            rooms.add(rs.getString("room_name"));
        }
        if (rooms.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null,
            "No empty rooms at this time.", "Available Empty Rooms",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
} 
        else {
    // convert to 2D array for JTable
        String[][] data = new String[rooms.size()][1];
        for (int i = 0; i < rooms.size(); i++) 
        {
        data[i][0] = rooms.get(i);
        }
            String[] column = {"Available Room"};
            javax.swing.JTable table = new javax.swing.JTable(data, column);
            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(table);
            javax.swing.JOptionPane.showMessageDialog(btns[0], scrollPane,
            "Available Empty Rooms", javax.swing.JOptionPane.INFORMATION_MESSAGE);
}

        // cleanup
        rs.close();
        stmt.close();
        con.close();

    } catch (ClassNotFoundException f) {
        System.out.println("MySQL JDBC Driver not found!");
        f.printStackTrace();
    } catch (SQLException f) {
        System.out.println("Database error occurred.");
        f.printStackTrace();
    }
        }
    }
    
}
