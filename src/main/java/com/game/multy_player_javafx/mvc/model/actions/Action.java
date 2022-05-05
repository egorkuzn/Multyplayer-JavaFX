package com.game.multy_player_javafx.mvc.model.actions;

import com.game.multy_player_javafx.mvc.model.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.PassiveStatus;

import java.util.ArrayList;
import java.util.HashMap;

public interface Action {
    void make(String name,
              Integer coordinate,
              ActiveStatus status,
              HashMap<Integer, PassiveStatus> passive_models,
              HashMap<String, ArrayList<Integer>> letter_to_server);

    Action clone();
}
