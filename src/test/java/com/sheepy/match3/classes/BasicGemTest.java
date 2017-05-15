package com.sheepy.match3.classes;

import com.sheepy.match3.enums.GemState;
import com.sheepy.match3.enums.GemType;
import com.sheepy.match3.interfaces.Gem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Troels "Sheepyhead" Jessen on 17/05/14.
 * Email: kairyuka@live.dk
 */
public class BasicGemTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void gemHasCoordinates() throws Exception {
        GemPoint point = new GemPoint(5, 5);
        Gem gem = new BasicGem(point, GemType.test);

        assertEquals(point, gem.getCoordinates());
        assertEquals((int) point.getX(), gem.getX());
        assertEquals((int) point.getY(), gem.getY());
    }

    @Test
    public void gemChangesCoordinatesWhenMoved() throws Exception {
        GemPoint from = new GemPoint(5, 5);
        GemPoint to = new GemPoint(4, 4);

        Gem gem = new BasicGem(from, GemType.test);

        gem.move(to);

        assertEquals(gem.getCoordinates(), to);

    }

    @Test
    public void gemHasAType() throws Exception {
        GemPoint point = new GemPoint(0, 0);
        Gem gem = new BasicGem(point, GemType.test);

        assertEquals(gem.getType(), GemType.test);
    }

    @Test
    public void gemTypeCanBeChanged() throws Exception {
        GemPoint point = new GemPoint();
        Gem gem = new BasicGem(point, GemType.test);

        gem.setType(GemType.nottest);

        assertEquals(gem.getType(), GemType.nottest);
    }

    @Test
    public void gemHasNoStates() throws Exception {
        GemPoint point = new GemPoint(0, 0);
        Gem gem = new BasicGem(point, GemType.test);

        assertTrue(gem.getStates().isEmpty());
    }

    @Test
    public void gemCanHaveStatesAdded() throws Exception {
        GemPoint point = new GemPoint();
        Gem gem = new BasicGem(point, GemType.test);

        gem.addState(GemState.test);

        assertTrue(gem.getStates().contains(GemState.test));
        assertTrue(gem.hasState(GemState.test));
    }

    @Test
    public void gemCanHaveStatesRemoved() throws Exception {
        GemPoint point = new GemPoint();
        Gem gem = new BasicGem(point, GemType.test);

        gem.addState(GemState.test);

        gem.removeState(GemState.test);

        assertFalse(gem.getStates().contains(GemState.test));
        assertFalse(gem.hasState(GemState.test));
    }

    @Test
    public void gemLowerOnTheBoardIsSortedLower() throws Exception {
        GemPoint above = new GemPoint();
        GemPoint below = new GemPoint(5, 9);
        GemPoint left = new GemPoint(2, 9);
        GemPoint right = new GemPoint(9, 9);

        Gem aboveGem = new BasicGem(above);
        Gem belowGem = new BasicGem(below);
        Gem leftGem = new BasicGem(left);
        Gem rightGem = new BasicGem(right);

        assertEquals(-1, belowGem.compareTo(aboveGem));
        assertEquals(1, aboveGem.compareTo(belowGem));
        assertEquals(-1, belowGem.compareTo(rightGem));
        assertEquals(1, belowGem.compareTo(leftGem));
    }

}