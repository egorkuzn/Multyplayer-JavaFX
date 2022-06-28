package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ClientsRunner extends Thread{
    Logger log = Logger.getLogger("");
    String thread_information = "";
    AtomicBoolean RUN = new AtomicBoolean(true);
    AtomicBoolean isStarted = new AtomicBoolean(false);
    Socket socket;
    CopyOnWriteArrayList<String> message_keeper = new CopyOnWriteArrayList<>();
    ArrayList<ToDo> toDoList = new ArrayList<>();
    BufferedReader reader;
    final int index;
    public ClientsRunner(int index){
        this.index = index;
        start();
    }

    public void startNewClient(Socket socket){
        this.socket = socket;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            isStarted.set(true);
            log.info("Thread started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (RUN.get()) {
            if(isStarted.get()) {
                String message = "";

                try {
                    while (RUN.get()) {
                        message = reader.readLine();

                        if(message == null)
                            break;

                        if (message.equals("stop")) {
                            log.info("stop");
                            ServerController.deleteOnDisplay(thread_information);
                            break;
                        } else if (message.startsWith("THREAD_")) {
                            thread_information = message.substring(7);
                        } else {
                            log.info(message);

                            if (!message.isEmpty() && !message.startsWith("*"))
                                message_keeper.add(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        log.info("Socket closed");
                        reader.close();
                        ServerController.removeSocket(socket, this);

                        if (!socket.isClosed())
                            socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            isStarted.set(false);
        }
    }

    public synchronized ArrayList<ToDo> getToDoList() {
        toDoList.clear();

        for (String message : message_keeper) {
            try {
                toDoList.add(new ToDo(thread_information, message));
                log.info(toDoList.get(0).who().getName());
            } catch (BadCommand e) {
                e.printStackTrace();
            }
        }

        message_keeper.clear();
        return toDoList;
    }

    public synchronized boolean contains() {
        return !message_keeper.isEmpty();
    }

    int getIndex(){
        return index;
    }
}
