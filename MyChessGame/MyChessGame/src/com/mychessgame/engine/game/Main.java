package com.mychessgame.engine.game;

import com.mychessgame.engine.board.Board;
import com.mychessgame.engine.boardGui.Gui;

public class Main {
    public static void main(String[] args) {
       game();
    }

    public static void game(){
        // Initialize the board and GUI
        Board gameBoard = new Board();
        gameBoard.setBoard();
        Gui gui = new Gui();


        // Set up the board in the GUI
        gui.setChessBoard(gameBoard.getBoard());
        gameBoard.printBoard();
    }
}