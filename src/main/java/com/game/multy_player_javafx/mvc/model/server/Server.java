package com.game.multy_player_javafx.mvc.model.server;

import com.game.multy_player_javafx.mvc.controller.ToDo;
import com.game.multy_player_javafx.mvc.model.Mask;

import java.util.ArrayList;

public class Server {
    ArrayList<ToDo> toDoList = new ArrayList<>();
    Mask layoutMask = new Mask();
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

    public Mask getView(){
        return this.layoutMask;
    }
    public void setView(Mask layoutMask){
        this.layoutMask = layoutMask;
    }
}
