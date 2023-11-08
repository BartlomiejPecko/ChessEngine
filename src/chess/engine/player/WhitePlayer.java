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

public class WhitePlayer extends Player{
    public WhitePlayer(final Board board,
                       final Collection<Moves> whiteDefaultMoves,
                       final Collection<Moves> blackDefaultMoves) {
        super(board, whiteDefaultMoves, blackDefaultMoves );
    }
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Moves> calculateKingCastles(final Collection<Moves> playerLegal, final Collection<Moves> opponentLegal) {
        final List<Moves> kingCastles = new ArrayList<>();
        if(this.playerKing.isFirstPawnMove() && !this.isCheck()){
            if(!this.board.getTile(61).Occupied() && !this.board.getTile(62).Occupied()){
                final Tile rookTile = this.board.getTile(63);
                if(rookTile.Occupied() && rookTile.getPiece().isFirstPawnMove()){
                    if(Player.calculateAttacksOnTile(61, opponentLegal).isEmpty() &&
                            Player.calculateAttacksOnTile(62, opponentLegal).isEmpty() &&
                    rookTile.getPiece().getPieceType().isRook()){
                        kingCastles.add(new Moves.ShortCastle(this.board,
                                this.playerKing, 62,
                                (Rook) rookTile.getPiece(),
                                rookTile.getTileCoordinate(), 61));
                    }
                }
            }
            if(!this.board.getTile(59).Occupied() &&
                    !this.board.getTile(58).Occupied() &&
                    !this.board.getTile(57).Occupied()){
                final Tile rookTile = this.board.getTile(56);
                if(rookTile.Occupied() && rookTile.getPiece().isFirstPawnMove()){
                    kingCastles.add(new Moves.LongCastle(this.board, this.playerKing, 58,
                            (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 59));
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
