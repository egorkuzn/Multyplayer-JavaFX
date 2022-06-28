package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;
import com.game.multy_player_javafx.mvc.model.passive.area.Point;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ServerController extends Thread{
        Logger log = Logger.getLogger("");
        public Boolean RUN;
        final int port = 9000;
        static final int clientsLimit = 3;
        static AtomicInteger countOfClients = new AtomicInteger(0);
        ServerSocket server;
        static LinkedList<Socket> socketsList = new LinkedList<>();
        static ArrayList<ClientsRunner> serverList = new ArrayList<>();
        static ArrayList<ToDo> deleteList = new ArrayList<>();
        public ArrayList<ToDo> toDoList = new ArrayList<>();
        static ArrayList<Integer> freeClientThread = new ArrayList<>();
        ArrayList<ClientsRunner> clientsPool = new ArrayList<>();

        public ServerController(){
                RUN = true;
                initSizeLimit();

                for(int i = 0; i < clientsLimit; i++) {
                    clientsPool.add(new ClientsRunner(i));
                    freeClientThread.add(i);
                }

                start();
        }

        public static void deleteOnDisplay(String thread_information) {
            try {
                deleteList.add(new ToDo(thread_information, "DELETE"));
            } catch (BadCommand e) {
                throw new RuntimeException(e);
            }
        }

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
            try {
                server = new ServerSocket(port, 10);

                while (RUN) {
                    if (!freeClientThread.isEmpty()) {
                        Socket socket = server.accept();

                        socketsList.add(socket);
                        serverList.add(clientsPool.get(freeClientThread.get(0)));
                        serverList.get(serverList.size() - 1).startNewClient(socket);
                        freeClientThread.remove(0);
                        log.info("Length: " + serverList.size());
                    }
                }

            } catch (IOException e) {
                RUN = false;
                e.printStackTrace();
            } finally {
                try {
                    if(!server.isClosed())
                        server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized ArrayList<ToDo> sendCommands(){
            toDoList.clear();

            for (ClientsRunner clientsRunner : serverList) {
                if (clientsRunner.contains())
                    toDoList.addAll(clientsRunner.getToDoList());
            }

            toDoList.addAll(deleteList);
            deleteList.clear();

            return toDoList;
        }

        public synchronized LinkedList<Socket> getSockets() {
            return socketsList;
        }

        public static synchronized void removeSocket(Socket socket, ClientsRunner clientsRunner){
            socketsList.remove(socket);
            serverList.remove(clientsRunner);
            freeClientThread.add(clientsRunner.getIndex());
            countOfClients.decrementAndGet();
        }
    }
