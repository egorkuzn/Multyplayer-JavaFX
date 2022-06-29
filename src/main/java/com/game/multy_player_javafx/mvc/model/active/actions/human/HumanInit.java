package com.game.multy_player_javafx.mvc.model.active.actions.human;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.active.actions.human.move.Direction;
import com.game.multy_player_javafx.mvc.model.passive.area.Area;
import com.game.multy_player_javafx.mvc.model.passive.area.AreaFrames;
import com.game.multy_player_javafx.mvc.model.passive.items.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.area.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class HumanInit implements Action {
    Logger log = Logger.getLogger("");
    boolean isSet = false;
    ActiveStatus activeStatus;
    Point coordinate;
    Area place;

    @Override
    public boolean make(String name, Point coordinate, ActiveStatus[] status, HashMap<Point, PassiveStatus> passive_models, HashMap<String, ArrayList<Point>> letter_to_server, Area place) {
        while (!isSet) {
            AreaFrames.getRandomInArea(place, coordinate);
            isSet = AreaFrames.isInArea(place, coordinate, 0, Direction.DOWN, status[0]);
        }

        this.coordinate = coordinate;
        this.place = place;
        activeStatus = status[0];

        return true;
    }

    @Override
    public String getViewParam() {
        return "_init:0:" + activeStatus.dynamicHeight(coordinate, place);
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
        return isSet;
    }
}
