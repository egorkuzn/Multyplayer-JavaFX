package com.game.multy_player_javafx.mvc.view.network_controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;

public class ClientController{
    final String path = "127.0.0.1";
    final int port= 8080;
    BufferedWriter out;
    Socket clientSocket;
    public boolean status;

    boolean playerConnetionInit(){
        try {
            clientSocket = new Socket(path, port);

            try {
                FileWriter out = new FileWriter("out.txt");
                out.write("works");
                out.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (IOException e) {
            try {
                FileWriter out = new FileWriter("out.txt");
                out.write("doesn't works");
                out.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            return false;
        }

        return true;
    }

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
