package com.mychessgame.engine.pieces;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.mychessgame.engine.pieces.King.*;
import static com.mychessgame.engine.pieces.King.isInCheck;

public class Pawn extends Piece{

    public Pawn(PieceTypes type, String color, String imagePath) {
    super(type, color,imagePath);
}

    @Override
    public ImageIcon getImage() {
        return this.image;
    }

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
         // Execute move if valid
         if (isValidMove) {
             System.out.println("Move is valid: Moving Pawn to (" + desiredRow + ", " + desiredCol + ")");
             // Move the piece on the board
             board[desiredRow][desiredCol] = board[row][col]; // Move the piece to the new position
             board[row][col] = null; // Clear the old position
         } else {
             System.out.println("Invalid move: Cannot move Pawn to (" + desiredRow + ", " + desiredCol + ")");
         }
         return isValidMove;
     }

    @Override
    public List<int[]> validMoves(Piece[][] board, String color, int row, int col) {
        List<int[]> moves = new ArrayList<>();

        switch (color) {
            case "White" -> {
                // Ensure no out-of-bounds check for row == 0
                if (row > 0) {
                    //check for first move two squares up
                    if (row == 6 & board[5][col] == null && board[4][col] == null) {
                        moves.add(new int[] {4,col});
                    }
                    // Check for normal move: row - 1
                    if (board[row - 1][col] == null) {
                        moves.add(new int[]{row - 1, col}); // Move up
                    }

                    // Check for capture moves (left and right diagonals)
                    if (col > 0 && board[row - 1][col - 1] != null &&
                            board[row - 1][col - 1].getColor().equals("Black")) {
                        moves.add(new int[]{row - 1, col - 1}); // Capture up-left
                    }

                    if (col < 7 && board[row - 1][col + 1] != null &&
                            board[row - 1][col + 1].getColor().equals("Black")) {
                        moves.add(new int[]{row - 1, col + 1}); // Capture up-right
                    }
                }else {   //update pawn to queen
                    board[row][col] = new Queen(PieceTypes.QUEEN,"White","C:\\Users\\ALIENWARE\\Desktop\\MyChessGame\\MyChessGame\\src\\Images\\WhiteQueen.png");
                }
            }
            case "Black" -> {
                // Ensure no out-of-bounds check for row == 7
                if (row < 7) {
                    // check for first move two squares down
                    if (row == 1 & board[2][col] == null && board[3][col] == null) {
                        moves.add(new int[] {3,col});
                    }
                    // Check for normal move: row + 1
                    if (board[row + 1][col] == null) {
                        moves.add(new int[]{row + 1, col}); // Move down
                    }

                    // Check for capture moves (left and right diagonals)
                    if (col > 0 && board[row + 1][col - 1] != null &&
                            board[row + 1][col - 1].getColor().equals("White")) {
                        moves.add(new int[]{row + 1, col - 1}); // Capture down-left
                    }

                    if (col < 7 && board[row + 1][col + 1] != null &&
                            board[row + 1][col + 1].getColor().equals("White")) {
                        moves.add(new int[]{row + 1, col + 1}); // Capture down-right
                    }
                }else {    // update pawn to  queen
                    board[row][col] = new Queen(PieceTypes.QUEEN,"Black","C:\\Users\\ALIENWARE\\Desktop\\MyChessGame\\MyChessGame\\src\\Images\\BlackQueen.png");
                }
            }
            default -> System.out.println("Invalid color");
        }
        return moves;
    }
}
