package chess.engine;

import chess.engine.board.Board;

public class Chess {
    public static void main(String[] args) {
        Board board = Board.createStartingPosition();
        System.out.println(board);
    }
}
