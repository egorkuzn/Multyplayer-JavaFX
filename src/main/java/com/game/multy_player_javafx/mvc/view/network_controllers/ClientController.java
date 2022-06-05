package com.game.multy_player_javafx.mvc.view.network_controllers;

import java.io.*;
import java.net.DatagramSocket;
import java.net.Socket;

public class ClientController extends Thread{
    final String path = "localhost";
    final int port= 9000;
    static BufferedWriter out;
    public static Socket clientSocket;
    public boolean status = true;

    boolean playerConnetionInit(){
        try {
            clientSocket = new Socket(path, port);
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            if (!sendCommandToServer("*Hello!"))
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void run() {
        status = playerConnetionInit();
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
            return false;
        }

        return true;
    }
}
