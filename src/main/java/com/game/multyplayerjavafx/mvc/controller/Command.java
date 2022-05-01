package com.game.multyplayerjavafx.mvc.controller;

import com.game.multyplayerjavafx.mvc.exceptions.BadCommand;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Command {
        ArrayList<ToDo> toDoList = new ArrayList<ToDo>();

        void getCommands(String fileName){
                BufferedReader reader = null;
                String cmd = null;

                try {
                        reader = new BufferedReader(new FileReader(fileName));
                        cmd = null;
                        while((cmd = reader.readLine()) != null){
                                toDoList.add(new ToDo(cmd));
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (BadCommand e) {
                        e.printStackTrace();
                }
        }

        ArrayList<ToDo> sendCommand(){
                return toDoList;
        }
}
