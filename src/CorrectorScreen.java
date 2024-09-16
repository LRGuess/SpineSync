package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CorrectorScreen extends JFrame implements KeyListener {

    public CorrectorScreen() {
        //Set up JFrame
        this.setTitle("Correct your posture");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true); //Remove window borders
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Fullscreen

        //Detect key presses
        this.addKeyListener(this);

        //Make screen black
        this.getContentPane().setBackground(Color.BLACK);

        //Center window
        this.setLocationRelativeTo(null);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 50));

        String message = "Prees sdkghjsdfgh";
        FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
        int x = (getWidth() - fontMetrics.stringWidth(message));
        int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();

        g.drawString(message, x, y);
    }

    //KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
        System.exit(0);
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    //Main to run the prog
    public static void main(String [] args) {
        CorrectorScreen correctScreen = new CorrectorScreen();
        correctScreen.setVisible(true);
    }
}
