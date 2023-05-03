package chess.engine.pieces;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.Moves;
import java.util.List;
public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance; //black or white pieces

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    public abstract List<Moves> calcLegalMoves(final Board board);

}
