package com.game.multy_player_javafx.mvc.model.actions.car.position;

import com.game.multy_player_javafx.mvc.model.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class SitInCar implements Action {
    boolean first_time;
    public SitInCar(){
        first_time = true;
    }
    @Override
    public void make(String name, Integer coordinate, ActiveStatus[] status, HashMap<Integer, PassiveStatus> passive_models, HashMap<String, ArrayList<Integer>> letter_to_server) {
        if(first_time){
            status[1] = status[0];
            status[0] = ActiveStatus.CAR;
            first_time = false;
        }
    }

    @Override
    public String getViewParam() {
        return "";
    }

    @Override
    public Action clone() {
        return new SitInCar();
    }
}
