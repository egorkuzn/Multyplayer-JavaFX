package com.game.multy_player_javafx.mvc.model.actions.car.headlights;

import com.game.multy_player_javafx.mvc.model.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class LightsOn  implements Action {
    boolean first_time;
    public LightsOn(){
        first_time = true;
    }
    @Override
    public void make(String name, Integer coordinate, ActiveStatus[] status, HashMap<Integer, PassiveStatus> passive_models, HashMap<String, ArrayList<Integer>> letter_to_server) {

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
