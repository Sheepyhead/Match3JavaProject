package com.sheepy.match3.interfaces;

/**
 * Created by Troels "Sheepyhead" Jessen on 17/05/14.
 * Email: kairyuka@live.dk
 */

import com.sheepy.match3.classes.GemPoint;

import java.util.Map;
import java.util.SortedSet;

/**
 * The board is the object holding gems and handling their mechanics.
 */
public interface Board {

    /**
     * Accessor for the map of gems currently in the board.
     *
     * @return A map of the gems on the board.
     */
    Map<GemPoint, Gem> getGems();

    /**
     * Accessor for the width of the board.
     *
     * @return The width of the board.
     */
    int getWidth();


    /**
     * Accessor for the height of the board.
     *
     * @return The height of the board.
     */
    int getHeight();

    /**
     * Randomizes the positions of all gems on the board.
     */
    void shuffle();

    /**
     * Moves a gem on the board.
     *  @param from The position of the gem moved.
     * @param to   The destination of the gem. If a gem is already on this position it is moved to the from position.
     */
    void move(GemPoint from, GemPoint to);

    /**
     * Accessor for a single gem on the board.
     *
     * @param gemPosition Coordinates for the gem.
     * @return The gem at the position requested.
     */
    Gem getGem(GemPoint gemPosition);

    /**
     * Removes the gem at the position.
     *
     * @param gemPosition Position of the gem to be removed.
     */
    void removeGem(GemPoint gemPosition);

    /**
     * Removes multiple gems at the same time, unlike multiple calls to {@link #removeGem(GemPoint) removeGem}.
     *
     * @param gemPositions The positions of the gems to remove.
     */
    void removeGems(SortedSet<GemPoint> gemPositions);
}
