package com.mychessgame.engine.boardGui;

import com.mychessgame.engine.pieces.Piece;
import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private final JButton[][] buttons; // Grid of buttons for the chessboard
    private Piece[][] boardState; // 2D array to hold the state of the board
    int moveCount = 0;

    public Gui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(560,560);
        setVisible(true);
        setLayout(new GridLayout(8, 8)); // Set layout to GridLayout for chessboard
        buttons = new JButton[8][8];
        initializeChessBoard();

        setVisible(true);
    }
    private void initializeChessBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(70, 70)); // Set button size
                button.setOpaque(true);
                button.setBorderPainted(false);

                // Alternate colors for the chessboard
                if ((row + col) % 2 != 0) {
                    button.setBackground(new Color(184,139,74));
                } else {
                    button.setBackground(new Color(227,193,111));
                }
                // Add ActionListener to capture button clicks
                final int currentRow = row;
                final int currentCol = col;
                button.addActionListener(_ -> handleButtonClick(currentRow, currentCol));

                // Add button to the grid
                buttons[row][col] = button;
                add(button);

            }
        }
    }
    // Set up the chessboard with pieces
    public void setChessBoard(Piece[][] board) {
        this.boardState = board;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    buttons[row][col].setIcon(piece.getImage()); // Set piece icon
                } else {
                    buttons[row][col].setIcon(null); // Clear square if no piece
                }
            }
        }
    }
    private int startRow = -1;
    private int startCol = -1;

private void handleButtonClick(int row, int col) {
    if (boardState == null) {
        System.out.println("Board state not initialized");
        return;
    }
    Piece piece = boardState[row][col]; // Retrieve the piece at the clicked position

    // If it's the first click, select the piece and show valid moves
    if (startRow == -1 && startCol == -1) {
        if (piece != null) {
            // Enforce turn-based logic
            if ((moveCount % 2 == 0 && piece.getColor().equals("White")) ||
                    (moveCount % 2 == 1 && piece.getColor().equals("Black"))) {
                System.out.println("Clicked on: " + piece.getClass().getSimpleName() +
                        " at (" + row + ", " + col + ")");
                // Store the starting position of the piece
                startRow = row;
                startCol = col;
            } else {
                System.out.println("It's not " + piece.getColor() + "'s turn!");
            }
        } else {
            System.out.println("No piece at (" + row + ", " + col + ")");
        }
    }
    // If it's the second click, attempt to move the piece to the destination
    else {
        Piece selectedPiece = boardState[startRow][startCol];
        if (selectedPiece == null) {
            System.out.println("Clicked on an empty square.");
        } else  {
            // Check if the move is valid
                System.out.println("Moving piece from (" + startRow + ", " + startCol + ") to (" + row + ", " + col + ")");
                if (selectedPiece.move(boardState, selectedPiece.getColor(), startRow, startCol, row, col)){
                    // Increment move count and switch turns
                    moveCount++;
                }
                // Update the chessboard with the new board state
                setChessBoard(boardState);

        }

        // Reset for the next move
        startRow = -1;
        startCol = -1;
    }
}

}



