package chess.engine.player;

import chess.engine.board.Board;
import chess.engine.board.Moves;
import chess.engine.pieces.Piece;

import java.util.Collection;

public class BlackPlayer extends Player{
    public BlackPlayer(Board board, Collection<Moves> whiteDefaultMoves, Collection<Moves> blackDefaultMoves) {
        super(board, blackDefaultMoves, whiteDefaultMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }
}
