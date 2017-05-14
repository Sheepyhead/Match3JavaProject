package com.sheepy.match3.interfaces; /**
 * Created by Troels "Sheepyhead" Jessen on 17/05/14.
 * Email: kairyuka@live.dk
 */

import com.sheepy.match3.enums.GemState;
import com.sheepy.match3.enums.GemType;

import java.awt.*;
import java.util.Set;

/**
 * Gems are the main objects that are matched in the game.
 */
public interface Gem {
    /**
     * Accessor for X-coordinate
     * @return X-coordinate of the gem
     */
    int getX();

    /**
     * Accessor for Y-coordinate
     * @return Y-coordinate of the gem
     */
    int getY();

    /**
     * Accessor for the coordinates
     * @return The coordinates of the gem
     */
    Point getCoordinates();

    /**
     * Changes the gem's coordinates.
     * @param to The new coordinates for the gem.
     */
    void move(Point to);

    /**
     * The type/color of the gem. Must have exactly one type.
     * @return The type of the gem.
     */
    GemType getType();

    /**
     * The states (if any) of the gem. Can have 0 or more states, duplicates are ignored.
     * @return The set of states for the gem, empty if gem is stateless.
     */
    Set<GemState> getStates();

    /**
     * Test if gem has a particular state.
     * @param state The state to be tested.
     * @return True if gem has the state, false otherwise.
     */
    boolean hasState(GemState state);

    /**c
     * Mutator for the type of the gem. Must have exactly one type.
     * @param type The type of the gem.
     */
    void setType(GemType type);

    /**
     * Method for adding a single state to the gem. Does nothing if the gem already has the state.
     * Can have 0 or more states, duplicates are ignored.
     * @param state The state to add to the gem.
     */
    void addState(GemState state);

    /**
     * Removes a single state from the gem. Does nothing if the gem does not have the state.
     * @param state
     */
    void removeState(GemState state);

    /**
     * Adds multiple states, same conditions as {@link #addState(GemState) addState}.
     * @param states The states to add to the gem.
     */
    void addStates(Set<GemState> states);

    /**
     * Sets the current states on the gem, removing any states that aren't included in {@code Set<GemState> states}.
     * @param states The state set to be set on the gem.
     */
    void setStates(Set<GemState> states);

    /**
     * Removes multiple states, same conditions as {@link #removeState(GemState) removeState}.
     * @param states The states to remove from the gem.
     */
    void removeStates(Set<GemState> states);

    /**
     * Returns a copy of the gem.
     * @return A copy of this gem.
     */
    Gem copy();

    // TODO: Figure out if this is needed
    //void remove();

}
