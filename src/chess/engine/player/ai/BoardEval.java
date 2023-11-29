package chess.engine.player.ai;

import chess.engine.board.Board;

public interface BoardEval {
    int evaluate(Board board, int depth);
}
