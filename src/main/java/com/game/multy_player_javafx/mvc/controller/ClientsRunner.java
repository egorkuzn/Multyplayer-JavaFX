package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ClientsRunner extends Thread{
    ReentrantLock lock = new ReentrantLock();
    Logger log = Logger.getLogger("");
    String thread_information = "";
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
                message = reader.readLine();
                if(message.equals("stop")) {
                    log.info("stop");
                    break;
                } else if(message.startsWith("THREAD_")) {
                    synchronized (this) {
                        thread_information = message.substring(7);
                    }
                } else {
                    log.info(message);

                    if(!message.isEmpty() && !message.startsWith("*")) {
                        synchronized (this) {
                            message_keeper.add(message);
                        }
                    }
                }
            }
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
        toDoList.clear();

        synchronized (this) {
            for (String message : message_keeper) {
                try {
                    toDoList.add(new ToDo(thread_information, message));
                    log.info(toDoList.get(0).who().showName());
                } catch (BadCommand e) {
                    e.printStackTrace();
                }
            }

            message_keeper.clear();
        }

        return toDoList;
    }

    public boolean contains() {
        synchronized (this) {
            return !message_keeper.isEmpty();
        }
    }
}
