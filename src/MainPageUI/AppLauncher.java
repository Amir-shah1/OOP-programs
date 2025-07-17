package MainPageUI;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class AppLauncher {

    public static void main(String[] args) 
    {
        MainFrame frm=new MainFrame();
        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        frm.setBounds(0, 0, width, height);
        // Keep title bar and controls, but disable maximize button
        frm.setResizable(false);
        frm.getContentPane().setBackground(Color.BLACK);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(null);
        frm.addcomp();
        frm.setTitle("Slot Management System(SMS)");
        frm.setVisible(true);
    }
    
}
