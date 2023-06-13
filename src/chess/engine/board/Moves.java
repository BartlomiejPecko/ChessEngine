package chess.engine.board;

import chess.engine.pieces.Piece;

public abstract class Moves {
    final Board board;
    final Piece movedPiece;
    final int pieceDestination;

    private Moves(final Board board,
         final Piece movedPiece,
         final int pieceDestination){
        this.board = board;
        this.movedPiece = movedPiece;
        this.pieceDestination = pieceDestination;
    }
    public int getPieceDestination(){
        return this.pieceDestination;
    }
    public static final class PieceMove extends Moves{

        public PieceMove(final Board board, final Piece movedPiece, final int pieceDestination) {
            super(board, movedPiece, pieceDestination);
        }
    }
    public static final class AttackMove extends Moves{

        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final int pieceDestination, final Piece attackedPiece) {
            super(board, movedPiece, pieceDestination);

        this.attackedPiece = attackedPiece;
        }
    }
}
