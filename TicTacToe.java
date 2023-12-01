import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Concept: Class
public class TicTacToe extends JFrame {

    // Concept: Static variable
    private static final int GRID_SIZE = 3;

    private JButton[][] buttons;
    private char currentPlayer = 'X';

    // Concept: Constructor
    public TicTacToe() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        buttons = new JButton[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }

        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            if (clickedButton.getText().equals("")) {
                clickedButton.setText(String.valueOf(currentPlayer));
                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetGame();
                } else {
                    switchPlayer();
                }
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (checkLine(buttons[i][0].getText(), buttons[i][1].getText(), buttons[i][2].getText()) ||
                checkLine(buttons[0][i].getText(), buttons[1][i].getText(), buttons[2][i].getText())) {
                return true;
            }
        }
        return checkLine(buttons[0][0].getText(), buttons[1][1].getText(), buttons[2][2].getText()) ||
                checkLine(buttons[0][2].getText(), buttons[1][1].getText(), buttons[2][0].getText());
    }

    private boolean checkLine(String a, String b, String c) {
        return !a.equals("") && a.equals(b) && b.equals(c);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        // Concept: Ternary operator and Method Overloading
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void resetGame() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    // Concept: Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}
