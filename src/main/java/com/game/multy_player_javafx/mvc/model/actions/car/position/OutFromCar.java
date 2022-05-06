package com.game.multy_player_javafx.mvc.model.actions.car.position;

import com.game.multy_player_javafx.mvc.model.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class OutFromCar implements Action {
    boolean first_time;
    public OutFromCar(){
        first_time = true;
    }
    @Override
    public void make(String name, Integer coordinate, ActiveStatus[] status, HashMap<Integer, PassiveStatus> passive_models, HashMap<String, ArrayList<Integer>> letter_to_server) {
        if(first_time){
            status[0] = status[1];
            first_time = false;
        }
    }

    @Override
    public String getViewParam() {
        return "";
    }

    @Override
    public Action clone() {
        return new OutFromCar();
    }
}
