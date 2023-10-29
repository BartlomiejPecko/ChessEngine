package chess.engine.board;

import chess.engine.Alliance;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testCreateStartingPosition() {
        Board board = Board.createStartingPosition();
        assertEquals(board.whitePlayer().getAlliance(), Alliance.WHITE);
        assertEquals(board.blackPlayer().getAlliance(), Alliance.BLACK);
        assertEquals(board.currentPlayer().getAlliance(), Alliance.WHITE);
    }
}
