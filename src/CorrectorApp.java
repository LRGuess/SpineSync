package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CorrectorApp extends JFrame {

    private Timer reminderTimer; // Timer to trigger the reminder screen
    private Timer countdownTimer; // Timer to update the countdown
    private int interval = 5000; // Default interval (5 seconds for demo purposes)
    private int remainingTime; // Time remaining until the next reminder
    private JLabel statusLabel; // Label to show time until next reminder

    public CorrectorApp() {
        // Set up the control window (menu)
        this.setTitle("Posture Corrector");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1));

        JLabel intervalLabel = new JLabel("Set interval (minutes):", SwingConstants.CENTER);
        JTextField intervalField = new JTextField("5"); // Default interval is 5 minutes
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        statusLabel = new JLabel("Stopped", SwingConstants.CENTER); // Status label to show time left or stopped

        this.add(intervalLabel);
        this.add(intervalField);
        this.add(startButton);
        this.add(stopButton);
        this.add(statusLabel);

        // Start button action
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int minutes;
                try {
                    minutes = Integer.parseInt(intervalField.getText());
                    interval = minutes * 60 * 1000; // Convert minutes to milliseconds
                    remainingTime = interval / 1000; // Set remaining time in seconds
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    return;
                }

                // Start the reminder timer
                reminderTimer = new Timer(interval, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new CorrectorScreen(); // Display the reminder screen
                        remainingTime = interval / 1000; // Reset remaining time after reminder
                    }
                });
                reminderTimer.start();

                // Start the countdown timer to update the label every second
                countdownTimer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (remainingTime > 0) {
                            remainingTime--;
                            statusLabel.setText("Next reminder in: " + remainingTime + " seconds");
                        }
                    }
                });
                countdownTimer.start();

                JOptionPane.showMessageDialog(null, "Reminder started! Every " + minutes + " minutes.");
            }
        });

        // Stop button action
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reminderTimer != null && reminderTimer.isRunning()) {
                    reminderTimer.stop();
                }
                if (countdownTimer != null && countdownTimer.isRunning()) {
                    countdownTimer.stop();
                }
                statusLabel.setText("Stopped");
                JOptionPane.showMessageDialog(null, "Reminder stopped.");
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
            this.setAlwaysOnTop(true); // Make sure it stays on top of other windows

            // Make screen black
            this.getContentPane().setBackground(Color.BLACK);

            // Randomly select a message from the array
            Random random = new Random();
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
            buttonMessage = buttonMessages[random.nextInt(buttonMessages.length)];

            // Set layout and add a button
            this.setLayout(new BorderLayout());
            JButton closeButton = new JButton(buttonMessage);
            closeButton.setFont(new Font("Arial", Font.BOLD, 30));
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the screen when the button is pressed
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
