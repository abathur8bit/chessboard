package com.axorion.chess;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple ChessBoard.
 */
public class ChessBoardTest extends TestCase
{
    String initialPosition =
                "rnbqkbnr"+
                "pppppppp"+
                "        "+
                "        "+
                "        "+
                "        "+
                "PPPPPPPP"+
                "RNBQKBNR";

    public void testFenInitialPosition() {
        ChessBoard board = new ChessBoard();
        String expected = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        System.out.println("fen=["+board.fen()+"]");
        assertEquals(expected,board.fen());
    }

    public void testFenPosition() {
        String position =
                "rnbqkbnr"+
                "p   pppp"+
                "        "+
                " ppp    "+
                "    PP  "+
                "        "+
                "PPPP  PP"+
                "RNBQKBNR";
        ChessBoard board = new ChessBoard();
        board.setPosition(position);
        String expected = "rnbqkbnr/p3pppp/8/1ppp4/4PP2/8/PPPP2PP/RNBQKBNR";
        //                "rnbqkbnr/p3pppp/8/1ppp4/4PP2/8/PPPP2PP/RNBQKBNR w KQkq - 0 4"     fen from shredder
        System.out.println("fen=["+board.fen()+"]");
        assertEquals(expected,board.fen());
    }

    public void testSetPosition() {
        ChessBoard board = new ChessBoard();
        board.setPosition(initialPosition);
        assertEquals(initialPosition,board.toLetters());

    }
}
