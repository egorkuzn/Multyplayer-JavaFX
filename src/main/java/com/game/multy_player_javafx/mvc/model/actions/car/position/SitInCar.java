package com.game.multy_player_javafx.mvc.model.actions.car.position;

import com.game.multy_player_javafx.mvc.model.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class SitInCar implements Action {
    @Override
    public void make(String name, Integer coordinate, ActiveStatus status, HashMap<Integer, PassiveStatus> passive_models, HashMap<String, ArrayList<Integer>> letter_to_server) {

    }

    @Override
    public Action clone() {
        return null;
    }
}
