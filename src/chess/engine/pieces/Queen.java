package chess.engine.pieces;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.Moves;

import java.util.Collection;

public class Queen extends Piece{
    Queen(final int piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Moves> calcLegalMoves(Board board) {
        return null;
    }
}
