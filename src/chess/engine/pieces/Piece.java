package chess.engine.pieces;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.Moves;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance; //black or white pieces
    protected final boolean isFirstPawnMove;

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstPawnMove = false;
    }
    public int getPiecePosition(){
        return this.piecePosition;
    }

    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }
    public boolean isFirstPawnMove(){
        return this.isFirstPawnMove;
    }

    public abstract Collection<Moves> calcLegalMoves(final Board board);

}
