package com.game.multy_player_javafx.mvc.view.network_controllers;

import com.game.multy_player_javafx.mvc.model.networking.Letter;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class LetterReceiver extends Thread{
    Logger log = Logger.getLogger("");
    DatagramSocket clientSocket;
    Boolean RUN = true;
    String path = "localhost";
    final int port = 9001;
    Letter letter;
    ObjectInputStream reader;
    DatagramPacket packet;
    boolean isNormal = true;
    byte[] bytes = new byte[1024];

    @Override
    public void run() {
        try {
            clientSocket = new DatagramSocket(port);
            packet = new DatagramPacket(bytes, bytes.length);
            clientSocket.receive(packet);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData());
            reader = new ObjectInputStream(byteArrayInputStream);
            log.info("Done!");

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
