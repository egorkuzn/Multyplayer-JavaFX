package com.game.multy_player_javafx.mvc.model.networking;

import com.game.multy_player_javafx.mvc.model.passive.area.Point;

import java.io.*;
import java.net.*;
import java.util.*;
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
//            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
             ObjectOutputStream objectOutputStream;
//            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//            log.info("Send out...");
//            objectOutputStream.writeObject(letter);
//            final byte[] data = byteArrayOutputStream.toByteArray();
//            DatagramChannel channel = DatagramChannel.open();

            List<Socket> threadSafeList = Collections.synchronizedList(clientsList);

            synchronized (threadSafeList) {
                for (Socket socket : threadSafeList) {
                   objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                   objectOutputStream.writeObject(letter);
                   objectOutputStream.flush();
//                    SocketAddress client = socket.getLocalSocketAddress();
//                    channel.send(ByteBuffer.wrap(data), client);
                }
            }
//            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
