package com.game.multy_player_javafx.mvc.model.active.actions.car.headlights;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class LightsOff  implements Action {
    boolean first_time;
    public LightsOff(){
        first_time = true;
    }
    @Override
    public boolean make(String name, Point coordinate, ActiveStatus[] status, HashMap<Point, PassiveStatus> passive_models, HashMap<String, ArrayList<Point>> letter_to_server) {
        return true;
    }

    @Override
    public String getViewParam() {
        return "_lightsOff";
    }

    @Override
    public Action clone() {
        return new LightsOff();
    }
}
