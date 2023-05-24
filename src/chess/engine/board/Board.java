package chess.engine.board;

import chess.engine.Alliance;
import chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;

public class Board {

    private final List<Tile> chessboard;
    private Board(Builder builder){
        this.chessboard = createChessboard(builder);
    }

    public Tile getTile(final int tileCoordinate) {
        return null;
    }
    private static List<Tile> createChessboard(final Builder builder){
        final Tile[] tiles = new Tile[BoardUtils.NR_TILES];
        for(int i=0; i<BoardUtils.NR_TILES; i++) {
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static Board createStartingPosition(){
        //method that will create the initial position of chess
    }

    public static class Builder{
        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;
        public Builder(){

        }
        public Builder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }
        public Builder setMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }
        public Board build(){
            return new Board(this);
        }
    }
}
