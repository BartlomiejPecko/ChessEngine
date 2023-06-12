package chess.engine.board;

import chess.engine.Alliance;
import chess.engine.pieces.*;
import chess.engine.player.BlackPlayer;
import chess.engine.player.Player;
import chess.engine.player.WhitePlayer;
import com.google.common.collect.ImmutableList;

import java.util.*;

public class Board {

    private final List<Tile> chessboard;
    private final Collection<Piece>whitePieces;
    private final Collection<Piece>blackPieces;

    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;

    private Board(Builder builder){
        this.chessboard = createChessboard(builder);
        this.whitePieces = calculateActivePieces(this.chessboard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.chessboard, Alliance.BLACK);

        final Collection<Moves> whiteDefaultMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Moves> blackDefaultMoves = calculateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteDefaultMoves, blackDefaultMoves);
        this.blackPlayer = new BlackPlayer(this, whiteDefaultMoves, blackDefaultMoves);
    }

    @Override //what board looks like when turned into string
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < BoardUtils.NR_TILES; i++) {
            final String tileText = this.chessboard.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if((i+1) % BoardUtils.NR_TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public Player whitePlayer(){
        return this.whitePlayer;
    }

    public Player blackPlayer(){
        return this.blackPlayer;
    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public Collection<Piece> getWhitePieces(){
        return this.whitePieces;
    }

    private Collection<Moves> calculateLegalMoves(final Collection<Piece> pieces) {
        final List<Moves> legalMoves = new ArrayList<>();
        for (final Piece piece : pieces) {
           legalMoves.addAll(piece.calcLegalMoves(this));
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static Collection<Piece> calculateActivePieces(final List<Tile> chessboard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();
        for(final Tile tile: chessboard){
            if(tile.Occupied()){
                final Piece piece = tile.getPiece();
                if(piece.getPieceAlliance()== alliance) {
                    activePieces.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(activePieces);
    }

    public Tile getTile(final int tileCoordinate) {
        return chessboard.get(tileCoordinate);
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

        final Builder builder = new Builder();

        builder.setPiece(new Rook(Alliance.BLACK, 0)); //a8 tile
        builder.setPiece(new Knight(Alliance.BLACK, 1));
        builder.setPiece(new Bishop(Alliance.BLACK, 2));
        builder.setPiece(new Queen(Alliance.BLACK, 3));
        builder.setPiece(new King(Alliance.BLACK, 4)); //e8 tile
        builder.setPiece(new Bishop(Alliance.BLACK, 5));
        builder.setPiece(new Knight(Alliance.BLACK, 6));
        builder.setPiece(new Rook(Alliance.BLACK, 7));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 9));
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 11));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 13));
        builder.setPiece(new Pawn(Alliance.BLACK, 14));
        builder.setPiece(new Pawn(Alliance.BLACK, 15));


        builder.setPiece(new Pawn(Alliance.WHITE, 48));
        builder.setPiece(new Pawn(Alliance.WHITE, 49));
        builder.setPiece(new Pawn(Alliance.WHITE, 50));
        builder.setPiece(new Pawn(Alliance.WHITE, 51));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 53));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new Rook(Alliance.WHITE, 56));
        builder.setPiece(new Knight(Alliance.WHITE, 57));
        builder.setPiece(new Bishop(Alliance.WHITE, 58));
        builder.setPiece(new Queen(Alliance.WHITE, 59));
        builder.setPiece(new King(Alliance.WHITE, 60)); //e1 tile
        builder.setPiece(new Bishop(Alliance.WHITE, 61));
        builder.setPiece(new Knight(Alliance.WHITE, 62));
        builder.setPiece(new Rook(Alliance.WHITE, 63)); //h1 tile

        builder.setMoveMaker(Alliance.WHITE);
        return builder.build();

    }

    public static class Builder{
        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;
        public Builder(){
            this.boardConfig = new HashMap<>();
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
