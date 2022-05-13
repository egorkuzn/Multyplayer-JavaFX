package com.game.multy_player_javafx.mvc.model.networking;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
//tomashorak.hopto.org/9000
public class Clients{
    private static LinkedList<Socket> clientsList = new LinkedList<>();
    private static final String path = "tomashorak.hopto.org";
    private static boolean isRunnable = true;
    public static void setClientsList(LinkedList<Socket> clientsList) {
        Clients.clientsList = clientsList;
    }

    public void send(HashMap<String, ArrayList<Integer>> letter_from_server, Boolean RUN){
        Letter letter = new Letter(letter_from_server);
        clientsList.forEach(socket ->{
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(letter);
                objectOutputStream.flush();
            } catch (IOException e) {
                isRunnable = false;
            }
        });

        RUN = isRunnable;
    }
}
