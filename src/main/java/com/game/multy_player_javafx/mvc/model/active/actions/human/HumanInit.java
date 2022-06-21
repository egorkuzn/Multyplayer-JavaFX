package com.game.multy_player_javafx.mvc.model.active.actions.human;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class HumanInit implements Action {

    @Override
    public boolean make(String name, Point coordinate, ActiveStatus[] status, HashMap<Point, PassiveStatus> passive_models, HashMap<String, ArrayList<Point>> letter_to_server) {
        return false;
    }

    @Override
    public String getViewParam() {
        return "_init";
    }

    @Override
    public Action clone() {
        return null;
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
