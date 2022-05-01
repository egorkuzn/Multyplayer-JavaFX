package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Command {
        ArrayList<ToDo> toDoList = new ArrayList<ToDo>();

        public void getCommands(String fileName){
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

        public ArrayList<ToDo> sendCommand(){
                return toDoList;
        }
}
