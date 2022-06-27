package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;
import com.game.multy_player_javafx.mvc.model.networking.Clients;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ServerController extends Thread{
        Logger log = Logger.getLogger("");
        public Boolean RUN;
        final int port = 9000;
        static final int clientsLimit = 10;
        static AtomicInteger countOfClients = new AtomicInteger(0);
        ServerSocket server;
        public static HashSet<String> usersHistory = new HashSet<>();
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

        public static void deleteOnDispaly(String thread_information) {
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
                            server = new ServerSocket(port, 10); // вот ты его тут открыл, а про закрыть не забудь!!!

                            while (RUN) {
                                if(!freeClientThread.isEmpty()) {
                                    Socket socket = server.accept();

                                    synchronized (this) {
                                        socketsList.add(socket);
                                        serverList.add(clientsPool.get(freeClientThread.get(0)));
                                        serverList.get(serverList.size() - 1).startNewClient(socket, RUN);
                                        freeClientThread.remove(0);
                                        log.info("Length: " + serverList.size());
                                    }
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

            public ArrayList<ToDo> sendCommands(){
                    toDoList.clear();

                    synchronized (this) {
                            for (ClientsRunner clientsRunner : serverList) {
                                    if (clientsRunner.contains())
                                            toDoList.addAll(clientsRunner.getToDoList());
                            }

                            toDoList.addAll(deleteList);
                            deleteList.clear();
                    }

                    return toDoList;
            }
            public LinkedList<Socket> getSockets() {
                    synchronized (this) {
                            return socketsList;
                    }
            }

            public static void removeSocket(Socket socket, ClientsRunner clientsRunner){
                    ReentrantLock lock = new ReentrantLock();
                    lock.lock();
                    socketsList.remove(socket);
                    serverList.remove(clientsRunner);
                    freeClientThread.add(clientsRunner.getIndex());
                    lock.unlock();

                    countOfClients.decrementAndGet();
            }
    }
