package com.axorion.chess;

/**
 * Hello world!
 *
 */
public class ChessBoard
{
    int WHITE = 'w';
    int BLACK = 'b';
    int EMPTY_SQUARE = ' ';

    String whitePieceLetters = "PNBRQK";
    String blackPieceLetters = "pnbrqk";

    int[] gameBoard = new int[64];

    String boardLetters =
            "rnbqkbnr"+
            "pppppppp"+
            "        "+
            "        "+
            "        "+
            "        "+
            "PPPPPPPP"+
            "RNBQKBNR";

    public ChessBoard() {
        resetBoard();
    }

    public void resetBoard() {
        for(int i=0; i<64; ++i) {
            gameBoard[i] = boardLetters.charAt(i);
        }
    }

    public void setPosition(String letters) {
        for(int i=0; i<64; ++i) {
            gameBoard[i] = letters.charAt(i);
        }
    }

    public String toLetters() {
        StringBuilder letters = new StringBuilder();
        for(int i=0; i<gameBoard.length; ++i) {
            letters.append((char)gameBoard[i]);
        }
        return letters.toString();
    }

    public String fen() {
        StringBuilder fen = new StringBuilder();
        int emptyCount = 0;

        for(int i=0; i<gameBoard.length; ++i) {
            if(i>0 && i%8==0) {
                if(emptyCount > 0) {
                    fen.append(emptyCount);
                }
                emptyCount = 0;
                fen.append('/');
            }

            if(gameBoard[i] == EMPTY_SQUARE) {
                emptyCount++;
            } else {
                if(emptyCount > 0) {
                    fen.append(emptyCount);
                    emptyCount = 0;
                }
                fen.append((char)gameBoard[i]);
            }
        }
        return fen.toString();
    }
}
