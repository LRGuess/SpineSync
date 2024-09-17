package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CorrectorScreen extends JFrame {

    private final String selectedMessage;

    public CorrectorScreen() {
        //Set up JFrame
        this.setTitle("Correct your posture");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true); //Remove window borders
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Fullscreen

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

        // Set layout and add a button
        this.setLayout(new BorderLayout());
        JButton closeButton = new JButton("OK");
        closeButton.setFont(new Font("Arial", Font.BOLD, 30));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the screen when the button is pressed
                System.exit(0);
            }
        });

        // Center button at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(closeButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

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

    //Main to run the prog
    public static void main(String [] args) {
        CorrectorScreen correctScreen = new CorrectorScreen();
        correctScreen.setVisible(true);
    }
}
