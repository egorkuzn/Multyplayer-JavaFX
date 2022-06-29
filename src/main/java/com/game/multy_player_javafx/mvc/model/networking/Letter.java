package com.game.multy_player_javafx.mvc.model.networking;

import com.game.multy_player_javafx.mvc.model.passive.area.geometry.Point;

import java.io.Serializable;
import java.util.*;

public class Letter implements Serializable {
    private final HashMap<String, ArrayList<Point>> data_keeper;
    public Letter(HashMap<String, ArrayList<Point>> letter_from_server) {
        data_keeper = new HashMap<>(letter_from_server);
    }

    public HashMap<String, ArrayList<Point>> getData_keeper(){
        return data_keeper;
    }
}
