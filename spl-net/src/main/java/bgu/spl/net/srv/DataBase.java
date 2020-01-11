package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataBase {
    private DataBase database = new DataBase();
    private ConcurrentHashMap<String,String> users; // user name , passcode
    private ConcurrentHashMap<String,ConcurrentLinkedQueue<Integer>> topics;// topic, user id list
    private ConcurrentHashMap<String,ConcurrentLinkedQueue<String>> booksByTopic;// topic, books list
    private ConcurrentHashMap<Integer,ConcurrentLinkedQueue<String>> clintesBooks;// user id, books list
    private ConcurrentHashMap<Integer,NonBlockingConnectionHandler> connectionsHandlers;// ConHandler for each active user (by user's connectionId)

    private DataBase(){
        users = new ConcurrentHashMap<>();
        topics = new ConcurrentHashMap<>();
        booksByTopic = new ConcurrentHashMap<>();
        clintesBooks = new ConcurrentHashMap<>();
        connectionsHandlers= new ConcurrentHashMap<>();
    }
    public DataBase getInstance(){return database;}// singleton

    public ConcurrentHashMap<String, String> getUsers() {
        return users;
    }

    public ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> getBooksByTopic() {
        return booksByTopic;
    }

    public ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>> getClintesBooks() {
        return clintesBooks;
    }

    public ConcurrentHashMap<Integer, NonBlockingConnectionHandler> getConnectionsHandlers() {
        return connectionsHandlers;
    }
    public ConcurrentHashMap<String,ConcurrentLinkedQueue<Integer>> getTopics() {
        return topics;
    }
}
