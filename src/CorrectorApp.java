package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CorrectorApp extends JFrame {

    private Timer timer; // Timer to trigger the reminder screen
    private int interval = 5000; // Default interval (5 seconds for demo purposes)

    public CorrectorApp() {
        // Set up the control window (menu)
        this.setTitle("Posture Corrector");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        JLabel intervalLabel = new JLabel("Set interval (minutes):", SwingConstants.CENTER);
        JTextField intervalField = new JTextField("5"); // Default interval is 5 minutes
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");

        this.add(intervalLabel);
        this.add(intervalField);
        this.add(startButton);
        this.add(stopButton);

        // Start button action
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int minutes;
                try {
                    minutes = Integer.parseInt(intervalField.getText());
                    interval = minutes * 60 * 1000; // Convert minutes to milliseconds
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    return;
                }

                // Start the timer
                timer = new Timer(interval, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new CorrectorScreen(); // Display the reminder screen
                    }
                });
                timer.start();
                JOptionPane.showMessageDialog(null, "Reminder started! Every " + minutes + " minutes.");
            }
        });

        // Stop button action
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Reminder stopped.");
                }
            }
        });

        // Center window
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Reminder screen
    public static class CorrectorScreen extends JFrame {

        private final String selectedMessage;
        private final String buttonMessage;

        public CorrectorScreen() {
            // Set up JFrame
            this.setTitle("Correct your posture");
            this.setSize(800, 600);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setUndecorated(true); // Remove window borders
            this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen

            // Make screen black
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
            String[] buttonMessages = {
                    "Go away",
                    "Ok",
                    "Thanks",
                    "I don't care",
                    "Leave me alone"
            };
            buttonMessage = buttonMessages[random.nextInt(messages.length)];

            // Set layout and add a button
            this.setLayout(new BorderLayout());
            JButton closeButton = new JButton(buttonMessage);
            closeButton.setFont(new Font("Arial", Font.BOLD, 30));
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Close the screen when the button is pressed
                    dispose(); // Just close this window, not the whole app
                }
            });

            // Center button at the bottom
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.BLACK);
            buttonPanel.add(closeButton);
            this.add(buttonPanel, BorderLayout.SOUTH);

            // Center window
            this.setLocationRelativeTo(null);
            this.setVisible(true);
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
    }

    // Main to run the app
    public static void main(String[] args) {
        new CorrectorApp();
    }
}
