package com.game.multy_player_javafx.mvc.model.networking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Letter implements Serializable {
    private final HashMap<String, ArrayList<Integer>> data_keeper;
    public Letter(HashMap<String, ArrayList<Integer>> letter_from_server) {
        data_keeper = new HashMap<>(letter_from_server);
    }

    public HashMap<String, ArrayList<Integer>> getData_keeper(){
        return data_keeper;
    }
}
