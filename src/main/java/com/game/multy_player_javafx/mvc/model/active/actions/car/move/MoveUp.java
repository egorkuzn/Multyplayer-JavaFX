package com.game.multy_player_javafx.mvc.model.active.actions.car.move;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.car.headlights.LightsOff;
import com.game.multy_player_javafx.mvc.model.passive.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveUp implements Action {
    int energy;
    boolean first_time;

    public MoveUp(){
        energy = 0;
        first_time = true;
    }
    @Override
    public boolean make(String name, Point coordinate, ActiveStatus[] status, HashMap<Point, PassiveStatus> passive_models, HashMap<String, ArrayList<Point>> letter_to_server, String placeName) {
        if(first_time){
            energy = status[0].energy;
            first_time = false;
        }

        if(energy > 0){
            energy--;
            coordinate.Y -= status[0].speed;
        }

        return true;
    }

    @Override
    public String getViewParam() {
        return "_up:" + energy;
    }

    @Override
    public Action clone() {
        return new MoveUp();
    }

    @Override
    public boolean equals(Action other) {
        return other != null && other.getClass() == MoveUp.class;
    }

    @Override
    public boolean isFinished() {
        return energy == 0;
    }
}
