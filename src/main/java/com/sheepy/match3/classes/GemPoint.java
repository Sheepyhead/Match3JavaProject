package com.sheepy.match3.classes;

import java.awt.*;

/**
 * Created by Troels "Sheepyhead" Jessen on 17/05/14.
 * Email: kairyuka@live.dk
 */
public class GemPoint extends Point implements Comparable<Point> {
    public GemPoint(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public GemPoint() {
        super();
    }

    @Override
    public int compareTo(Point other) {
        if (this.getY() > other.getY() ||
                (this.getY() == other.getY()
                        && this.getX() < other.getX()))
            return -1;
        else if (this.getY() == other.getY()
                && this.getX() == other.getX())
            return 0;
        else return 1;
    }
}
