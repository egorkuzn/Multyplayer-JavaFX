package com.game.multy_player_javafx.mvc.model.active;

import com.game.multy_player_javafx.mvc.model.active.actions.Action;

import java.util.HashMap;

public enum ActiveStatus {
    NO_STATUS,
    GIRL,
    BOY,
    CAR,
    DOG;

    // defaults energy and speed
    public int energy = 4;
    public int speed = 40;
    public HashMap<String, Action> actionList = new HashMap<>();
}
