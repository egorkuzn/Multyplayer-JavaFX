package com.game.multyplayerjavafx.model;

import com.game.multyplayerjavafx.model.actions.Action;
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
}
