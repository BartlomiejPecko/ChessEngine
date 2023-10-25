package chess.engine.pieces;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.Moves;

import java.util.Collection;
import java.util.Objects;

public abstract class Piece {

    protected final PieceType pieceType;

    protected final int piecePosition;
    protected final Alliance pieceAlliance; //black or white pieces
    protected final boolean isFirstPawnMove;
    private final int cachedHashCode;

    Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstPawnMove = false;
        this.cachedHashCode = computeHashCode();
    }
    private int computeHashCode(){
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstPawnMove ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(final Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof Piece)){
            return false;
        } final Piece otherPiece = (Piece) other;
        return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() &&
                pieceAlliance == otherPiece.getPieceAlliance() && isFirstPawnMove == otherPiece.isFirstPawnMove();
    }
    @Override
    public int hashCode(){
        return this.cachedHashCode;
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

    public PieceType getPieceType(){
        return this.pieceType;
    }

    public abstract Collection<Moves> calcLegalMoves(final Board board);
    public abstract Piece movePiece(Moves move);

    public enum PieceType{


        PAWN("P") {
            @Override
            public boolean isKing(){
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        };

        private String pieceName;
        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }
        @Override
        public String toString(){
            return this.pieceName;
        }
        public abstract boolean isKing();
    }

}
