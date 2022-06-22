package com.game.multy_player_javafx.mvc.model.networking;

import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

//tomashorak.hopto.org/9000
public class Clients{
    Logger log = Logger.getLogger("");
    LinkedList<Socket> clientsList = new LinkedList<>();
    final int port = 9001;
    public void setClientsList(LinkedList<Socket> clientsList) {
        this.clientsList = clientsList;
    }

    public boolean send(HashMap<String, ArrayList<Point>> letter_from_server){
        Letter letter = new Letter(letter_from_server);

        try {
            //I don't know what object size is. So 1024 would be perfect
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            log.info("Send out...");
            objectOutputStream.writeObject(letter);
            final byte[] data = byteArrayOutputStream.toByteArray();

            for (Socket socket : clientsList) {
                synchronized (socket.getInetAddress()){
                    DatagramSocket datagramSocket = new DatagramSocket();
                    DatagramPacket packet = new DatagramPacket(data, data.length, socket.getInetAddress(), port);
                    datagramSocket.send(packet);
                    datagramSocket.disconnect();
                    datagramSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
