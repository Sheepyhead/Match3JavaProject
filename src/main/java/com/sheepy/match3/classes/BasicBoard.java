package com.sheepy.match3.classes;

import com.sheepy.match3.enums.GemType;
import com.sheepy.match3.interfaces.Board;
import com.sheepy.match3.interfaces.Gem;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Troels "Sheepyhead" Jessen on 17/05/14.
 * Email: kairyuka@live.dk
 */
public class BasicBoard implements Board {
    private Map<Point, Gem> gems;

    private int width;
    private int height;

    public BasicBoard(int width, int height) {
        this.width = width;
        this.height = height;

        gems = new HashMap<Point, Gem>();

        populate();
    }

    private void populate() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point point = new Point(x, y);
                Gem gem = new BasicGem(point, GemType.test);
                gems.put(point, gem);
            }
        }
    }

    @Override
    public Map<Point, Gem> getGems() {
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
    public void move(Point from, Point to) {
        Gem fromGem = gems.get(from).copy();
        Gem toGem = null;
        if (gems.containsKey(to) && gems.get(to) != null)
        {
            toGem = gems.get(to).copy();
        }

        gems.put(to, fromGem);
        if (toGem != null)
        {
            gems.put(from, toGem);
        } else {
            gems.remove(from);
        }

    }

    @Override
    public Gem getGem(Point gemPosition) {
        return gems.get(gemPosition);
    }

    @Override
    public void removeGem(Point gemPosition) {
        gems.remove(gemPosition);

        for (int y = ((int) gemPosition.getY()) - 1; y >= 0; y--) {
            Point from = new Point((int) gemPosition.getX(), y);
            Point to = new Point((int) gemPosition.getX(), y + 1);

            move(from,to);
        }
        Point top = new Point((int) gemPosition.getX(), 0);
        Gem gem = new BasicGem(top);
        gems.put(top, gem);
    }
}
