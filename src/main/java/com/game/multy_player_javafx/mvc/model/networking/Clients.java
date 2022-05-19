package com.game.multy_player_javafx.mvc.model.networking;

import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
//tomashorak.hopto.org/9000
public class Clients{
    private static LinkedList<Socket> clientsList = new LinkedList<>();
    private static final String path = "tomashorak.hopto.org";
    public static void setClientsList(LinkedList<Socket> clientsList) {
        Clients.clientsList = clientsList;
    }

    public void send(HashMap<String, ArrayList<Point>> letter_from_server, Boolean RUN){
        Letter letter = new Letter(letter_from_server);
        try {
            for (Socket socket : clientsList) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(letter);
                objectOutputStream.flush();
            }
        } catch (IOException e){
            RUN = false;
        }
    }
}
