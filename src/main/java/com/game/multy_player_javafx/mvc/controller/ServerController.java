package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.model.networking.Clients;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

public class ServerController extends Thread{
        Clients clients;
        public Boolean RUN;
        final int port = 9000;
        final String path = "tomashorak.hopto.org";
        ServerSocket server;
        public LinkedList<Socket> socketsList = new LinkedList<>();
        ArrayList<ClientsRunner> serverList;
        public ServerController(){
                RUN = true;
                initSizeLimit();
                start();
        }
        public ArrayList<ToDo> toDoList = new ArrayList<ToDo>();

        public Point initSizeLimit(){
                InputStream inputStream = null;
                int X = 200, Y = 200;

                try {
                        Properties prop = new Properties();
                        final String propFileName = "/init_params.properties";
                        inputStream = getClass().getResourceAsStream(propFileName);

                        if (inputStream != null) {
                                prop.load(inputStream);
                        }

                        if(prop.containsKey("X") && prop.containsKey("Y")) {
                                X = Integer.parseInt(prop.getProperty("X"));
                                Y = Integer.parseInt(prop.getProperty("Y"));
                        }

                        inputStream.close();
                } catch (IOException e) {
                        RUN = false;
                        throw new RuntimeException(e);
                }

                return new Point(X, Y);
        }

        @Override
        public void run() {
                serverList = new ArrayList<>();

                try {
                        server = new ServerSocket(port); // вот ты его тут открыл, а про закрыть не забудь!!!
                        try (ServerSocket server = new ServerSocket(port)) {
                                while (RUN) {
                                        Socket socket = server.accept();
                                        socketsList.add(socket);
                                        serverList.add(new ClientsRunner(socket, RUN));
                                }
                        } catch (IOException e){
                                RUN = false;
                        }

                } catch (IOException e) {
                        throw new RuntimeException(e);
                } finally {
                        try {
                                if(!server.isClosed())
                                        server.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        public Clients initClients(){
                return clients;
        }
        public ArrayList<ToDo> sendCommands(){
                toDoList.clear();

                for(ClientsRunner clientsRunner : serverList)
                        toDoList.addAll(clientsRunner.getToDoList());

                return toDoList;
        }
        public LinkedList<Socket> getSockets() {
                return socketsList;
        }
}
