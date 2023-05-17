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

public class Bishop extends Piece{

    private final static int[] CANDIDATE_VECTOR_COORDINATES = {-9, -7, 7, 9};
    Bishop(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Moves> calcLegalMoves(final Board board) {
        final List<Moves> legalMoves = new ArrayList<>();

        for(final int coordinateOffset: CANDIDATE_VECTOR_COORDINATES){
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
    private static boolean exclusion1(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
    }
    private static boolean exclusion8(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
    }
}
