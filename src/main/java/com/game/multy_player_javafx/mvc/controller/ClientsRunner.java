package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ClientsRunner extends Thread{
    Logger log = Logger.getLogger(ClientsRunner.class.getName());
    String thread_information;
    Boolean RUN;
    Socket socket;
    ArrayList<String> message_keeper = new ArrayList<>();
    ArrayList<ToDo> toDoList = new ArrayList<>();
    BufferedReader reader;
    public ClientsRunner(Socket socket, Boolean RUN){
        this.RUN = RUN;
        this.socket = socket;
        log.info("Thread started");

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
                log.info("Waiting of reading:");
                message = reader.readLine();
                if(message.equals("stop")) {
                    log.info("stop");
                    break;
                } else if(message.startsWith("*"))
                    log.info(message);
                else if(message.startsWith("THREAD_")) {
                    log.info("thread_info");
                    thread_information = message.substring(7);
                }
                else {
                    log.info("message");
                    log.info(message);

                    if(!message.isEmpty())
                        message_keeper.add(message);
                }
                log.info("--to_the_next--");
            }

            log.info("Finishing ...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                log.info("Socket closed");

                if(!socket.isClosed())
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
