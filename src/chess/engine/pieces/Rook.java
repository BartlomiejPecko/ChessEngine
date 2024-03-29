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

public class Rook extends Piece{

    private final static int[] CANDIDATE_MOVES = {-8, -1, 1, 8};
    public Rook(final Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.ROOK, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Moves> calcLegalMoves(final Board board) {
        final List<Moves> legalMoves = new ArrayList<>();

        for(final int coordinateOffset: CANDIDATE_MOVES){
            int destinationCoordinate = this.piecePosition;
            while(BoardUtils.ValidCoordinate(destinationCoordinate)) {

                if(exclusion1(destinationCoordinate, coordinateOffset) ||
                        exclusion8(destinationCoordinate, coordinateOffset)) {
                    break;
                }

                destinationCoordinate += coordinateOffset;

                if(BoardUtils.ValidCoordinate(destinationCoordinate)){
                    final Tile destinationTile = board.getTile(destinationCoordinate);

                    if(!destinationTile.Occupied()) {
                        legalMoves.add(new Moves.PieceMove(board, this, destinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = destinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Moves.AttackMove(board, this, destinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }

                }
            }
        }


        return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public Rook movePiece(final Moves move) {
        return new Rook(move.getMovedPiece().getPieceAlliance(), move.getPieceDestination()); //creates new rook onto moved location of prev one
    }
    @Override
    public String toString(){
        return PieceType.ROOK.toString();
    }
    private static boolean exclusion1(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
    }
    private static boolean exclusion8(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == 1);
    }
}

