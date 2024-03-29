package chess.engine.player;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.Moves;
import chess.engine.pieces.King;
import chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Moves> legalMoves;
    private final boolean isCheck;

    Player(final Board board,
           final Collection<Moves> legalMoves,
           final Collection<Moves> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves, calculateKingCastles(legalMoves, opponentMoves)));
        this.isCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }

    public King getPlayerKing() {
        return this.playerKing;
    }

    public Collection<Moves> getLegalMoves(){
        return this.legalMoves;
    }

    protected static Collection<Moves> calculateAttacksOnTile(int piecePosition, Collection<Moves> moves) {
        final List<Moves> attackMoves = new ArrayList<>();
        for(final Moves move : moves) {
            if(piecePosition == move.getPieceDestination()) {
                attackMoves.add(move);
            }
        }
        return ImmutableList.copyOf(attackMoves);
    }

    private King establishKing() {
        for(final Piece piece : getActivePieces()) {
            if(piece.getPieceType().isKing()) {
                return (King) piece;
            }
        } throw new RuntimeException(" no king ");
    }

    public boolean isMoveLegal(final Moves move){
        return this.legalMoves.contains(move);
    }

    public boolean isCheck(){
        return this.isCheck;
    }

    public boolean isCheckmate(){
        return this.isCheck && !hasMoves();
    }

    protected boolean hasMoves() {
        for(final Moves move : this.legalMoves){
            final MoveTransition transition = makeMove(move);
            if(transition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    public boolean isStalemate(){
        return !this.isCheck && !hasMoves();
    }

    public boolean isCastled(){
        return false;
    }

    public MoveTransition makeMove (final Moves move){
        if(!isMoveLegal(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.execute();

        final Collection<Moves> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
                transitionBoard.currentPlayer().getLegalMoves());

        if(!kingAttacks.isEmpty()){
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    protected abstract Collection<Moves> calculateKingCastles(Collection<Moves> playerLegal, Collection<Moves>opponentLegal);

}
