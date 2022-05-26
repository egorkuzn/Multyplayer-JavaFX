package com.game.multy_player_javafx.mvc.view.network_controllers;

import com.game.multy_player_javafx.mvc.model.networking.Letter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class LetterReciever extends Thread{
    Socket clientSocket;
    Boolean RUN = true;
    String path = "0.0.0.0";
    int port = 9000;
    Letter letter;
    ObjectInputStream reader;
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
            throw new RuntimeException(e);
        }
    }

    public Letter getLetter(){
        return letter;
    }
}
