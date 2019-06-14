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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for ChessBoard.
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
