package com.game.multy_player_javafx.mvc.model.active.actions.human.move;


import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.area.Area;
import com.game.multy_player_javafx.mvc.model.passive.area.AreaFrames;
import com.game.multy_player_javafx.mvc.model.passive.items.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.area.geometry.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveLeft implements Action {
    int energy;
    ActiveStatus activeStatus;
    Point coordinate;
    Area place;
    boolean first_time;

    public  MoveLeft(){
        energy = 0;
        first_time = true;
    }
    @Override
    public boolean make(String name, Point coordinate, ActiveStatus[] status, HashMap<Point, PassiveStatus> passive_models, HashMap<String, ArrayList<Point>> letter_to_server, Area place) {
        if(first_time) {
            energy = status[0].energy;
            first_time = false;
        }

        if(energy > 0) {
            energy--;

            if(AreaFrames.isInArea(place, coordinate, status[0].speed, Direction.LEFT, status[0]))
                coordinate.X -= status[0].speed;
        }

        activeStatus = status[0];
        this.coordinate = coordinate;
        this.place = place;

        return true;
    }

    @Override
    public String getViewParam() {
        return "_left:" + (energy + 1) + ":" + activeStatus.dynamicHeight(coordinate, place);
    }

    @Override
    public Action clone() {
        return new MoveLeft();
    }

    @Override
    public boolean equals(Action other) {
        return other != null && other.getClass() == MoveLeft.class;
    }

    @Override
    public boolean isFinished() {
        return energy == 0;
    }
}
