package chess.engine.player;

import chess.engine.Alliance;
import chess.engine.board.Board;
import chess.engine.board.Moves;
import chess.engine.board.Tile;
import chess.engine.pieces.Piece;
import chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player{
    public BlackPlayer(final Board board,
                       final Collection<Moves> whiteDefaultMoves,
                       final Collection<Moves> blackDefaultMoves) {
        super(board, blackDefaultMoves, whiteDefaultMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Moves> calculateKingCastles(final Collection<Moves> playerLegal, final Collection<Moves> opponentLegal) {
        final List<Moves> kingCastles = new ArrayList<>();
        if(this.playerKing.isFirstPawnMove() && !this.isCheck()){
            if(!this.board.getTile(5).Occupied() && !this.board.getTile(6).Occupied()){
                final Tile rookTile = this.board.getTile(7);
                if(rookTile.Occupied() && rookTile.getPiece().isFirstPawnMove()){
                    if(Player.calculateAttacksOnTile(5, opponentLegal).isEmpty() &&
                            Player.calculateAttacksOnTile(6, opponentLegal).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()){
                        kingCastles.add(new Moves.ShortCastle(this.board, this.playerKing, 6,
                                (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 5));
                    }
                }
            }
            if(!this.board.getTile(1).Occupied() &&
                    !this.board.getTile(2).Occupied() &&
                    !this.board.getTile(3).Occupied()){
                final Tile rookTile = this.board.getTile(0);
                if(rookTile.Occupied() && rookTile.getPiece().isFirstPawnMove()){
                    kingCastles.add(new Moves.LongCastle(this.board, this.playerKing, 2,
                            (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 3));
                }
            }
        }

        return ImmutableList.copyOf(kingCastles);
    }
}
