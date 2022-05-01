package com.game.multyplayerjavafx.controller;

import java.util.ArrayList;

public class Command {
        ArrayList<ToDo> toDoList = new ArrayList<ToDo>();

        void getCommands(smt){
                while(smt.haveCommands){
                        cmd = smt.get();
                        toDoList.append(cmd);
                }
        }

        ArrayList<ToDo> sendCommand(){
                return toDoList;
        }
}
