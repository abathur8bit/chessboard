/* *****************************************************************************
 * Copyright 2019 Lee Patterson <https://8BitCoder.com> <https://github.com/abathur8bit>
 *
 * You may use and modify at will. Please credit me in the source.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ******************************************************************************/

package com.axorion.chess;

import java.util.ArrayList;
import java.util.List;

/**
 * Chess board that keeps track of the state, generates FEN notation.
 */
public class ChessBoard
{
    public static final int WHITE = 'w';
    public static final int BLACK = 'b';
    public static final int EMPTY_SQUARE = ' ';

    String blackPieceLetters = "pnbrqk";
    String whitePieceLetters = "PNBRQK";

    ArrayList<String> moveCard = new ArrayList<String>();

    int[] gameBoard = new int[64];
    int currentMove = WHITE;
    int halfMoveCounter = 0;
    int fullMoveCounter = 0;
    
    boolean castleWhiteKingSide = true;
    boolean castleWhiteQueenSide = true;
    boolean castleBlackKingSide = true;
    boolean castleBlackQueenSide = true;

    String boardLetters =
            "rnbqkbnr"+
            "pppppppp"+
            "        "+
            "        "+
            "        "+
            "        "+
            "PPPPPPPP"+
            "RNBQKBNR";

    String horz = "abcdefgh";
    String vert = "87654321";

    int[] validPawnMoves = {-1,-2};
    int[] validKnightMoves = {15,17};

    public ChessBoard() {
        resetBoard();
    }

    public void resetBoard() {
        for(int i=0; i<64; ++i) {
            gameBoard[i] = boardLetters.charAt(i);
        }
        moveCard.clear();
    }

    public void setPosition(String letters) {
        for(int i=0; i<64; ++i) {
            gameBoard[i] = letters.charAt(i);
        }
    }

    public void setWhoMoves(int color) {
        if(color == WHITE || color == BLACK) {
            currentMove = color;
        }
    }

    /**
     * Convert the board coordinate to an index with 0 being the upper left corner.
     * used internally.
     *
     * @param coord Board coordinate like "e1"
     * @return index into the gameBoard array.
     */
    public int boardToIndex(String coord) {
        int x=0,y=0,c=0;

        c = coord.charAt(0);
        for(int i=0; i<8; i++) {
            if(horz.charAt(i) == c) {
                x = i;
                break;
            }
        }

        c = coord.charAt(1);
        for(int i=0; i<8; i++) {
            if(vert.charAt(i) == c) {
                y = i;
                break;
            }
        }

        int index= y*8+x;
        return index;
    }

    /** A list of all the moves in chess coordinates like "e2e3". Always has from and to coordinates. */
    public List<String> getScoreCard() {
        return moveCard;
    }

    /** Returns all the moves in a single string in chess board "e1e3" format. For example "g1f3 b8c6 b1c3". */
    public String getMoveString() {
        StringBuilder moves = new StringBuilder();
        int fullMove = 0;
        for(int i=0; i<moveCard.size(); i++) {
            if(i>0) {
                moves.append(" ");
            }
            moves.append(moveCard.get(i));
        }
        return moves.toString();
    }

    /** Return what piece is at the chess coordinate specified, like "a1" would return 'R' on a new board. */
    public int pieceAt(String s) {
        if(s.length() == 2) {
            int index = boardToIndex(s.substring(0,2));
            return gameBoard[index];
        }
        return 0;
    }

    /** Enter a move in the form of "e2e4. Returns if the move was valid. */
    public boolean move(String s) {
        if(s.length() == 4) {
            String from = s.substring(0,2);
            String to = s.substring(2);
            int fromIndex = boardToIndex(from);
            int toIndex = boardToIndex(to);

            boolean isCapture = gameBoard[toIndex] != EMPTY_SQUARE;
            gameBoard[toIndex] = gameBoard[fromIndex];
            gameBoard[fromIndex] = EMPTY_SQUARE;
            moveCard.add(s);
            halfMoveCounter++;
            if(gameBoard[toIndex] == 'p' || gameBoard[toIndex] == 'P' || isCapture) {
                halfMoveCounter = 0;
            }
            if(currentMove == WHITE) {
                currentMove = BLACK;
            } else {
                currentMove = WHITE;
                fullMoveCounter++;
            }
            return true;
        }
        return false;
    }

    public String toLetters() {
        StringBuilder letters = new StringBuilder();
        for(int value : gameBoard) {
            letters.append((char)value);
        }
        return letters.toString();
    }

    public String toFen() {
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
        fen.append(" "+(char)currentMove+" ");

        fen.append((castleWhiteKingSide ?"K":""));
        fen.append((castleWhiteQueenSide?"Q":""));
        fen.append((castleBlackKingSide ?"k":""));
        fen.append((castleBlackQueenSide?"q":""));

        fen.append(" "+"-");  //todo En passant target square not implemented

        fen.append(" "+halfMoveCounter);
        fen.append(" "+(fullMoveCounter+1));

        return fen.toString();
    }
}
