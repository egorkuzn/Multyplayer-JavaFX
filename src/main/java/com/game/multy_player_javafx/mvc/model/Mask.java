package com.game.multy_player_javafx.mvc.model;

import java.util.ArrayList;
import java.util.HashSet;

public class Mask {
    int xLimit;
    int yLimit;
    HashSet<ModelStatus> matrix = new HashSet<>();
    public Mask(int xLimit, int yLimit){
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public void addCoordinates(Integer[] coordinates){
        matrix.add(coordinates[0]);
        matrix.add(coordinates[0]);
    }
}
