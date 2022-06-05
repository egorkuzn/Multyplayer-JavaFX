package com.game.multy_player_javafx.mvc.model.networking;

import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

//tomashorak.hopto.org/9000
public class Clients{
    Logger log = Logger.getLogger("");
    private LinkedList<Socket> clientsList = new LinkedList<>();
    final String path = "tomashorak.hopto.org";
    public void setClientsList(LinkedList<Socket> clientsList) {
        this.clientsList = clientsList;
    }

    public boolean send(HashMap<String, ArrayList<Point>> letter_from_server){
        Letter letter = new Letter(letter_from_server);

        try {
            for (Socket socket : clientsList) {
                //TODO: письмо нахер не нужно
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                log.info(letter_from_server.toString());
                objectOutputStream.writeInt(50);
                objectOutputStream.flush();
            }
        } catch (IOException e){
            return false;
        }

        return true;
    }
}
