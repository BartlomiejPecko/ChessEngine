package chess.engine.player.ai;

import chess.engine.board.Board;
import chess.engine.board.Moves;
import chess.engine.player.MoveTransition;

public class MiniMax implements MoveStrat{

    private final BoardEval boardEvaluator;

    public MiniMax(){
        this.boardEvaluator = null;
    }

    @Override
    public String toString(){
        return "MM";
    }

    @Override
    public Moves execute(Board board, int depth) {



        return null;
    }

    public int min(final Board board,
                   final int depth) {
        if (depth == 0) {
            return this.boardEvaluator.evaluate(board, depth);
        }
        int lowestSeenValue = Integer.MAX_VALUE;
        for (final Moves move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if (moveTransition.getMoveStatus().isDone()) {
                final int currentValue = max(moveTransition.getTransitionBoard(), depth - 1);
                //todo
            }
        }
    }
    public int max(final Board board,
                   final int depth){

    }

}
