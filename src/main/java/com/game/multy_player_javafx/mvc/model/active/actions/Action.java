package com.game.multy_player_javafx.mvc.model.active.actions;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.PassiveStatus;

import java.util.ArrayList;
import java.util.HashMap;

public interface Action {
    boolean make(String name,
              Integer coordinate,
              ActiveStatus[] status,
              HashMap<Integer, PassiveStatus> passive_models,
              HashMap<String, ArrayList<Integer>> letter_to_server);

    String getViewParam();

    Action clone();
}
