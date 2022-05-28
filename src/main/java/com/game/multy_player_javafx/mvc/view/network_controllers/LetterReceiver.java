package com.game.multy_player_javafx.mvc.view.network_controllers;

import com.game.multy_player_javafx.mvc.model.networking.Letter;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class LetterReceiver extends Thread{
    Socket clientSocket;
    Boolean RUN = true;
    String path = "tomashorak.hopto.org";
    int port = 9000;
    Letter letter;
    ObjectInputStream reader;
    boolean isNormal = true;
    @Override
    public void run() {
        try {
            clientSocket = new Socket(path, port);
            reader = new ObjectInputStream(clientSocket.getInputStream());

            while (RUN){
                Object object = reader.readObject();

                if(object != null && object.getClass() == Letter.class){
                    letter = (Letter) object;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            isNormal = false;
        }
    }

    public HashMap<String, ArrayList<Point>> getMap(){
        HashMap<String, ArrayList<Point>> map = letter.getData_keeper();
        letter = null;
        return map;
    }

    public boolean itWorks(){
        return isNormal;
    }
    public boolean newLetter(){
        return letter != null;
    }
}
