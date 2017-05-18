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
public class BasicGem implements Gem, Comparable<Gem> {

    // Holds the coordinates of the gem
    private GemPoint coordinates;

    // Holds the type of the gem
    private GemType type;

    // Holds the states of the gem, if any
    private Set<GemState> states;

    /**
     * Constructs a gem with the given coordinates and the given type
     * @param point Coordinates for the gem
     * @param type Type of the gem
     */
    public BasicGem(GemPoint point, GemType type) {
        coordinates = point;
        this.type = type;

        states = new HashSet<GemState>();
    }

    /**
     * TEST: Constructs a gem with the type GemType.test for testing purposes only
     * @param point The coordinates for the constructed gem.
     */
    public BasicGem(GemPoint point) {
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
    public GemPoint getCoordinates() {
        return coordinates;
    }

    @Override
    public void move(GemPoint to) {
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
    public String toString() {
        return "BasicGem{" +
                "coordinates=" + coordinates +
                ", type=" + type +
                ", states=" + states +
                '}';
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

    @Override
    public int compareTo(Gem other) {
        if (this.coordinates.getY() > other.getCoordinates().getY() ||
                (this.coordinates.getY() == other.getCoordinates().getY()
                        && this.coordinates.getX() < other.getCoordinates().getX()))
            return -1;
        else if (this.coordinates.getY() == other.getCoordinates().getY()
                && this.coordinates.getX() == other.getCoordinates().getX())
            return 0;
        else return 1;
    }
}
