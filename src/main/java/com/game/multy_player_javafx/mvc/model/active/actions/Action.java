package com.game.multy_player_javafx.mvc.model.active.actions;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.util.ArrayList;
import java.util.HashMap;

public interface Action {
    boolean make(String name,
                 Point coordinate,
                 ActiveStatus[] status,
                 HashMap<Point, PassiveStatus> passive_models,
                 HashMap<String, ArrayList<Point>> letter_to_server);

    String getViewParam();

    Action clone();

    boolean equals(Action other);

    boolean isFinished();
}
