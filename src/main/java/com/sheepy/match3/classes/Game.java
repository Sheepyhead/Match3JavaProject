package com.sheepy.match3.classes;
/**
 * Created by Troels "Sheepyhead" Jessen on 17/05/14.
 * Email: kairyuka@live.dk
 */

import com.sheepy.match3.enums.GemType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The Game object is the primary object that holds control of the entire game.
 */
public class Game {
    public static final GemType[] TYPE_LIST = new GemType[] {GemType.type1, GemType.type2, GemType.type3, GemType.type4, GemType.type5};

    public Game() {
        System.out.println("Constructing game object");
    }

    public static void main(String[] args) {
        System.out.println("Starting game");
    }

}
