package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientsRunner extends Thread{
    String thread_information;
    Boolean RUN;
    Socket socket;
    ArrayList<String> message_keeper;
    ArrayList<ToDo> toDoList = new ArrayList<>();
    BufferedReader reader;
    public ClientsRunner(Socket socket, Boolean RUN){
        this.RUN = RUN;
        this.socket = socket;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            RUN = false;
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        String message =  "";

        try {
            while (RUN){
                message = reader.readLine();

                if(message.equals("stop"))
                    break;

                if(message.startsWith("THREAD_"))
                    thread_information = message.substring(7);

                message_keeper.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        } finally {
//            try {
//                if(!socket.isClosed())
//                    socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public ArrayList<ToDo> getToDoList() {
        for(String message: message_keeper) {
            try {
                toDoList.add(new ToDo(thread_information, message));
            } catch (BadCommand e) {
                e.printStackTrace();
            }
        }

        return toDoList;
    }
}
