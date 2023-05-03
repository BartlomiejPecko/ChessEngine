package chess.engine.pieces;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.Moves;

import java.util.List;

public class Rook extends Piece{
    Rook(final int piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Moves> calcLegalMoves(Board board) {
        return null;
    }
}
