package chess.engine.player.ai;

import chess.engine.board.Board;
import chess.engine.board.Moves;

public interface MoveStrat {

    Moves execute(Board board, int depth);

}
