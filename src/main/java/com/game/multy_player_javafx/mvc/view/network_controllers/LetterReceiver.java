package com.game.multy_player_javafx.mvc.view.network_controllers;

import com.game.multy_player_javafx.mvc.model.networking.Letter;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class LetterReceiver extends Thread{
    DatagramSocket clientSocket;
    Boolean RUN = true;
    final int port = 9000;
    Letter letter;
    DatagramPacket packet;
    AtomicBoolean isNormal = new AtomicBoolean(true);
    byte[] bytes = new byte[1024];

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(ClientController.clientSocket.getInputStream());

            while (RUN){
//                clientSocket = new DatagramSocket(port);
//                packet = new DatagramPacket(bytes, 1024);
//                clientSocket.receive(packet);
//                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData());
//                clientSocket.close();
//                Object object = new ObjectInputStream(byteArrayInputStream).readObject();
//                byteArrayInputStream.close();

                Object object = objectInputStream.readObject();

                if(object != null && object.getClass() == Letter.class) {
                    letter = (Letter) object;
                    System.out.println("Caught!!!");
                    System.out.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("IO");
            System.out.flush();
            e.printStackTrace();
            isNormal.set(false);
        } catch (ClassNotFoundException e) {
            System.out.println("CLASS");
            System.out.flush();

            isNormal.set(false);
        }
    }

    public HashMap<String, ArrayList<Point>> getMap(){
        HashMap<String, ArrayList<Point>> map;

        synchronized (this) {
            map = letter.getData_keeper();
            letter = null;
        }

        return map;
    }

    public boolean itWorks(){
        return isNormal.get();
    }
    public boolean newLetter(){
        synchronized (this) {
            return letter != null;
        }
    }
}
