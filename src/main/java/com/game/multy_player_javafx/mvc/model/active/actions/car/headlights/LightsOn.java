package com.game.multy_player_javafx.mvc.model.active.actions.car.headlights;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class LightsOn  implements Action {
    boolean first_time;
    public LightsOn(){
        first_time = true;
    }
    @Override
    public boolean make(String name, Integer coordinate, ActiveStatus[] status, HashMap<Integer, PassiveStatus> passive_models, HashMap<String, ArrayList<Integer>> letter_to_server) {
        return true;
    }

    @Override
    public String getViewParam() {
        return "_lightsOn";
    }

    @Override
    public Action clone() {
        return new LightsOff();
    }
}
