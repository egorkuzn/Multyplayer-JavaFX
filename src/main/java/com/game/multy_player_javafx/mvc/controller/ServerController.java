package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;
import com.game.multy_player_javafx.mvc.model.networking.Clients;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Timer;

public class ServerController {
        Clients clients;
        final int port = 9000;
        final String path = "tomashorak.hopto.org";
        ServerSocket server;
        LinkedList<Socket> soketsList = new LinkedList<>();
        public ServerController() throws IOException {
                server = new ServerSocket(port); // вот ты его тут открыл, а про закрыть не забудь!!!
                clients = new Clients();
        }
        ArrayList<ToDo> toDoList = new ArrayList<ToDo>();

        public void getCommands() throws IOException {
                soketsList.clear();
                try {
                        while (true/*Тут время должно быть*/) {
                                Socket socket = null;
                                try {
                                        socket = server.accept();
                                        soketsList.add(socket);
                                } catch (IOException e) {
                                        socket.close();
                                }
                        }
                } finally {
                        server.close();
                }
        }

        public Point initSizeLimit(){
                InputStream inputStream = null;
                int X = 200, Y = 200;

                try {
                        Properties prop = new Properties();
                        final String propFileName = "/init_params";
                        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

                        if (inputStream != null) {
                                prop.load(inputStream);
                        }

                        if(prop.containsKey("X") && prop.containsKey("Y")) {
                                X = Integer.parseInt(prop.getProperty("X"));
                                Y = Integer.parseInt(prop.getProperty("Y"));
                        }

                        inputStream.close();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }

                return new Point(X, Y);
        }

        public Clients initClients(){
                return clients;
        }
        public ArrayList<ToDo> sendCommands(){
                return toDoList;
        }
        public LinkedList<Socket> getSockets() {
                return soketsList;
        }
}
