package chess.engine.pieces;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Moves;
import chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece{

    private final static int[] CANDIDATE_MOVES = {-9, -8, -7, -1, 1, 7, 8, 9};
    public King(final Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.KING, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Moves> calcLegalMoves(Board board) {
        final List<Moves> legalMoves = new ArrayList<>();

        for(final int candidateOffset : CANDIDATE_MOVES){
           final int destinationCoordinate = this.piecePosition + candidateOffset;

           if(edges1(this.piecePosition, candidateOffset) || edges8(this.piecePosition, candidateOffset)){
               continue;
           }

           if(BoardUtils.ValidCoordinate(destinationCoordinate)) {

               final Tile destinationTile = board.getTile(destinationCoordinate);

               if(!destinationTile.Occupied()) {
                   legalMoves.add(new Moves.PieceMove(board, this, destinationCoordinate));
               } else {

                   final Piece pieceAtDestination = destinationTile.getPiece();
                   final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                   if (this.pieceAlliance != pieceAlliance) {

                       legalMoves.add(new Moves.AttackMove(board, this, destinationCoordinate, pieceAtDestination));
                   }
               }
           }
        }



        return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public King movePiece(final Moves move) {
        return new King(move.getMovedPiece().getPieceAlliance(), move.getPieceDestination()); //creates new king onto moved location of prev one
    }

    @Override
    public String toString(){
        return PieceType.KING.toString();
    }
    private static boolean edges1(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 ||
                candidateOffset == 7) ;

    }
    private static boolean edges8(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 ||
                candidateOffset == 9);
    }
}
