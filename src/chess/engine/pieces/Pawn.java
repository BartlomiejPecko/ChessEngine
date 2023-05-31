package chess.engine.pieces;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Moves;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVES = {8, 16, 7, 9};
    public Pawn(final Alliance pieceAlliance, final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Moves> calcLegalMoves(Board board) {
        final List<Moves> legalMoves = new ArrayList<>();
        for(final int candidateOffset : CANDIDATE_MOVES) {
            int destinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * candidateOffset);

            if(!BoardUtils.ValidCoordinate(destinationCoordinate)) {
                continue;
            }
            if(candidateOffset == 8 && !board.getTile(destinationCoordinate).Occupied()) {
                legalMoves.add(new Moves.PieceMove(board, this, destinationCoordinate));
            } else if (candidateOffset == 16 && this.isFirstPawnMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {
                final int behindDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if(!board.getTile(behindDestinationCoordinate).Occupied() &&
                        !board.getTile(destinationCoordinate).Occupied()) {
                    legalMoves.add(new Moves.PieceMove(board, this, destinationCoordinate));
                }
            } else if(candidateOffset == 7 &&
                    !((BoardUtils.EIGHT_COLUMN[piecePosition] && this.pieceAlliance.isWhite() ||
                    (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {
                if(board.getTile(destinationCoordinate).Occupied()) {
                    final Piece pieceDestination = board.getTile(destinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceDestination.getPieceAlliance()) {
                        legalMoves.add(new Moves.PieceMove(board, this, destinationCoordinate));
                    }
                }
            } else if (candidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN[piecePosition] && this.pieceAlliance.isWhite() ||
                    (BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {
                if(board.getTile(destinationCoordinate).Occupied()) {
                    final Piece pieceDestination = board.getTile(destinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceDestination.getPieceAlliance()) {
                        legalMoves.add(new Moves.PieceMove(board, this, destinationCoordinate));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }
}
