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
        assertNotNull(board.getGem(new GemPoint(5, 5)));
        assertNotNull(board.getGem(new GemPoint(7, 6)));
        assertNotNull(board.getGem(new GemPoint(0, 0)));
    }

    @Test
    public void movingGemShouldSwapGems() throws Exception {
        GemPoint from = new GemPoint(),
                to = new GemPoint(1, 2);

        GemType fromType = board.getGem(from).getType();
        GemType toType = board.getGem(to).getType();

        board.move(from, to);

        assertEquals(fromType, board.getGem(to).getType());
        assertEquals(toType, board.getGem(from).getType());
    }

    @Test
    public void removingAGemShouldMakeTheGemAboveItDropDownInItsPlace() throws Exception {
        GemPoint above = new GemPoint(9, 8);
        GemPoint below = new GemPoint(9, 9);

        board.getGem(above).setType(GemType.nottest);

        board.removeGem(below);

        assertEquals(GemType.nottest, board.getGem(below).getType());

    }

    @Test
    public void removingAGemShouldPlaceNewGemOnTopRow() throws Exception {
        GemPoint bottom = new GemPoint(9, 9);
        GemPoint top = new GemPoint(9, 0);

        board.removeGem(bottom);

        assertNotNull(board.getGem(top));
    }

    @Test
    public void boardShouldNotSpawnTestTypes() throws Exception {
        GemPoint point = new GemPoint();
        assertNotEquals(board.getGem(point).getType(), GemType.test);
        assertNotEquals(board.getGem(point).getType(), GemType.nottest);
    }

    @Test
    public void puttingThreeGemsInAVerticalLineShouldRemoveTheGems() {
        GemPoint point1 = new GemPoint(9, 9);
        GemPoint point2 = new GemPoint(9, 8);
        GemPoint point3 = new GemPoint(8, 7);
        GemPoint point4 = new GemPoint(9, 7);

        board.getGem(point1).setType(GemType.test);
        board.getGem(point2).setType(GemType.test);
        board.getGem(point3).setType(GemType.test);


        board.move(point3, point4);

        assertNotEquals(GemType.test, board.getGem(point1).getType());
        assertNotEquals(GemType.test, board.getGem(point2).getType());
        assertNotEquals(GemType.test, board.getGem(point3).getType());
        assertNotEquals(GemType.test, board.getGem(point4).getType());

        assertNotNull(board.getGem(new GemPoint(9, 0)));
        assertNotNull(board.getGem(new GemPoint(9, 1)));
        assertNotNull(board.getGem(new GemPoint(9, 2)));
    }

    @Test
    public void puttingThreeGemsInAHorizontalLineShouldRemoveTheGems() {
        GemPoint point1 = new GemPoint(9, 9);
        GemPoint point2 = new GemPoint(8, 9);
        GemPoint point3 = new GemPoint(7, 8);
        GemPoint point4 = new GemPoint(7, 9);

        board.getGem(point1).setType(GemType.test);
        board.getGem(point2).setType(GemType.test);
        board.getGem(point3).setType(GemType.test);

        board.move(point3, point4);

        assertNotEquals(GemType.test, board.getGem(point1).getType());
        assertNotEquals(GemType.test, board.getGem(point2).getType());
        assertNotEquals(GemType.test, board.getGem(point3).getType());
        assertNotEquals(GemType.test, board.getGem(point4).getType());

        assertNotNull(board.getGem(new GemPoint(9, 0)));
        assertNotNull(board.getGem(new GemPoint(8, 0)));
        assertNotNull(board.getGem(new GemPoint(7, 0)));
    }
}