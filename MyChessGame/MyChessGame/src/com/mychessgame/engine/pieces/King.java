package com.mychessgame.engine.pieces;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    public King(PieceTypes type, String color, String imagePath) {
        super(type, color, imagePath);
    }

    public static boolean isInCheck(Piece[][] board, String color, int kingRow, int kingCol) {
        String opponentColor = color.equals("White") ? "Black" : "White";

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null && piece.getColor().equals(opponentColor)) {
                    List<int[]> opponentMoves = piece.validMoves(board, opponentColor, row, col);
                    for (int[] move : opponentMoves) {
                        if (move[0] == kingRow && move[1] == kingCol) {
                            return true; // King is in check
                        }
                    }
                }
            }
        }
        return false;
    }


    @Override
    public boolean move(Piece[][] board, String color, int row, int col, int desiredRow, int desiredCol) {
        // Validate if the desired move is in the King's valid moves
        List<int[]> validMoves = validMoves(board, color, row, col);
        Piece[][] simulatedBoard = simulateMove(board,row,col,desiredRow,desiredCol);
        if (isInCheck(simulatedBoard,color,desiredRow,desiredCol)){
            System.out.println("King can not move there");
            return false;
        }

        for (int[] move : validMoves) {
            if (move[0] == desiredRow && move[1] == desiredCol) {
                // Execute the move
                board[desiredRow][desiredCol] = this; // Place King in new position
                board[row][col] = null;              // Clear old position
                return true;
            }
        }

        System.out.println("Invalid move for King.");
        return false;
    }
    @Override
    public List<int[]> validMoves(Piece[][] board, String color, int row, int col) {
        List<int[]> moves = new ArrayList<>();

        // Directions for King movement
        int[][] directions = {
                {-1, 0},  // Up
                {1, 0},   // Down
                {0, -1},  // Left
                {0, 1},   // Right
                {-1, -1}, // Up-left
                {-1, 1},  // Up-right
                {1, -1},  // Down-left
                {1, 1}    // Down-right
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Ensure the move is within bounds
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Piece targetPiece = board[newRow][newCol];
                // If the square is empty or contains an opponent's piece
                if (targetPiece == null || !targetPiece.getColor().equals(color)) {
                    // Temporarily make the move to check for "check" state
                 moves.add(new int[] {newRow,newCol});
                }
            }
        }
        return moves;
    }

    public static Piece[][] simulateMove(Piece[][] board, int startRow, int startCol, int destRow, int destCol) {
        if (destRow < 0 || destRow >= 8 || destCol < 0 || destCol >= 8 ||
                startRow < 0 || startRow >= 8 || startCol < 0 || startCol >= 8) {
            throw new IllegalArgumentException("Invalid board coordinates.");
        }
        // Deep copy of the board
        Piece[][] simulatedBoard = new Piece[8][8];
        for (int r = 0; r < 8; r++) {
            System.arraycopy(board[r], 0, simulatedBoard[r], 0, 8);
        }

        // Simulate the move
        simulatedBoard[destRow][destCol] = simulatedBoard[startRow][startCol];
        simulatedBoard[startRow][startCol] = null;

        return simulatedBoard;
    }


    @Override
    public ImageIcon getImage() {
        return this.image;
    }

    public static int findKingRow(Piece[][] board, String color) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Piece piece = board[row][col];
                if (piece instanceof King && piece.getColor().equals(color)) {
                    return row; // Return the row of the king
                }
            }
        }
        throw new IllegalStateException("King not found on the board for color: " + color);
    }

    public static int findKingCol(Piece[][] board, String color) {
        for (Piece[] pieces : board) {
            for (int col = 0; col < pieces.length; col++) {
                Piece piece = pieces[col];
                if (piece instanceof King && piece.getColor().equals(color)) {
                    return col; // Return the column of the king
                }
            }
        }
        throw new IllegalStateException("King not found on the board for color: " + color);
    }


}
