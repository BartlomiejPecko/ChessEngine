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

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVES = {-17, -15, -10, -6, 6, 10, 15, 17};
    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Moves> calcLegalMoves(Board board) {
        int destinationCoordinate;
        List<Moves> legalMoves = new ArrayList<>();


        for(final int currentCandidate : CANDIDATE_MOVES)  {
            destinationCoordinate = this.piecePosition + currentCandidate;
            if(BoardUtils.ValidCoordinate(destinationCoordinate)) {

                if(edges1(this.piecePosition, currentCandidate) || edges2(this.piecePosition, currentCandidate) ||
                        edges7(this.piecePosition, currentCandidate) || edges8(this.piecePosition, currentCandidate)) {
                    continue;
                }

            final Tile destinationTile = board.getTile(destinationCoordinate);

            if(!destinationTile.Occupied()) {
                legalMoves.add(new Moves());
            } else {
                final Piece pieceAtDestination = destinationTile.getPiece();
                final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                if(this.pieceAlliance != pieceAlliance) {
                    legalMoves.add(new Moves());
                }
            }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }


    private static boolean edges1(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
                candidateOffset == 6 || candidateOffset == 15);

    }
    private static boolean edges2(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }

    private static boolean edges7(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
    }

    private static boolean edges8(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 ||
                candidateOffset == 10 || candidateOffset == 17);
    }

}
