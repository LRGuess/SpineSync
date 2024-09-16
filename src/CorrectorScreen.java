package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class CorrectorScreen extends JFrame implements KeyListener {

    private final String selectedMessage;

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

        // Randomly select a message from the array
        Random random = new Random();
        // Array of messages to display
        String[] messages = {
                "Sit up straight!",
                "Correct your posture!",
                "Don't slouch!",
                "Stay focused!",
                "Stretch your back!"
        };
        selectedMessage = messages[random.nextInt(messages.length)];

        //Center window
        this.setLocationRelativeTo(null);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        // Get the screen size and center the text
        FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
        int x = (getWidth() - fontMetrics.stringWidth(selectedMessage)) / 2;
        int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();

        g.drawString(selectedMessage, x, y);
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
