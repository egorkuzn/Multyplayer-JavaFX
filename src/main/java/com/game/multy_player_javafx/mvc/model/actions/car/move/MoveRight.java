package com.game.multy_player_javafx.mvc.model.actions.car.move;

import com.game.multy_player_javafx.mvc.model.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveRight implements Action {
    int energy;
    boolean first_time;

    public MoveRight(){
        energy = 0;
        first_time = true;
    }
    @Override
    public void make(String name, Integer coordinate, ActiveStatus[] status, HashMap<Integer, PassiveStatus> passive_models, HashMap<String, ArrayList<Integer>> letter_to_server) {
        if(first_time){
            energy = status[0].energy;
            first_time = false;
        }

        if(energy > 0){
            energy--;
            coordinate += status[0].speed << 17;
        }
    }

    @Override
    public String getViewParam() {
        return "_right";
    }

    @Override
    public Action clone() {
        return new MoveRight();
    }
}
