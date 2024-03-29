package com.game.multy_player_javafx.mvc.view.network_controllers;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientController extends Thread{
    final String path = "irental.ddns.net";
    final int port= 9000;
    static BufferedWriter out;
    public static Socket clientSocket;
    static AtomicBoolean status = new AtomicBoolean(false);

    public boolean playerConnectionInit(){
        if(!status.get()) {
            try {
                clientSocket = new Socket(path, port);
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                if (!sendCommandToServer("*Hello!")) {
                    status.set(false);
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                status.set(false);
                return false;
            }
        }

        status.set(true);
        return true;
    }

    public void run() {
        while (!status.get())
            status.set(playerConnectionInit());
    }

    public static boolean sendCommandToServer(String message){
        try {
            if(message != null){
                out.write(message);
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            status.set(false);
            return false;
        }

        status.set(true);
        return true;
    }

    public static boolean getStatus() {
        return status.get();
    }
}
