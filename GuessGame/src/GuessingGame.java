import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int randomNumber;
    private int attempts;
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;

    public GuessingGame() {
        setTitle("Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel promptLabel = new JLabel("Guess a number between 1 and 100:");
        promptLabel.setBounds(20, 20, 250, 20);
        add(promptLabel);

        guessField = new JTextField();
        guessField.setBounds(20, 50, 150, 20);
        add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.setBounds(180, 50, 80, 20);
        add(guessButton);

        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(20, 80, 250, 20);
        add(feedbackLabel);

        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setBounds(20, 110, 100, 20);
        add(attemptsLabel);

        guessButton.addActionListener(new GuessButtonListener());

        resetGame();
    }

    private void resetGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("");
        attemptsLabel.setText("Attempts: 0");
        guessField.setText("");
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                if (guess < randomNumber) {
                    feedbackLabel.setText("Too low!");
                } else if (guess > randomNumber) {
                    feedbackLabel.setText("Too high!");
                } else {
                    feedbackLabel.setText("Correct! You guessed it!");
                    JOptionPane.showMessageDialog(null, "You guessed the number in " + attempts + " attempts!");
                    resetGame();
                }
                attemptsLabel.setText("Attempts: " + attempts);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessingGame().setVisible(true);
            }
        });
    }
}
