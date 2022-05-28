package com.game.multy_player_javafx.mvc.view.network_controllers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class ClientController extends Thread{
    final String path = "tomashorak.hopto.org";
    final int port= 9000;
    BufferedWriter out;
    Socket clientSocket;
    public boolean status;

    boolean playerConnetionInit(){
        try {
            clientSocket = new Socket(path, port);
        } catch (IOException e) {
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
