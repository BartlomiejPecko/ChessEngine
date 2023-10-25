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

    public Piece getMovedPiece(){
        return this.movedPiece;
    }

    public abstract Board execute();

    public static final class PieceMove extends Moves{

        public PieceMove(final Board board, final Piece movedPiece, final int pieceDestination) {
            super(board, movedPiece, pieceDestination);
        }

        @Override
        public Board execute() {
            final Board.Builder builder = new Board.Builder();
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!this.movedPiece.equals(piece)){
                    builder.setPiece(piece);
                }

            } for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();

        }
    }
    public static final class AttackMove extends Moves{

        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final int pieceDestination, final Piece attackedPiece) {
            super(board, movedPiece, pieceDestination);

        this.attackedPiece = attackedPiece;
        }

        @Override
        public Board execute() {
            return null;
        }
    }
}
