package com.game.multy_player_javafx.mvc.model;

import com.game.multy_player_javafx.mvc.model.actions.Action;

import java.util.HashMap;

public enum ActiveStatus {
    NO_STATUS,
    GIRL,
    BOY,
    CAR,
    DOG;

    public HashMap<String, Action> actionList;
}
