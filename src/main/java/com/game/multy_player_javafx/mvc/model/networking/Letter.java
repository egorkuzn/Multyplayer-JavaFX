package com.game.multy_player_javafx.mvc.model.networking;

import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.Serializable;
import java.util.*;

public class Letter implements Serializable {
    private final HashMap<String, ArrayList<Point>> data_keeper;
    public Letter(HashMap<String, ArrayList<Point>> letter_from_server) {
        data_keeper = new HashMap<>(letter_from_server);
        sort();
    }

    public HashMap<String, ArrayList<Point>> getData_keeper(){
        return data_keeper;
    }

    void sort(){
        for(Map.Entry<String, ArrayList<Point>> elem : data_keeper.entrySet())
            elem.getValue().sort(Point::compareTo);
    }
}
