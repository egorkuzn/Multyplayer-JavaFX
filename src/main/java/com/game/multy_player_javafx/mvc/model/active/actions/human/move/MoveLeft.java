package com.game.multy_player_javafx.mvc.model.active.actions.human.move;


import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveLeft implements Action {
    int energy;
    boolean first_time;

    public  MoveLeft(){
        energy = 0;
        first_time = true;
    }
    @Override
    public boolean make(String name, Integer coordinate, ActiveStatus[] status, HashMap<Integer, PassiveStatus> passive_models, HashMap<String, ArrayList<Integer>> letter_to_server) {
        if(first_time) {
            energy = status[0].energy;
            first_time = false;
        }

        if(energy > 0) {
            energy--;
            coordinate -= status[0].speed << 17;
        }

        return true;
    }

    @Override
    public String getViewParam() {
        return "_left";
    }

    @Override
    public Action clone() {
        return new MoveLeft();
    }
}
