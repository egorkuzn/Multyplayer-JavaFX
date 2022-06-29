package com.game.multy_player_javafx.mvc.model.active.actions.car.position;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.area.Area;
import com.game.multy_player_javafx.mvc.model.passive.items.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.area.geometry.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class OutFromCar implements Action {
    boolean first_time;
    public OutFromCar(){
        first_time = true;
    }
    @Override
    public boolean make(String name, Point coordinate, ActiveStatus[] status, HashMap<Point, PassiveStatus> passive_models, HashMap<String, ArrayList<Point>> letter_to_server, Area place) {
        if(first_time){
            status[0] = status[1];
            first_time = false;
        }

        return true;
    }

    @Override
    public String getViewParam() {
        return "";
    }

    @Override
    public Action clone() {
        return new OutFromCar();
    }

    @Override
    public boolean equals(Action other) {
        return other != null && other.getClass() == OutFromCar.class;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
