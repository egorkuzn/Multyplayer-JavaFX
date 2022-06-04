package com.game.multy_player_javafx.mvc.view.network_controllers;

import java.io.*;
import java.net.DatagramSocket;
import java.net.Socket;

public class ClientController extends Thread{
    final String path = "localhost";
    final int port= 9000;
    BufferedWriter out;
    Socket clientSocket;
    public boolean status = true;

    boolean playerConnetionInit(){
        try {
            clientSocket = new Socket(path, port);
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    @Override
    public void run() {
        status = playerConnetionInit();
    }

    public boolean sendCommandToServer(String message){
        try {
            out.write(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
