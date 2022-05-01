package com.game.multy_player_javafx.mvc.model;

import com.game.multy_player_javafx.mvc.model.actions.Action;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public enum ModelStatus {
    GIRL,
    BOY,
    CAR,
    DOG;

    public HashMap<String, Action> actionList;
    public Image image;
    public ArrayList<Integer> coordinatesBuffer;
}
