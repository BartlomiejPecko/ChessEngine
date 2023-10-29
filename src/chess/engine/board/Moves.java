package chess.engine.board;

import chess.engine.pieces.Pawn;
import chess.engine.pieces.Piece;

public abstract class Moves {
    final Board board;
    final Piece movedPiece;
    final int pieceDestination;

    public static final Moves NULL_MOVE = new NullMove();

    private Moves(final Board board,
         final Piece movedPiece,
         final int pieceDestination){
             this.board = board;
             this.movedPiece = movedPiece;
             this.pieceDestination = pieceDestination;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;

        result = prime * result + this.pieceDestination;
        result = prime * result + this.movedPiece.hashCode();
        return result;
    }
    @Override
    public boolean equals(final Object other){
        if(this==other){
            return true;
        } if(!(other instanceof Moves)){
            return false;
        } final Moves otherMove = (Moves) other;
        return getPieceDestination() == otherMove.getPieceDestination()
                && getMovedPiece().equals(otherMove.getMovedPiece());
    }

    public int getCurrentCoordinate(){
        return this.movedPiece.getPiecePosition();
    }
    public int getPieceDestination(){
        return this.pieceDestination;
    }

    public Piece getMovedPiece(){
        return this.movedPiece;
    }

    public boolean isAttack(){
        return false;
    }
    public boolean isCastling(){
        return false;
    }
    public Piece getAttackedPiece(){
        return null;
    }



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

    public static final class PieceMove extends Moves{

        public PieceMove(final Board board, final Piece movedPiece, final int pieceDestination) {
            super(board, movedPiece, pieceDestination);
        }

    }
    public static class AttackMove extends Moves{

        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final int pieceDestination, final Piece attackedPiece) {
            super(board, movedPiece, pieceDestination);

        this.attackedPiece = attackedPiece;
        }


        @Override
        public int hashCode(){
            return this.attackedPiece.hashCode() + super.hashCode();
        }
        @Override
        public boolean equals(final Object other){
            if(this == other){
                return true;
            }
            if(!(other instanceof AttackMove)){
                return false;
            }
            final AttackMove otherAttackMove = (AttackMove) other;
            return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
        }
        @Override
        public Board execute() {
            return null;
        }

        @Override
        public boolean isAttack(){
            return true;

        }
        @Override
        public Piece getAttackedPiece(){
            return this.attackedPiece;
        }
    }

    public static final class PawnMove extends Moves{

        public PawnMove(final Board board, final Piece movedPiece, final int pieceDestination) {
            super(board, movedPiece, pieceDestination);
        }

    }
    public static class PawnAttackMove extends AttackMove{

        public PawnAttackMove(final Board board, final Piece movedPiece, final int pieceDestination, final Piece attackedPiece) {
            super(board, movedPiece, pieceDestination, attackedPiece);
        }

    }

    public static final class EnPassant extends AttackMove{

        public EnPassant(final Board board, final Piece movedPiece, final int pieceDestination, final Piece attackedPiece) {
            super(board, movedPiece, pieceDestination, attackedPiece);
        }

    }
    public static final class PawnJump extends Moves{

        public PawnJump(final Board board, final Piece movedPiece, final int pieceDestination) {
            super(board, movedPiece, pieceDestination);
        }
        @Override
        public Board execute(){
            final Board.Builder builder = new Board.Builder();
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!this.movedPiece.equals(piece)){
                    builder.setPiece(piece);
                }
            } for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                builder.setPiece(piece);
            }
            final Pawn movedPawn = (Pawn)this.movedPiece.movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }

    }

    static abstract class Castling extends Moves{

        public Castling(final Board board, final Piece movedPiece, final int pieceDestination) {
            super(board, movedPiece, pieceDestination);
        }
    }
    public static final class ShortCastle extends Castling{

        public ShortCastle(final Board board, final Piece movedPiece, final int pieceDestination) {
            super(board, movedPiece, pieceDestination);
        }
    }
    public static final class LongCastle extends Castling{

        public LongCastle(final Board board, final Piece movedPiece, final int pieceDestination) {
            super(board, movedPiece, pieceDestination);
        }
    }

    public static final class NullMove extends Moves{

        public NullMove() {
            super(null, null,-1);
        }
        @Override
        public Board execute(){
            throw new RuntimeException("");
        }

    }



    public static class MoveFactory{
        private MoveFactory(){
            throw new RuntimeException("");
        }
        public static Moves createMove(final Board board,
                                       final int currentCoordinate,
                                       final int pieceDestination){
            for(final Moves move : board.getAllLegalMoves()){
                if(move.getCurrentCoordinate()==currentCoordinate&& move.getPieceDestination()==pieceDestination){
                    return move;
                }
            } return NULL_MOVE;
        }
    }

}
