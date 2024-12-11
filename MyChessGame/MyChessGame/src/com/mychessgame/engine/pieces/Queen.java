package com.mychessgame.engine.pieces;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.mychessgame.engine.pieces.King.*;

public class Queen extends Piece{
    public Queen(PieceTypes type, String color, String imagePath) {
        super(type, color, imagePath);
    }

    @Override
    public boolean move(Piece[][] board, String color, int row, int col, int desiredRow, int desiredCol) {
        List<int[]> desiredMoves = validMoves(board, color, row, col);
        // Check if the desired move is in the list of valid moves
        boolean isValidMove = false;
        for (int[] move : desiredMoves) {
            if (move[0] == desiredRow && move[1] == desiredCol) {
                isValidMove = true;
                break;
            }
        }
        Piece[][] simulatedMove = simulateMove(board,row,col,desiredRow,desiredCol);
        int kingRow = findKingRow(simulatedMove, color);
        int kingCol = findKingCol(simulatedMove, color);
        if (isInCheck(simulatedMove,color,kingRow,kingCol)) {
            System.out.println("You can not move your piece it opens check for king");
            isValidMove = false;
        }
        if (isValidMove) {
            System.out.println("Move is valid: Moving Queen to (" + desiredRow + ", " + desiredCol + ")");
            // Move the piece on the board
            board[desiredRow][desiredCol] = board[row][col]; // Move the piece to the new position
            board[row][col] = null; // Clear the old position
        } else {
            System.out.println("Invalid move: Cannot move Queen to (" + desiredRow + ", " + desiredCol + ")");
        }
        return isValidMove;
    }
    @Override
    public List<int[]> validMoves(Piece[][] board, String color, int row, int col) {
        List<int[]> moves = new ArrayList<>();
        int[][] directions = {
                {-1, 0},  // Top vertical
                {0, 1},   // right horizontal
                {1, 0},   // bottom vertical
                {0, -1},  // left horizontal
                {-1, -1}, // Top-left diagonal
                {-1, 1},  // Top-right diagonal
                {1, -1},  // Bottom-left diagonal
                {1, 1}    // Bottom-right diagonal
        };
        for (int[] direction : directions) {
            int currentRow = row;
            int currentCol = col;

            while (true) {
                currentRow += direction[0];
                currentCol += direction[1];


                // Check if the new position is outside the board
                if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                    break;
                }
                Piece targetPiece = board[currentRow][currentCol];
                if (targetPiece == null) {
                    // Empty square: add to valid moves
                    moves.add(new int[]{currentRow, currentCol});
                } else if (!targetPiece.getColor().equals(color)) {
                    // Opponent's piece: add move and stop in this direction
                    moves.add(new int[]{currentRow, currentCol});
                    break;
                } else {
                    // Same color piece: stop in this direction
                    break;
                }
            }
        }
        return moves;
    }


    @Override
    public ImageIcon getImage() {
        return this.image;
    }


}
