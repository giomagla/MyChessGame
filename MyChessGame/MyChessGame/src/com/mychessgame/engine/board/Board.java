package com.mychessgame.engine.board;

import com.mychessgame.engine.pieces.*;

public class Board {
    Piece[][] board;
    String imagePath = "C:\\Users\\ALIENWARE\\Desktop\\MyChessGame\\MyChessGame\\src\\Images\\";
    public Board() {
        board = new Piece[8][8];
    }


    public  void setBoard() {
        //set empty spaces
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
        // set pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(PieceTypes.PAWN, "Black",imagePath + "BlackPawn.png");
            board[6][i] = new Pawn(PieceTypes.PAWN, "White",imagePath + "WhitePawn.png");
        }
        //set rooks
        board[0][7] = new Rook(PieceTypes.ROOK, "Black",imagePath + "BlackRook.png");
        board[0][0] = new Rook(PieceTypes.ROOK, "Black",imagePath + "BlackRook.png");
        board[7][0] = new Rook(PieceTypes.ROOK, "White",imagePath + "WhiteRook.png");
        board[7][7] = new Rook(PieceTypes.ROOK, "White",imagePath + "WhiteRook.png");
        //set knights
        board[0][1] = new Knight(PieceTypes.KNIGHT, "Black",imagePath + "BlackKnight.png");
        board[7][1] = new Knight(PieceTypes.KNIGHT, "White",imagePath + "WhiteKnight.png");
        board[0][6] = new Knight(PieceTypes.KNIGHT, "Black",imagePath + "BlackKnight.png");
        board[7][6] = new Knight(PieceTypes.KNIGHT, "White",imagePath + "WhiteKnight.png");
        // set bishops
        board[0][2] = new Bishop(PieceTypes.BISHOP,"Black",imagePath + "BlackBishop.png");
        board[0][5] = new Bishop(PieceTypes.BISHOP,"Black",imagePath + "BlackBishop.png");
        board[7][2] = new Bishop(PieceTypes.BISHOP,"White",imagePath + "WhiteBishop.png");
        board[7][5] = new Bishop(PieceTypes.BISHOP,"White",imagePath + "WhiteBishop.png");

        board[0][3] = new Queen(PieceTypes.QUEEN,"Black",imagePath + "BlackQueen.png");
        board[7][3] = new Queen(PieceTypes.QUEEN,"White",imagePath + "WhiteQueen.png");
        //set kings
        board[0][4] = new King(PieceTypes.KING,"Black",imagePath + "BlackKing.png");
        board[7][4] =  new King(PieceTypes.KING,"White",imagePath + "WhiteKing.png");

    }
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(" . "); // Empty space
                } else {
                    System.out.print(board[i][j].getType().getSymbol() + " "); // Print the symbol of the piece
                }
            }
            System.out.println();
        }
    }
    public Piece[][] getBoard() {
        return board;
    }


}
