package chess.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initColumn(0);

    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHT_COLUMN = initColumn(7);
    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] SEVENTH_ROW = initRow(48);

    public final static int NR_TILES = 64;
    public static final int NR_TILES_PER_ROW = 8;

    private static boolean[] initColumn(int columnNr) {
        final boolean[] column = new boolean[64];

        do {
            column[columnNr] = true;
            columnNr+= NR_TILES_PER_ROW;
        } while(columnNr< NR_TILES);
        return column;
    }

    private static boolean[] initRow(int rowNr){
        final boolean[] row = new boolean[NR_TILES];
        do {
            row[rowNr] = true;
            rowNr++;
        } while(rowNr % NR_TILES_PER_ROW != 0);
        return row;
    }


    private BoardUtils(){
        throw new RuntimeException("");
    }
    public static boolean ValidCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < NR_TILES;
    }
}
