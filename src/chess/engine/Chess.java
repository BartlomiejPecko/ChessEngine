package chess.engine;

import chess.engine.board.Board;

public class Chess {
    public static void main(String[] args) {

        System.out.println("Printing starting position...\n");
        Board board = Board.createStartingPosition();
        System.out.println(board);

    }
}
