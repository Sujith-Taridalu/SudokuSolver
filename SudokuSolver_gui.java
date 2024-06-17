import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class SudokuSolver_gui extends JFrame {
    private final JTextField[][] boardFields;

    public SudokuSolver_gui() {
        setTitle("Sudoku Solver");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the board fields
        boardFields = new JTextField[9][9];
        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardFields[i][j] = new JTextField(1);
                boardFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                boardFields[i][j].setFont(new Font("Arial", Font.BOLD, 20));

                // Set the border for each JTextField
                Border border = BorderFactory.createMatteBorder(
                        i % 3 == 0 ? 3 : 1, // top
                        j % 3 == 0 ? 3 : 1, // left
                        (i + 1) % 3 == 0 ? 3 : 1, // bottom
                        (j + 1) % 3 == 0 ? 3 : 1, // right
                        Color.BLACK
                );
                boardFields[i][j].setBorder(border);
                boardPanel.add(boardFields[i][j]);
            }
        }

        // Create the solve button
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> solveSudoku());

        // Add components to the frame
        add(boardPanel, BorderLayout.CENTER);
        add(solveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void solveSudoku() {
        // Get the values from the board fields and create the board array
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String value = boardFields[i][j].getText();
                if (value.length() == 0) {
                    board[i][j] = '.';
                } else {
                    board[i][j] = value.charAt(0);
                }
            }
        }

        // Solve the puzzle
        SudokuSolver.solveSudoku(board);

        // Update the board fields with the solved values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardFields[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }

    public static void main(String[] args) {
        new SudokuSolver_gui();
    }
}
