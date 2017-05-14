package com.sheepy.match3.classes;

import com.sheepy.match3.enums.GemType;
import com.sheepy.match3.interfaces.Board;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Troels "Sheepyhead" Jessen on 17/05/14.
 * Email: kairyuka@live.dk
 */
public class BasicBoardTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new BasicBoard(10, 10);
    }

    @Test
    public void boardShouldHaveDimensions() throws Exception {
        assertEquals(10, board.getWidth());
        assertEquals(10, board.getWidth());
    }

    @Test
    public void boardShouldHaveGems() throws Exception {
        assertFalse(board.getGems().isEmpty());
    }

    @Test
    public void boardShouldHaveGemsInAllSpots() throws Exception {
        assertNotNull(board.getGem(new Point(5, 5)));
        assertNotNull(board.getGem(new Point(7, 6)));
        assertNotNull(board.getGem(new Point(0, 0)));
    }

    @Test
    public void movingGemShouldSwapGems() throws Exception {
        Point from = new Point(),
                to = new Point(1, 2);
        board.getGem(from).setType(GemType.nottest);

        board.move(from, to);

        assertEquals(GemType.nottest, board.getGem(to).getType());
        assertEquals(GemType.test, board.getGem(from).getType());
    }

    @Test
    public void removingAGemShouldMakeTheGemAboveItDropDownInItsPlace() throws Exception {
        Point above = new Point(9,8);
        Point below = new Point(9,9);

        board.getGem(above).setType(GemType.nottest);

        board.removeGem(below);

        assertEquals(board.getGem(below).getType(), GemType.nottest);

    }

    @Test
    public void removingAGemShouldPlaceNewGemOnTopRow() throws Exception {
        Point bottom = new Point(9,9);
        Point top = new Point(9,0);

        board.removeGem(bottom);

        assertNotNull(board.getGem(top));
    }
}