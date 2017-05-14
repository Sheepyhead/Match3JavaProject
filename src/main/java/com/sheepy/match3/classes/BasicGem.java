package com.sheepy.match3.classes;

import com.sheepy.match3.enums.GemState;
import com.sheepy.match3.enums.GemType;
import com.sheepy.match3.interfaces.Gem;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Troels "Sheepyhead" Jessen on 17/05/14.
 * Email: kairyuka@live.dk
 */
public class BasicGem implements Gem {

    // Holds the coordinates of the gem
    private Point coordinates;

    // Holds the type of the gem
    private GemType type;

    // Holds the states of the gem, if any
    private Set<GemState> states;

    public BasicGem(Point point, GemType type) {
        coordinates = point;
        this.type = type;

        states = new HashSet<GemState>();
    }

    public BasicGem(Point point) {
        coordinates = point;
        type = GemType.test;

        states = new HashSet<GemState>();
    }

    @Override
    public int getX() {
        return (int) coordinates.getX();
    }

    @Override
    public int getY() {
        return (int) coordinates.getY();
    }

    @Override
    public Point getCoordinates() {
        return coordinates;
    }

    @Override
    public void move(Point to) {
        coordinates = to;
    }

    @Override
    public GemType getType() {
        return type;
    }

    @Override
    public Set<GemState> getStates() {
        return states;
    }

    @Override
    public boolean hasState(GemState state) {
        return states.contains(state);
    }

    @Override
    public void setType(GemType type) {
        this.type = type;
    }

    @Override
    public void addState(GemState state) {
        states.add(state);
    }

    @Override
    public void removeState(GemState state) {
        states.remove(state);
    }

    @Override
    public void addStates(Set<GemState> states) {
        this.states.addAll(states);
    }

    @Override
    public void setStates(Set<GemState> states) {
        this.states = states;
    }

    @Override
    public void removeStates(Set<GemState> states) {
        this.states.removeAll(states);
    }

    @Override
    public Gem copy() {
        Gem clone = new BasicGem(coordinates, type);
        clone.setStates(states);

        return clone;
    }
}
