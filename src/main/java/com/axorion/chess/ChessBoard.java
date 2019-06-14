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

/**
 * Chess board that keeps track of the state, generates fen notation, and validates moves.
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
