package com.game.multy_player_javafx.mvc.model.server;

import com.game.multy_player_javafx.mvc.controller.ToDo;
import com.game.multy_player_javafx.mvc.model.Mask;

import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    ArrayList<ToDo> toDoList = new ArrayList<>();
    HashMap<String, Integer> layoutMask = new HashMap<>();
    int userLimit = 0;
    String pathToServer = "";

    public Server(int userLimit, String pathToServer){
        this.userLimit = userLimit;
        this.pathToServer = pathToServer;
    }


    public ArrayList<ToDo> getToDo(){
        return this.toDoList = toDoList;
    }
    public void sendToDo(ArrayList<ToDo> toDoList){
        this.toDoList = toDoList;
    }

    public HashMap<String, Integer> getView(){
        return this.layoutMask;
    }
    public void sendView(HashMap<String, Integer> layoutMask){
        this.layoutMask = layoutMask;
    }
}
