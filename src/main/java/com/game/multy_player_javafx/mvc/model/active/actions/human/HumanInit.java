package com.game.multy_player_javafx.mvc.model.active.actions.human;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.area.Area;
import com.game.multy_player_javafx.mvc.model.passive.area.AreaFrames;
import com.game.multy_player_javafx.mvc.model.passive.items.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.area.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class HumanInit implements Action {
    Logger log = Logger.getLogger("");

    @Override
    public boolean make(String name, Point coordinate, ActiveStatus[] status, HashMap<Point, PassiveStatus> passive_models, HashMap<String, ArrayList<Point>> letter_to_server, Area place) {
        AreaFrames.getRandomInArea(place, coordinate);
        return true;
    }

    @Override
    public String getViewParam() {
        return "_init:0";
    }

    @Override
    public Action clone() {
        return new HumanInit();
    }

    @Override
    public boolean equals(Action other) {
        return other != null && other.getClass() == HumanInit.class;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
