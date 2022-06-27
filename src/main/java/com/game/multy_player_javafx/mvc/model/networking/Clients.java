package com.game.multy_player_javafx.mvc.model.networking;

import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

//tomashorak.hopto.org/9000
public class Clients{
    Logger log = Logger.getLogger("");
    LinkedList<Socket> clientsList = new LinkedList<>();
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
            DatagramChannel channel = DatagramChannel.open();

            for (Socket socket : clientsList) {
                synchronized (socket.getInetAddress()){
                    // TODO : try to use datagram channels from https://flylib.com/books/en/1.134.1/datagram_channels.html
                    SocketAddress client = socket.getLocalSocketAddress();
                    channel.send(ByteBuffer.wrap(data), client);
                }
            }

            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
