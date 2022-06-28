package com.game.multy_player_javafx;

import com.game.multy_player_javafx.mvc.controller.ServerController;
import com.game.multy_player_javafx.mvc.model.Model;

public class Server {
    public static void main(String[] args){
        ServerController controller = new ServerController();
        Model city = new Model(controller);
        city.run();
    }
}
