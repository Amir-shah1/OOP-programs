package MainPageUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

public class RoundedButton extends JButton  {
    public RoundedButton(String label) {
        super(label);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // rounded corners
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getBackground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }
}
