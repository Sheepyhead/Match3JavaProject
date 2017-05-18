package com.sheepy.match3.classes;

import com.sheepy.match3.enums.GemType;
import com.sheepy.match3.interfaces.Board;
import com.sheepy.match3.interfaces.Gem;

import java.awt.*;
import java.util.*;

/**
 * Created by Troels "Sheepyhead" Jessen on 17/05/14.
 * Email: kairyuka@live.dk
 */
public class BasicBoard implements Board {
    private Map<GemPoint, Gem> gems;

    private int width;
    private int height;
    private Random r;

    public BasicBoard(int width, int height) {
        this.width = width;
        this.height = height;

        gems = new HashMap<>();
        r = new Random();

        populate();
    }

    /**
     * Fills the board with gems of random types
     */
    private void populate() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GemPoint point = new GemPoint(x, y);
                GemType type = getRandomType();

                Gem gem = new BasicGem(point, type);
                gems.put(point, gem);
            }
        }
    }

    private GemType getRandomType() {
        return Game.TYPE_LIST[r.nextInt(Game.TYPE_LIST.length)];
    }

    @Override
    public Map<GemPoint, Gem> getGems() {
        return gems;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void shuffle() {

    }

    @Override
    public void move(GemPoint from, GemPoint to) {
        System.out.println("[DEBUG] Moving gem at " + from + " to " + to);
        Gem fromGem = gems.get(from).copy();
        Gem toGem = null;
        if (gems.containsKey(to) && gems.get(to) != null) {
            toGem = gems.get(to).copy();
        }

        gems.put(to, fromGem);
        fromGem.move(to);

        if (toGem != null) {
            gems.put(from, toGem);
            toGem.move(from);
            checkForLines(from);
        } else {
            gems.put(from,null);
        }

        checkForLines(to);
    }

    @SuppressWarnings("Duplicates")
    private void checkForLines(GemPoint point) {
        if (gems.values().contains(null)) return;
        System.out.println("[DEBUG] Checking for lines centered on point " + point);
        SortedSet<GemPoint> verticalPoints = new TreeSet<>();
        SortedSet<GemPoint> horizontalpoints = new TreeSet<>();

        /**
         * TODO: check current gem type, if same as previous add to temp list
         * else save temp list if fewer than 3, or add temp list points to final list if
         * more than 3 and start temp list over with new gem
         */
        SortedSet<Gem> tempSet = new TreeSet<>();
        System.out.println("[DEBUG] Checking vertical lines");
        for (int yVar = height - 1; yVar >= 0; yVar--) {
            GemPoint here = new GemPoint((int) point.getX(), yVar);
            System.out.println("[DEBUG] Checking point " + here);
            Gem thisGem = gems.get(here);

            // If there is no prior gem, or the set contains gems of the same type, and it's not the last gem in the line
            if ((tempSet.isEmpty() || tempSet.first().getType() == thisGem.getType()) && yVar != 0) {
                // Add the gem to the set
                if (thisGem != null)
                    tempSet.add(thisGem);
            } else {
                // Check if there are any rows worth scoring
                if (tempSet.size() >= 3) {
                    // If there is, add them to the final list of scoring gems
                    for (Gem gem : tempSet) {
                        verticalPoints.add(gem.getCoordinates());
                    }
                }
                tempSet.clear();
            }
        }

        tempSet.clear();

        System.out.println("[DEBUG] Checking horizontal lines");
        for (int xVar = width - 1; xVar >= 0; xVar--) {
            GemPoint here = new GemPoint(xVar, (int) point.getY());
            System.out.println("[DEBUG] Checking point  " + here);
            Gem thisGem = gems.get(here);
            // If there is no prior gem, or the set contains gems of the same type, and it's not the last gem in the line
            if ((tempSet.isEmpty() || tempSet.first().getType() == thisGem.getType()) && xVar != 0) {
                // Add the gem to the set
                tempSet.add(thisGem);
            } else {
                // Check if there are any rows worth scoring
                if (tempSet.size() >= 3) {
                    // If there is, add them to the final list of scoring gems
                    for (Gem gem : tempSet) {
                        horizontalpoints.add(gem.getCoordinates());
                    }
                }
                tempSet.clear();
            }
        }

        SortedSet<GemPoint> finalSet = horizontalpoints;
        finalSet.addAll(verticalPoints);

        removeGems(finalSet);
    }

    @Override
    public Gem getGem(GemPoint gemPosition) {
        return gems.get(gemPosition);
    }

    @Override
    public void removeGem(GemPoint gemPosition) {
        SortedSet<GemPoint> set = new TreeSet<GemPoint>();
        set.add(gemPosition);
        removeGems(set);
    }

    @Override
    public void removeGems(SortedSet<GemPoint> gemPositions) {
        System.out.println("[DEBUG] Removing gems at points " + gemPositions);
        SortedSet<Integer> columns = new TreeSet<>();

        for (GemPoint p : gemPositions) {
            gems.put(p,null);
            columns.add((int) p.getX());
        }

        dropGems(columns);
    }

    private void dropGems(SortedSet<Integer> columns) {
        System.out.println("[DEBUG] Dropping gems at colums " + columns);
        for (int x : columns) {
            for (int y = height - 1; y >= 0; y--) {
                GemPoint here = new GemPoint(x, y);

                // If there is no gem here
                if (!(gems.containsKey(here) && gems.get(here) != null)) {
                    if (y != 0) {
                        Gem aboveGem = null;
                        // Look up the column until a gem is found
                        for (int localY = y - 1; localY >= 0; localY--) {
                            GemPoint localPoint = new GemPoint(x, localY);
                            // If a gem exists at this point
                            if (gems.containsKey(localPoint) && gems.get(localPoint) != null) {
                                aboveGem = gems.get(localPoint);
                                break;
                            }
                        }
                        // If no gems are above this space
                        if (aboveGem == null) {
                            // Put a gem on the top and move it here
                            GemPoint top = new GemPoint(x, 0);
                            gems.put(top, new BasicGem(top, getRandomType()));
                            move(top, here);
                        } else {
                            // If a gem exists above this space, move it to here
                            move(aboveGem.getCoordinates(), here);
                        }
                    } else {
                        gems.put(here, new BasicGem(here, getRandomType()));
                    }
                }
            }
        }
    }
}
