public abstract class Tile {
    int tileCoordinate;
    Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }
    public abstract boolean Occupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile{
        EmptyTile(int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean Occupied(){
            return false;
        }
        @Override
        public Piece getPiece(){
            return null;
        }
    }
    public static final class Occupied extends Tile{
        Piece pieceOnTile;
        Occupied(int tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }
        @Override
        public boolean Occupied(){
            return true;
        }
        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
