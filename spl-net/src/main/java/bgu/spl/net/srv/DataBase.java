package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataBase {
    private DataBase database = new DataBase();
    private ConcurrentHashMap<String,String> users; // user name , passcode
    private ConcurrentHashMap<String,ConcurrentLinkedQueue<String>> topics;// topic, user names list
    private ConcurrentHashMap<String,ConcurrentLinkedQueue<String>> booksByTopic;// topic, books list
    private ConcurrentHashMap<String,ConcurrentLinkedQueue<String>> clintesBooks;// client, books list



    private DataBase(){
        users = new ConcurrentHashMap<>();
        topics = new ConcurrentHashMap<>();
        booksByTopic = new ConcurrentHashMap<>();
        clintesBooks = new ConcurrentHashMap<>();
    }
    public DataBase getInstance(){return database;}// singleton
}
