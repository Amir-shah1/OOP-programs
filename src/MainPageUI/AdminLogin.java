package MainPageUI;

import AdminPageUI.AdminDashboard;
import java.awt.*;
import java.awt.event.*;    
import javax.swing.*;

public class AdminLogin extends JFrame {

    JPanel loginPanel;

    public AdminLogin(MainFrame ref) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        ref.setVisible(false);
        this.setTitle("Admin Login");
        this.setSize(width, height);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.initLogin();

        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ref.setVisible(true);
            }
        });
    }

    private void initLogin() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(0, 0, width, height);
        loginPanel.setBackground(Color.decode("#f0f0f0"));

        // HEADER with image
        ImageIcon headerIcon = new ImageIcon("src/images/es.png");
        Image headerScaled = headerIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        headerIcon = new ImageIcon(headerScaled);

        JLabel headerLabel = new JLabel("Admin Login", headerIcon, JLabel.CENTER);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.decode("#2c3e50"));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        headerLabel.setBounds(0, 0, width, 80);
        loginPanel.add(headerLabel);

        // COMSATS logo on login
        ImageIcon comsatsIcon = new ImageIcon("src/images/mono.png");
        JLabel comsatsLogo = new JLabel(comsatsIcon);
        comsatsLogo.setBounds((width - 400) / 2, 90, 400, 120);
        loginPanel.add(comsatsLogo);

        // Stylish login box
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(null);
        boxPanel.setBounds((width - 400) / 2, 260, 400, 320);
        boxPanel.setBackground(Color.WHITE);
        boxPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#2c3e50"), 2));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds((400 - 240) / 2, 70, 100, 25);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        boxPanel.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds((400 - 240) / 2, 95, 240, 30);
        boxPanel.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds((400 - 240) / 2, 135, 100, 25);
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        boxPanel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds((400 - 240) / 2, 160, 240, 30);
        boxPanel.add(passField);

        JButton loginBtn = new RoundedButton("Login");
        loginBtn.setBounds((400 - 140) / 2, 230, 140, 40);
        loginBtn.setBackground(Color.decode("#2980b9"));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginBtn.setFocusPainted(false);

        boxPanel.add(loginBtn);

        loginPanel.add(boxPanel);

        // handle button
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUser = userField.getText();
                String enteredPass = new String(passField.getPassword());

                if (enteredUser.equals("admin") && enteredPass.equals("1234")) {
                    loginPanel.setVisible(false);
                    AdminDashboard admin = new AdminDashboard();
                } else {
                    JOptionPane.showMessageDialog(AdminLogin.this,
                            "Wrong username or password",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.add(loginPanel);
    }
}
