import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Guessinggame {
    private JFrame frame;
    private JPanel panel;
    private JLabel promptLabel;
    private JLabel feedbackLabel;
    private JTextField guessField;
    private JButton submitButton;
    private int numberToGuess;
    private int numberOfAttempts;

    public Guessinggame () {
        // Initialize random number to guess
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        numberOfAttempts = 0;

        // Create the JFrame window
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the window

        // Create the JPanel to hold the components
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // Create the prompt label
        promptLabel = new JLabel("Guess a number between 1 and 100:");
        promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(promptLabel);

        // Create the text field for input
        guessField = new JTextField();
        panel.add(guessField);

        // Create the feedback label
        feedbackLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(feedbackLabel);

        // Create the submit button
        submitButton = new JButton("Submit Guess");
        submitButton.addActionListener(new SubmitButtonListener());
        panel.add(submitButton);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    // ActionListener for the submit button
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Get the user's guess from the text field
                int userGuess = Integer.parseInt(guessField.getText());
                numberOfAttempts++;

                // Compare the user's guess with the number to guess
                if (userGuess < numberToGuess) {
                    feedbackLabel.setText("Too low! Try again.");
                    feedbackLabel.setForeground(Color.RED);
                } else if (userGuess > numberToGuess) {
                    feedbackLabel.setText("Too high! Try again.");
                    feedbackLabel.setForeground(Color.RED);
                } else {
                    feedbackLabel.setText("Congratulations! You guessed it in " + numberOfAttempts + " attempts.");
                    feedbackLabel.setForeground(Color.GREEN);
                    // Disable the text field and button after correct guess
                    guessField.setEnabled(false);
                    submitButton.setEnabled(false);
                }

                // Clear the text field for the next guess
                guessField.setText("");

            } catch (NumberFormatException ex) {
                // Handle non-integer inputs
                feedbackLabel.setText("Please enter a valid number.");
                feedbackLabel.setForeground(Color.RED);
            }
        }
    }

    public static void main(String[] args) {
        // Start the GUI-based game
        SwingUtilities.invokeLater(() -> new Guessinggame());
    }
}
