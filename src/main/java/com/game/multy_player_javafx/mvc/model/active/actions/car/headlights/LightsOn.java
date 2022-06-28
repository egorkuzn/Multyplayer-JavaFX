package com.game.multy_player_javafx.mvc.model.active.actions.car.headlights;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.area.Area;
import com.game.multy_player_javafx.mvc.model.passive.items.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.area.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class LightsOn  implements Action {
    boolean first_time;
    public LightsOn(){
        first_time = true;
    }
    @Override
    public boolean make(String name, Point coordinate, ActiveStatus[] status, HashMap<Point, PassiveStatus> passive_models, HashMap<String, ArrayList<Point>> letter_to_server, Area place) {
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

    @Override
    public boolean equals(Action other) {
        return other != null && other.getClass() == LightsOn.class;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
