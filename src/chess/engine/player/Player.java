package chess.engine.player;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.Moves;
import chess.engine.pieces.King;
import chess.engine.pieces.Piece;

import java.util.Collection;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Moves> legalMoves;

    Player(final Board board,
           final Collection<Moves> legalMoves,
           final Collection<Moves> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    private King establishKing() {
        for(final Piece piece : getActivePieces()) {
            if(piece.getPieceType().isKing()) {
                return (King) piece;
            }
        } throw new RuntimeException(" ");
    }

    public boolean isMoveLegal(final Moves move){
        return this.legalMoves.contains(move);
    }

    public boolean isCheck(){
        return false;
    }

    public boolean isCheckmate(){
        return false;
    }

    public boolean isStalemate(){
        return false;
    }

    public boolean isCastled(){
        return false;
    }

    public MoveTransition makeMove (final Moves move){
        return null;
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();


}
