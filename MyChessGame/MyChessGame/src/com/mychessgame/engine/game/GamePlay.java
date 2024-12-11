package com.mychessgame.engine.game;

import com.mychessgame.engine.board.Board;
import com.mychessgame.engine.boardGui.Gui;


public class GamePlay {

    public static void main(String[] args) {
       startPlay();
    }
    public static void startPlay(){
        Board gameBoard = new Board();
        Gui gui = new Gui();
        gameBoard.setBoard();
        gameBoard.printBoard();
        gui.setChessBoard(gameBoard.getBoard());



    }
}
