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
        Point point = new Point(5,5);
        Gem gem = new BasicGem(point, GemType.test);

        assertEquals(point, gem.getCoordinates());
        assertEquals(point.getX(), gem.getX(), .5);
        assertEquals(point.getY(), gem.getY(), .5);
    }

    @Test
    public void gemChangesCoordinatesWhenMoved() throws Exception {
        Point from = new Point(5,5);
        Point to = new Point(4,4);

        Gem gem = new BasicGem(from, GemType.test);

        gem.move(to);

        assertEquals(gem.getCoordinates(), to);

    }

    @Test
    public void gemHasAType() throws Exception {
        Point point = new Point(0,0);
        Gem gem = new BasicGem(point, GemType.test);

        assertEquals(gem.getType(), GemType.test);
    }

    @Test
    public void gemTypeCanBeChanged() throws Exception {
        Point point = new Point();
        Gem gem = new BasicGem(point, GemType.test);

        gem.setType(GemType.nottest);

        assertEquals(gem.getType(), GemType.nottest);
    }

    @Test
    public void gemHasNoStates() throws Exception {
        Point point = new Point(0,0);
        Gem gem = new BasicGem(point, GemType.test);

        assertTrue(gem.getStates().isEmpty());
    }

    @Test
    public void gemCanHaveStatesAdded() throws Exception {
        Point point = new Point();
        Gem gem = new BasicGem(point, GemType.test);

        gem.addState(GemState.test);

        assertTrue(gem.getStates().contains(GemState.test));
        assertTrue(gem.hasState(GemState.test));
    }

    @Test
    public void gemCanHaveStatesRemoved() throws Exception {
        Point point = new Point();
        Gem gem = new BasicGem(point, GemType.test);

        gem.addState(GemState.test);

        gem.removeState(GemState.test);

        assertFalse(gem.getStates().contains(GemState.test));
        assertFalse(gem.hasState(GemState.test));
    }

}